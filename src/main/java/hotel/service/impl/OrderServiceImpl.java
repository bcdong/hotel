package hotel.service.impl;

import hotel.dao.OrderDao;
import hotel.entity.OrderTblEntity;
import hotel.entity.VipTblEntity;
import hotel.service.OrderService;
import hotel.type.OrderState;
import hotel.util.PO2VO;
import hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Zero on 2017/3/12.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private PO2VO po2VO;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, PO2VO po2VO) {
        this.orderDao = orderDao;
        this.po2VO = po2VO;
    }

    @Override
    public List<OrderVO> getOrderByVip(String vipId) {
        int iVipId = Integer.parseInt(vipId);
        List<OrderTblEntity> orderPOs = orderDao.getOrderByVip(iVipId);
        List<OrderVO> voList = new ArrayList<>();
        for (OrderTblEntity po : orderPOs) {
            voList.add(po2VO.orderPO2VO(po));
        }
        return voList;
    }

    @Override
    public List<OrderVO> getOrderByVipAndState(String vipId, String state) {
        int iVipId = Integer.parseInt(vipId);
        OrderState state1 = OrderState.str2Enum(state);
        List<OrderTblEntity> orderPOs = orderDao.getOrderByVipAndState(iVipId, state1);
        List<OrderVO> voList = new ArrayList<>();
        for (OrderTblEntity po : orderPOs) {
            voList.add(po2VO.orderPO2VO(po));
        }
        return voList;
    }

    @Override
    public Map<String, Object> getVipStatistic(String vipId) {
        int id = Integer.parseInt(vipId);
        return orderDao.getVipStatistic(id);
    }

    @Override
    public List<OrderVO> getOrderByHotel(String hotelId) {
        int iHotelId = Integer.parseInt(hotelId);
        List<OrderTblEntity> poList = orderDao.getOrderByHotel(iHotelId);
        List<OrderVO> voList = new ArrayList<>();
        for (OrderTblEntity po : poList) {
            voList.add(po2VO.orderPO2VO(po));
        }
        return voList;
    }

    @Override
    public List<OrderVO> getOrderByHotelAndState(String hotelId, String state) {
        int iHotelId = Integer.parseInt(hotelId);
        OrderState s = OrderState.str2Enum(state);
        List<OrderTblEntity> poList = orderDao.getOrderByHotelAndState(iHotelId, s);
        List<OrderVO> voList = new ArrayList<>();
        for (OrderTblEntity po : poList) {
            voList.add(po2VO.orderPO2VO(po));
        }
        return voList;
    }

    @Override
    public boolean updateOrderState(String orderId, String state) {
        return updateOrderState(orderId, state, "");
    }

    @Override
    public boolean updateOrderState(String orderId, String state, String roomId) {
        int iOrderId = Integer.parseInt(orderId);
        OrderState eState = OrderState.str2Enum(state);
        return orderDao.updateOrder(iOrderId, eState, roomId);
    }

    @Override
    public boolean cancelOrder(String orderId) {
        Integer iOrderId = Integer.parseInt(orderId);
        return orderDao.deleteOrder(iOrderId);
    }
}
