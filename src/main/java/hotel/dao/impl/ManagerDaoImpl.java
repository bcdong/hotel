package hotel.dao.impl;

import hotel.dao.ManagerDao;
import hotel.entity.ManagerTblEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

    public ManagerTblEntity addManager(ManagerTblEntity manager) {
        Session session = sessionFactory.openSession();
        Integer id = (Integer) session.save(manager);
        session.close();
        manager.setId(id);
        return manager;
    }
}
