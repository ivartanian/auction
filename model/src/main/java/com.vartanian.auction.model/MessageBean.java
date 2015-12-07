package com.vartanian.auction.model;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by super on 12/7/15.
 */
@MessageDriven(name = "MessageEJB", mappedName = "jms/testQueue")
public class MessageBean implements MessageListener {

    @PersistenceContext(unitName = "auction")
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
        try {
            String text = ((TextMessage) message).getText();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + text + " " + em);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
