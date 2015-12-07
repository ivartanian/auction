package com.vartanian.auction.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by super on 12/2/15.
 */
@Entity
@Table(name = "persons")
@Access(AccessType.FIELD)
public class Person implements Serializable{

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="info")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
