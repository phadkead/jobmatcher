package com.service;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.bean.Job;
import com.bean.Worker;

/**
 * Service performing all magic of job matching
 * 
 * @author aditiphadke
 *
 */
@Service
public class JobMatcherService {

	private static final String WORKER_ID = "userId";

	private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherService.class);

	@Autowired
	private MongoOperations mongoOps;

	/**
	 * Finds best matching job for workedId with following matchin process: 
	 * 1.Based on geo location specified by worker 
	 * 2. if license criteria matches
	 * 3. skill matching 
	 * 4. top 3 jobs based on best bill rate offered
	 * 
	 * @param workerId
	 * @return
	 */
	public List<Job> findBestJobsForWorker(int workerId) {
		Worker worker = findWorkerByWorkerId(workerId);
		if (worker == null) {
			return null; // TODO optional
		}
		Point point = new Point(worker.getJobSearchAddress().getLongitude(),
				worker.getJobSearchAddress().getLatitude());
		NearQuery nearQuery = NearQuery.near(point).maxDistance(worker.getJobSearchAddress().getMaxJobDistance() * 1000)
				.spherical(true); // TODO remove hard coded 1000

		appendLicenseCriteria(worker, nearQuery);

		GeoResults<Job> jobs = mongoOps.geoNear(nearQuery, Job.class, "jobs");
		Stream<GeoResult<Job>> targetStream = StreamSupport
				.stream(Spliterators.spliteratorUnknownSize(jobs.iterator(), Spliterator.ORDERED), false);

		Stream<Job> job = targetStream.map(s -> s.getContent())
				.filter(j -> worker.getCertificates().containsAll(j.getRequiredCertificates()))
				.sorted(billRateComparator().reversed()).limit(3);

		List<Job> matchingJobs = job.collect(Collectors.toList());

		LOGGER.info("Total Matching jobs: {}", matchingJobs.size());

		matchingJobs.forEach(System.out::println);

		return matchingJobs;
	}

	/**
	 * Appends License critera If worker does not have a license, then need to
	 * check driverLicenseRequired for jobs
	 * 
	 * @param worker
	 * @param nearQuery
	 */
	public void appendLicenseCriteria(Worker worker, NearQuery nearQuery) {
		if (!worker.isHasDriversLicense()) {
			Query licenseQuery = new Query();
			licenseQuery.addCriteria(Criteria.where("driverLicenseRequired").is(false));
			nearQuery.query(licenseQuery);
		}
	}

	public Worker findWorkerByWorkerId(int workerId) {
		Query workerQuery = new Query();
		workerQuery.addCriteria(Criteria.where(WORKER_ID).is(workerId));
		Worker worker = mongoOps.findOne(workerQuery, Worker.class, "workers");
		return worker;
	}

	private static Comparator<Job> billRateComparator() {
		Comparator<Job> comparator = Comparator.comparing(Job::getBillRate);
		return comparator;
	}
}
