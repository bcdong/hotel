package hotel.dao.impl;

import hotel.dao.VipDao;
import hotel.entity.VipTblEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
        VipTblEntity vipPO = (VipTblEntity) session.save(vipTblEntity);
        session.close();
        return vipPO;
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
}
