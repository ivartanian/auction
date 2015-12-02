package com.vartanian.auction.model;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by super on 12/2/15.
 */
//@LocalBean
@Stateless
public class BeanWithoutInterface {

    private List<String> list;

    @PostConstruct
    public void postConstruct(){
        list = new ArrayList<>();
    }

    public String info() {
        return "BeanWithoutInterface.info()";
    }

    public int addItem(String item) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list.add(item);
        return list.size();
    }

}
