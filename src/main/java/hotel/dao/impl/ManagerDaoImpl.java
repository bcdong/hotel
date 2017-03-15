package hotel.dao.impl;

import hotel.dao.ManagerDao;
import hotel.entity.ManagerTblEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/3.
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    private SessionFactory sessionFactory;

    @Autowired
    public ManagerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ManagerTblEntity getManagerByUsername(String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ManagerTblEntity where username = :uname ");
        query.setParameter("uname", username);
        List<ManagerTblEntity> managerList = query.list();
        for (ManagerTblEntity managerTblEntity : managerList) {
            Hibernate.initialize(managerTblEntity.getHotelTblsById());
        }
        session.close();
        if (managerList.size() > 0) {
            return managerList.get(0);
        }
        return null;
    }

    public Integer addManager(ManagerTblEntity manager) {
        Integer result = -1;
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from ManagerTblEntity where username = :uname ");
        query.setParameter("uname", manager.getUsername());
        List list = query.list();
        if (list.size() == 0) {
            Integer id = (Integer) session.save(manager);
            result = id;
        }
        tx.commit();
        session.close();
        return result;
    }
}
