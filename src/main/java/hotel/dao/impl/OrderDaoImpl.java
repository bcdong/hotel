package hotel.dao.impl;

import hotel.dao.OrderDao;
import hotel.entity.HotelTblEntity;
import hotel.entity.OrderTblEntity;
import hotel.entity.VipTblEntity;
import hotel.type.OrderState;
import hotel.type.RoomType;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Zero on 2017/3/11.
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    private SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addOrder(OrderTblEntity orderPO) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(orderPO);
        tx.commit();
        session.close();
    }

    @Override
    public List<OrderTblEntity> getOrderByVip(int vipId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        VipTblEntity vip = session.load(VipTblEntity.class, vipId);
        Query query = session.createQuery("from OrderTblEntity where vipTblByVipId = :vipEntity ");
        query.setParameter("vipEntity", vip);
        List<OrderTblEntity> orders = query.list();
        tx.commit();
        session.close();
        return orders;
    }

    @Override
    public List<OrderTblEntity> getOrderByVipAndState(int vipId, OrderState state) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        VipTblEntity vip = session.load(VipTblEntity.class, vipId);
        Query query = session.createQuery("from OrderTblEntity where vipTblByVipId = :vipEntity and state = :s ");
        query.setParameter("vipEntity", vip);
        query.setParameter("s", state);
        List<OrderTblEntity> orders = query.list();
        tx.commit();
        session.close();
        return orders;
    }

    @Override
    public Map<String, Object> getVipStatistic(int vipId) {
        Map<String,Object> map = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createNativeQuery("select sum(cost) from order_tbl where vip_id = :vId ");
        query.setParameter("vId", vipId);
        List<Object> costList = query.list();
        if (costList.size()==0){
            map.put("totalCost",0);
        } else {
            map.put("totalCost",costList.get(0));
        }
        Query query1 = session.createNativeQuery("select count(*) from order_tbl where vip_id = :vId");
        query1.setParameter("vId", vipId);
        List<Object> cntList = query1.list();
        map.put("orderCount", cntList.get(0));
        tx.commit();
        session.close();
        return map;
    }

    @Override
    public boolean updateOrder(int orderId, OrderState state, String roomId) {
        boolean result = false;
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        OrderTblEntity po = session.get(OrderTblEntity.class, orderId);
        if (po != null) {
            if (state == OrderState.IN) {
                po.setRoomId(roomId);
            }
            po.setState(state);
            result = true;
        }
        if (state == OrderState.LEAVE) {
            HotelTblEntity hotel = po.getHotelTblByHotelId();
            RoomType roomType = po.getRoomType();
            Query query = session.createQuery("update PlanTblEntity set roomCount = roomCount+1 where hotelTblByHotelId = :hotelEntity and roomType = :rtype ");
            query.setParameter("hotelEntity", hotel);
            query.setParameter("rtype", roomType);
            query.executeUpdate();
        }
        tx.commit();
        session.close();
        return result;
    }

    @Override
    public List<OrderTblEntity> getOrderByHotel(int hotelId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        HotelTblEntity hotel = session.load(HotelTblEntity.class, hotelId);
        Query query = session.createQuery("from OrderTblEntity where hotelTblByHotelId = :hotelEntity ");
        query.setParameter("hotelEntity", hotel);
        List<OrderTblEntity> orders = query.list();
        tx.commit();
        session.close();
        return orders;
    }

    @Override
    public List<OrderTblEntity> getOrderByHotelAndState(int hotelId, OrderState state) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        HotelTblEntity hotel = session.load(HotelTblEntity.class, hotelId);
        Query query = session.createQuery("from OrderTblEntity where hotelTblByHotelId = :hotelEntity and state = :s ");
        query.setParameter("hotelEntity", hotel);
        query.setParameter("s", state);
        List<OrderTblEntity> orders = query.list();
        tx.commit();
        session.close();
        return orders;
    }
}
