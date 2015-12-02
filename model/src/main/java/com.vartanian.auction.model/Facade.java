package com.vartanian.auction.model;

import javax.annotation.Resource;
import javax.ejb.*;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * Created by super on 12/1/15.
 */
@Stateless
public class Facade implements FacadeLocal{

    private TimerHandle timerHandle;

    @Resource
    private SessionContext sessionContext;

    private long id;

    public Facade() {
        id = System.currentTimeMillis();
        System.out.println("Facade() id = " + id);
    }

    @Override
    public String info() {
        System.out.println("info() id = " + id);
        return "Hello; sess = " + sessionContext + ";;;";
    }

    @Override
    public void startJob() {
        if (timerHandle == null){
            timerHandle = sessionContext.getTimerService().createTimer(new Date(), 2000, "TimerJob").getHandle();
        }
    }

    @Override
    public void stopJob() {
        if (timerHandle != null){
            timerHandle.getTimer().cancel();
            timerHandle = null;
        }
    }

    @Asynchronous
    @Override
    public void invokeAsynch() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------made invokeAsynch()");
    }

    @Asynchronous
    @Override
    public Future<Person> invokeAsynchWithFuture() {
        AsyncResult<Person> objectAsyncResult = new AsyncResult<>(new Person("Igor", "Test"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------made invokeAsynchWithFuture()");
        return objectAsyncResult;
    }

    @Timeout
    public void invokeJob(Timer timer) {
        if (timer.getInfo().equals("TimerJob")){
            System.out.println("-------------- " + new Date());
        }
    }
}
