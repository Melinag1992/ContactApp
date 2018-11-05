package com.example.melinagonzalez.genius_plaza_melinag.model;

import java.io.Serializable;

public class Person  {


    private String last_name;
    private String job;

    public Person(String last_name, String job) {
        this.last_name = last_name;
        this.job = job;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
