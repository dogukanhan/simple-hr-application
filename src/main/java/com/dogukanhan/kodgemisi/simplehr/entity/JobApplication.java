package com.dogukanhan.kodgemisi.simplehr.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=2,max=30)
    private String name;

    @Size(min = 2 , max = 20)
    private String surname;

    @Size(min=5,max=60)
    @Email
    private String email;

    @Size(min=15,max=300)
    private String address;

    @Size(min=10,max=64)
    private String resumeUrl;

    @Size(min=25,max=350)
    private String thoughtsOnJob;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="JOB_LISTING")
    private JobListing jobListing;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }


    public String getThoughtsOnJob() {
        return thoughtsOnJob;
    }

    public void setThoughtsOnJob(String thoughtsOnJob) {
        this.thoughtsOnJob = thoughtsOnJob;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public JobListing getJobListing() {
        return jobListing;
    }

    public void setJobListing(JobListing jobListing) {
        this.jobListing = jobListing;
    }
}
