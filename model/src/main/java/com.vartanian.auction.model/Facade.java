package com.vartanian.auction.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created by super on 12/1/15.
 */
@Stateless
public class Facade implements FacadeLocal{

    private long id;

    public Facade() {
        id = System.currentTimeMillis();
        System.out.println("Facade() id = " + id);
    }

    @Override
    public String info() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("info() id = " + id);
        return "Hello";
    }
}
