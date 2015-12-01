package com.vartanian.auction.model;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Created by super on 12/1/15.
 */
@Local
public interface FacadeLocal {
    String info();
}
