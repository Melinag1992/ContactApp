package com.example.melinagonzalez.genius_plaza_melinag.model;

import java.io.Serializable;

public class Person  {



    private String job;
    private String name;

    public Person(){

    }

    public Person(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String get_name() {
        return name;
    }

    public void set_name(String last_name) {
        this.name = last_name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
