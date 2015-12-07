package com.vartanian.auction.model;

import com.vartanian.auction.model.utils.TxStatus;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * Created by super on 12/1/15.
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Facade implements FacadeLocal{

    private TimerHandle timerHandle;

    @PersistenceContext(unitName = "auction")
    private EntityManager em;

    @Resource
    private SessionContext sessionContext;
//    @Resource
//    private UserTransaction userTransaction;

    private long id;

    public Facade() {
        id = System.currentTimeMillis();
        System.out.println("Facade() id = " + id);
    }

    @Override
    public String info() {
//        System.out.println("info() id = " + id);

//        System.out.println(TxStatus.getStatus(sessionContext.getUserTransaction()));

        int i = executeSQL("CREATE TABLE test (id INTEGER PRIMARY KEY)");

//        System.out.println(TxStatus.getStatus(sessionContext.getUserTransaction()));

        return String.valueOf(i);
    }

    public int executeSQL(String sql) {

        System.out.println(TxStatus.getStatus(sessionContext.getUserTransaction()));

        int i = em.createNativeQuery(sql).executeUpdate();

        System.out.println(TxStatus.getStatus(sessionContext.getUserTransaction()));

        return i;
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

    @Override
    public void createPerson(Person person) {
        em.persist(person);
    }

    @Override
    public void updatePerson(Long id, String name, String info) {
        Person person = em.find(Person.class, id);
        person.setInfo(info);
        person.setName(name);
        em.merge(person);
    }

    @Timeout
    public void invokeJob(Timer timer) {
        if (timer.getInfo().equals("TimerJob")){
            System.out.println("-------------- " + new Date());
        }
    }
}
