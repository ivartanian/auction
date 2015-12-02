package com.vartanian.auction.model;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Stateful;

/**
 * Created by vartanian on 02.12.2015.
 */
@Stateful
public class StateFull {

    private long id;

    public StateFull() {
        id = System.currentTimeMillis();
        System.out.println("-------------StateFull() id = " + id);
    }

    public String info() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("--------------StateFull.info() id = " + id);
        return "--------------StateFull.info() id = " + id;
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("--------------StateFull.preDestroy() id = " + id);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("--------------StateFull.postConstruct() id = " + id);
    }

    @PostActivate
    public void postActivate() {
        System.out.println("--------------StateFull.postActivate() id = " + id);
    }

    @PrePassivate
    public void prePassivate() {
        System.out.println("--------------StateFull.prePassivate() id = " + id);
    }
}
