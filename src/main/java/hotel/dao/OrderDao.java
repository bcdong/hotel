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

    public List<OrderTblEntity> getOrderByVip(int vipId);

    public List<OrderTblEntity> getOrderByVipAndState(int vipId, OrderState state);

    public Map<String,Object> getVipStatistic(int vipId);
}
