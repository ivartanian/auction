package com.vartanian.auction.model;

import java.io.Serializable;

/**
 * Created by super on 12/2/15.
 */
public class Person implements Serializable{
    private String name;
    private String info;

    public Person() {
    }

    public Person(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
