package com.dogukanhan.kodgemisi.simplehr.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
public class JobListing implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=5, max = 50)
    private String title;

    @Size(min=10,max=4096)
    private String description;

    private Date lastApplication;

    @Min(1)
    private Integer hireCount;

    private int totalApply;

    @OneToMany(mappedBy = "jobListing")
    private List<JobApplication> applicationList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastApplication() {
        return lastApplication;
    }

    public void setLastApplication(Date lastApplication) {
        this.lastApplication = lastApplication;
    }

    public Integer getHireCount() {
        return hireCount;
    }

    public void setHireCount(Integer hireCount) {
        this.hireCount = hireCount;
    }

    public int getTotalApply() {
        return totalApply;
    }

    public void setTotalApply(int totalApply) {
        this.totalApply = totalApply;
    }

    public List<JobApplication> getApplicationList() {
        return applicationList;
    }

    public void setApplicationList(List<JobApplication> applicationList) {
        this.applicationList = applicationList;
    }
}
