package com;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bean.Job;
import com.bean.Worker;
import com.controller.JobMatcherController;
import com.mongodb.BulkWriteResult;
import com.utility.RestServiceInvoker;

/**
 * 
 * @author aditiphadke
 *
 */
@Service
@Configuration
@EnableScheduling
public class JobMatcherScheduler {
	private static final String JOBS = "jobs";

	private static final String WORKERS = "workers";

	private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherController.class);

	@Autowired
	private MongoOperations mongoOps;

	@Autowired
	private RestServiceInvoker restCallService;

	@Scheduled(fixedDelay = 10000000)
	public void syncWorkers() {
		clearData(Worker.class, "workers");
		ResponseEntity<Worker[]> list = restCallService.invoke("/workers", Worker[].class);
		BulkWriteResult result = bulkInsertData(list);
		LOGGER.info("Total: {} records inserted to workers", result.getInsertedCount());
	}

	@Scheduled(fixedDelay = 10000000)
	public void syncJobs() {
		clearData(Job.class, "jobs");
		ResponseEntity<Job[]> list = restCallService.invoke("/jobs", Job[].class);
		// System.out.println(list.getBody()[0]);
		BulkOperations bulkops = mongoOps.bulkOps(BulkMode.UNORDERED, Job.class, JOBS);
		List<Job> jobs = Arrays.asList(list.getBody());
		bulkops.insert(jobs);
		BulkWriteResult result = bulkops.execute();
		LOGGER.info("Total: {} records inserted to jobs", result.getInsertedCount());
	}

	// TODO common and service
	private BulkWriteResult bulkInsertData(ResponseEntity<Worker[]> list) {
		BulkOperations bulkops = mongoOps.bulkOps(BulkMode.UNORDERED, Worker.class, WORKERS);
		List<Worker> jobs = Arrays.asList(list.getBody());
		bulkops.insert(jobs);
		BulkWriteResult result = bulkops.execute();
		return result;
	}

	public <T> void clearData(Class<T> clazz, String collectionName) {
		Query query = new Query();
		mongoOps.remove(query, clazz, collectionName);
	}
}
