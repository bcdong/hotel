package hotel.timer;

import hotel.type.VipState;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Mr.Zero on 2017/3/15.
 */
@Component("timer")
public class BalanceCheckTimer {

    private SessionFactory sessionFactory;

    @Autowired
    public BalanceCheckTimer(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void checkBalance() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Timestamp lastYear = new Timestamp(new Date().getTime()-((long)1000*60*60*24*365));
        Query query = session.createQuery("update VipTblEntity set state = :chge where state = :stt and balance = 0 and chargeDate <= :d ");
        query.setParameter("chge", VipState.SUSPEND);
        query.setParameter("stt", VipState.ACTIVE);
        query.setParameter("d", lastYear);
        query.executeUpdate();

        query.setParameter("chge", VipState.STOP);
        query.setParameter("stt", VipState.SUSPEND);
        query.setParameter("d", lastYear);
        query.executeUpdate();

        tx.commit();
        session.close();
    }
}
