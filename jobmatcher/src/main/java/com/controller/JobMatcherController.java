package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.Job;
import com.service.JobMatcherService;

/**
 * 
 * @author aditiphadke
 *
 */
@RestController
@RequestMapping("/jobMatchingApplication")
public class JobMatcherController {

	@Autowired
	private JobMatcherService jobMatcherService;

	private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherController.class);

	@RequestMapping("/jobMatches")
	public List<Job> getJobs(@RequestParam(required = true) Integer workerId) {
		LOGGER.info("Executing job matcher to find best jobs for workerId: {}", workerId);
		return jobMatcherService.findBestJobsForWorker(workerId);
	}
}
