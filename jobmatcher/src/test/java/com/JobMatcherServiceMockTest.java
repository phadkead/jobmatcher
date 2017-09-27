package com;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.mongodb.core.MongoOperations;

import com.bean.Job;
import com.bean.Worker;
import com.bean.Worker.JobSearchAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.JobMatcherService;

@RunWith(MockitoJUnitRunner.class)
public class JobMatcherServiceMockTest {
	@Mock
	private JobMatcherService jobService;

	@Mock
	private MongoOperations mongoOps;

	// @Mock
	// private RestInvoker restInvoker;

	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldFindBestJobsForValidWorker() {
		Mockito.when(jobService.findWorkerByWorkerId(0)).thenReturn(createMockWorker());
		List<? extends GeoResult<Job>> jobs = new ArrayList<>();
		
		GeoResults<Job> geojobs = new GeoResults<Job>(jobs, new Distance(5));
		//TODO add mock results
		//Mockito.when(mongoOps.geoNear(Mockito.any(NearQuery.class), Job.class, Mockito.anyString())).thenReturn(geojobs);
	}

	private static Worker createMockWorker() {
		Worker worker = new Worker();
		worker.setJobSearchAddress(new JobSearchAddress("km", 30, 13.971284, 49.782281));
		worker.setHasDriversLicense(false);
		return worker;
	}
}
