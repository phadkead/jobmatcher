package com;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bean.Job;
import com.controller.JobMatcherController;
import com.service.JobMatcherService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = JobmatcherApplication.class)
@WebAppConfiguration
public class JobMatcherContollerTest {
	
    private MockMvc mockMvc;
    
	@Mock
	private JobMatcherService jobMatcherService;
	
    @InjectMocks
    private JobMatcherController userController;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }
    
    @Test
    public void shouldReturnResultsForValidRequest() throws Exception{
    	 Job job = new Job();
    	 job.setJobId(1);
    	    Mockito.when(jobMatcherService.findBestJobsForWorker(0)).thenReturn(Arrays.asList(job));
    	    mockMvc.perform(
    	            get("/jobMatchingApplication/jobMatches")
    	                    .contentType(MediaType.APPLICATION_JSON)
    	                    .param("workerId", "0"))
    	            .andExpect(status().is(200));
    	    
    	    
    	    verify(jobMatcherService, times(1)).findBestJobsForWorker(0);
    }

}
