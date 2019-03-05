package com.dogukanhan.kodgemisi.simplehr.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

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

    private short hireCount;

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

    public short getHireCount() {
        return hireCount;
    }

    public void setHireCount(short hireCount) {
        this.hireCount = hireCount;
    }
}
