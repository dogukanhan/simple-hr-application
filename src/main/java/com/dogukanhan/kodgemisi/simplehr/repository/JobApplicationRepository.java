package com.dogukanhan.kodgemisi.simplehr.repository;

import com.dogukanhan.kodgemisi.simplehr.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication,Long> {}
