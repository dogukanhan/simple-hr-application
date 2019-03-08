package com.dogukanhan.kodgemisi.simplehr;

import com.dogukanhan.kodgemisi.simplehr.repository.JobApplicationRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(profiles ="dev")
@ContextConfiguration
public class SimplehrApplicationTests {

	@Autowired
	WebApplicationContext wac;

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	private MockMvc mvc;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).dispatchOptions(true).build();

	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testApplicantJobIsNotSecure() throws Exception {
		mvc.perform(get("/applicant/job")).andExpect(status().is(200));
	}

	@Test
	public void testEmptyPostIsAccepted()
			throws Exception {
		long count = jobApplicationRepository.count();
		mvc.perform(post("/applicant/job/detail/1")).andExpect(status().is(200));
		long afterCount = jobApplicationRepository.count();
		assertEquals(count,afterCount);
	}

}
