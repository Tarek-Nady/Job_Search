package com.tarek.job_search.entity;

import java.sql.Date;

/**
 * Entity class representing a Job.
 * Contains job-related information and methods to access and modify it.
 */
public class Job {
    private int id;
    private String title;
    private String description;
    private String category;
    private String location;
    private String status;
    private Date pdate = new Date(System.currentTimeMillis());

    public Job() {
    }

     /**
     * Parameterized constructor to create a job object with specified details.
     * @param id The job's ID.
     * @param title The job's title.
     * @param description The job's description.
     * @param category The job's category.
     * @param location The job's location.
     * @param status The job's status.
     * @param pdate The job's posting date.
     */

    public Job(int id, String title, String description, String category, String location, String status, Date pdate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.location = location;
        this.status = status;
        this.pdate = pdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getPdate() {
        return pdate.getTime();
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }
}
