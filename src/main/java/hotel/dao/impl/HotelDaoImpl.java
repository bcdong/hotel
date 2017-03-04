package hotel.dao.impl;

import hotel.dao.HotelDao;
import hotel.entity.HotelTblEntity;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
@Repository
public class HotelDaoImpl implements HotelDao{

    private SessionFactory sessionFactory;

    @Autowired
    public HotelDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<HotelTblEntity> getAllHotels() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from HotelTblEntity ");
        List<HotelTblEntity> hotels = query.list();
        session.close();
        return hotels;
    }

    public HotelTblEntity getHotel(int id) {
        Session session = sessionFactory.openSession();
        HotelTblEntity entity = session.get(HotelTblEntity.class, id);
        Hibernate.initialize(entity.getPlanTblsById());
        session.close();
        return entity;
    }

}
