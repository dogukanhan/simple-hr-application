package com.dogukanhan.kodgemisi.simplehr.repository;

import com.dogukanhan.kodgemisi.simplehr.entity.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobListingRepository extends JpaRepository<JobListing,Long> {
}
