package hotel.dao;

import hotel.entity.OrderTblEntity;
import hotel.type.OrderState;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Zero on 2017/3/11.
 */
public interface OrderDao {
    public void addOrder(OrderTblEntity orderPO);

    public OrderTblEntity getOrderById(int orderId);

    public List<OrderTblEntity> getOrderByVip(int vipId);

    public List<OrderTblEntity> getOrderByVipAndState(int vipId, OrderState state);

    public Map<String,Object> getVipStatistic(int vipId);

    public boolean updateOrder(int orderId, OrderState state, String roomId);

    public List<OrderTblEntity> getOrderByHotel(int hotelId);

    public List<OrderTblEntity> getOrderByHotelAndState(int hotelId, OrderState state);

    public boolean deleteOrder(int orderId);

    public int getOrderCount(int hotelId);

    public List<Object[]> getHotelLiveCount();
}
