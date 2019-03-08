package com.dogukanhan.kodgemisi.simplehr.dev;

import com.dogukanhan.kodgemisi.simplehr.entity.JobApplication;
import com.dogukanhan.kodgemisi.simplehr.entity.JobListing;
import com.dogukanhan.kodgemisi.simplehr.repository.JobApplicationRepository;
import com.dogukanhan.kodgemisi.simplehr.repository.JobListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Profile("dev")
public class TestDataService {

    @Autowired
    private JobListingRepository jobListingRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void prepairTestDatas(){
        JobListing jsr = new JobListing();
                    jsr.setTitle("test title");
                    jsr.setDescription("This is a desc for a job");
                    jsr.setLastApplication(Date.valueOf(LocalDate.now()));
                    jsr.setHireCount(30);
                    jobListingRepository.save(jsr);
        JobApplication jobApplication = new JobApplication();
                       jobApplication.setAddress("asdfadsfnadflkajdsfkjaklsdfja jasdlfjaskd jasdlfjadfljasdfkljadflajsd");
                       jobApplication.setJobListing(jsr);
                       jobApplication.setEmail("123@123.com");
                       jobApplication.setSurname("HAN");
                       jobApplication.setName("Dogukan");
                       jobApplication.setThoughtsOnJob("This is toughts on job toghs on ladjfajkdfjadsf dads");
                       jobApplication.setResumeUrl("f606e7b3-c8cd-4465-a594-4b1772ae0776.pdf");
                       jobApplicationRepository.save(jobApplication);
    }

}
