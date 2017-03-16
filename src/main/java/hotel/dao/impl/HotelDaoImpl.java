package hotel.dao.impl;

import hotel.dao.HotelDao;
import hotel.entity.HotelTblEntity;
import hotel.entity.ManagerTblEntity;
import hotel.entity.PlanTblEntity;
import hotel.type.HotelState;
import hotel.type.RoomType;
import hotel.vo.HotelPlanForm;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
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

    public List<Object[]> getAllHotelIncome() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select t.name, (t.todayIncome+t.totalIncome) as income from HotelTblEntity t where state <> :s ");
        query.setParameter("s", HotelState.APPLYING);
        List<Object[]> hotels = query.list();
        session.close();
        return hotels;
    }

    @Override
    public List<HotelTblEntity> getOpenHotels() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from HotelTblEntity where state = :open or state = :suspend ");
        query.setParameter("open", HotelState.OPEN);
        query.setParameter("suspend", HotelState.SUSPEND);
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

    @Override
    public HotelTblEntity addHotel(HotelTblEntity hotelTblEntity, int managerId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        ManagerTblEntity manager = session.get(ManagerTblEntity.class, managerId);
        hotelTblEntity.setManagerTblByManagerId(manager);
        Integer hotelId = (Integer) session.save(hotelTblEntity);
        HotelTblEntity savedHotel = session.get(HotelTblEntity.class, hotelId);
        //initialize the plans of the new hotel
        session.save(initPlan(RoomType.SINGLE, savedHotel));
        session.save(initPlan(RoomType.T_DOUBLE, savedHotel));
        session.save(initPlan(RoomType.TRIPLE, savedHotel));

        tx.commit();
        session.close();
        hotelTblEntity.setId(hotelId);
        return hotelTblEntity;
    }

    @Override
    public boolean updateHotelPlan(HotelPlanForm planForm) {
        int hotelId = Integer.parseInt(planForm.getHotelId());
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        HotelTblEntity entity = session.get(HotelTblEntity.class, hotelId);
        Hibernate.initialize(entity.getPlanTblsById());
        Collection<PlanTblEntity> planPOs = entity.getPlanTblsById();
        for (PlanTblEntity planPO : planPOs) {
            switch (planPO.getRoomType()) {
                case SINGLE:
                    planPO.setRoomCount(planForm.getSingleRoomCount());
                    planPO.setRoomPrice(planForm.getSingleRoomPrice());
                    break;
                case T_DOUBLE:
                    planPO.setRoomCount(planForm.getDoubleRoomCount());
                    planPO.setRoomPrice(planForm.getDoubleRoomPrice());
                    break;
                case TRIPLE:
                    planPO.setRoomCount(planForm.getTripleRoomCount());
                    planPO.setRoomPrice(planForm.getTripleRoomPrice());
                    break;
            }
            session.update(planPO);
        }
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public void updatePlanByPO(PlanTblEntity planPO) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.merge(planPO);
        tx.commit();
        session.close();
    }

    @Override
    public List<HotelTblEntity> getApplyHotels() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from HotelTblEntity where state = :apply ");
        query.setParameter("apply", HotelState.APPLYING);
        List<HotelTblEntity> hotels = query.list();
        for (HotelTblEntity po : hotels) {
            Hibernate.initialize(po.getManagerTblByManagerId());
        }
        session.close();
        return hotels;
    }

    @Override
    public void handleApplyHotels(HotelState state, int hotelId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        HotelTblEntity hotelPO = session.get(HotelTblEntity.class, hotelId);
        if (hotelPO != null) {
            if (state != null) {
                hotelPO.setState(state);
            }
            else {
                session.delete(hotelPO);
            }
        }
        tx.commit();
        session.close();
    }

    @Override
    public boolean jieSuanHotel(int hotelId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        HotelTblEntity po = session.get(HotelTblEntity.class, hotelId);
        if (po!=null){
            po.setTotalIncome(po.getTotalIncome()+po.getTodayIncome());
            po.setTodayIncome(0.0);
            session.update(po);
        }
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public List<HotelTblEntity> getHotelWithITodayncome() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from HotelTblEntity where todayIncome > 0 ");
        List<HotelTblEntity> hotels = query.list();
        session.close();
        return hotels;
    }

    private PlanTblEntity initPlan(RoomType type, HotelTblEntity belongedHotel) {
        PlanTblEntity roomPlan = new PlanTblEntity();
        roomPlan.setRoomCount(0);
        roomPlan.setRoomPrice(0.0);
        roomPlan.setRoomType(type);
        roomPlan.setHotelTblByHotelId(belongedHotel);
        return roomPlan;
    }
}
