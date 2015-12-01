package com.vartanian.auction.model;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created by super on 12/1/15.
 */
@Stateless(mappedName = "F1")
@LocalBean
public class Facade implements FacadeLocal, FacadeRemote{

    private long id;

    public Facade() {
        id = System.currentTimeMillis();
        System.out.println("Facade() id = " + id);
    }

    @Override
    public String info() {
        System.out.println("info() id = " + id);
        return "Hello";
    }
}
