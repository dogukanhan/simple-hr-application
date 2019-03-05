package com.dogukanhan.kodgemisi.simplehr.dev;

import com.dogukanhan.kodgemisi.simplehr.entity.JobListing;
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

    @EventListener(ApplicationReadyEvent.class)
    public void prepairTestDatas(){
        JobListing jsr = new JobListing();
                    jsr.setTitle("test title");
                    jsr.setDescription("This is a desc for a job");
                    jsr.setLastApplication(Date.valueOf(LocalDate.now()));
                    jsr.setHireCount((short)30);
                    jobListingRepository.save(jsr);

    }

}
