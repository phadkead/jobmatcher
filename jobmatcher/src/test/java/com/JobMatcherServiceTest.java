package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.service.JobMatcherService;

import org.junit.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JobmatcherApplication.class)

public class JobMatcherServiceTest {
	@Autowired
	private MongoOperations mongoOps;
	
	@Autowired
	private JobMatcherService service;
	
	//TODO write before setup

	@Test
	public void shouldFindThreeBestJobs() {
		Assert.assertEquals(true, mongoOps.collectionExists("jobs"));
		service.findBestJobsForWorker(0);
		Assert.assertEquals(3, service.findBestJobsForWorker(0));
		
	}
}
