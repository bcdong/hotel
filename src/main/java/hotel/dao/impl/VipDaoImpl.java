package hotel.dao.impl;

import hotel.dao.VipDao;
import hotel.entity.VipTblEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Repository
public class VipDaoImpl implements VipDao {

    private SessionFactory sessionFactory;

    @Autowired
    public VipDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public VipTblEntity addVip(VipTblEntity vipTblEntity) {
        Session session = sessionFactory.openSession();
        Integer id  = (Integer) session.save(vipTblEntity);
        vipTblEntity.setId(id);
        session.close();
        return vipTblEntity;
    }

    public VipTblEntity getVipById(int id) {
        Session session = sessionFactory.openSession();
        VipTblEntity vip = session.get(VipTblEntity.class, id);
        session.close();
        return vip;
    }

    public VipTblEntity getVipByUsername(String username) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from VipTblEntity where username = :uname ");
        query.setParameter("uname", username);
        List<VipTblEntity> vips = query.list();
        session.close();
        if (vips.size() > 0){
            return vips.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean updateVip(VipTblEntity entity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        VipTblEntity ppo = (VipTblEntity) session.merge(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean updatePassword(int id, String oldPass, String newPass) {
        boolean result = false;
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        VipTblEntity po = session.get(VipTblEntity.class, id);
        if (po!=null && po.getPassword().equals(oldPass)) {
            po.setPassword(newPass);
            session.update(po);
            result = true;
        }
        tx.commit();
        session.close();
        return result;
    }
}
