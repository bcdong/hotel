package hotel.service.impl;

import hotel.dao.OrderDao;
import hotel.entity.OrderTblEntity;
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
        OrderState state1;
        switch (state) {
            case "BOOK":state1 = OrderState.BOOK;break;
            case "IN":state1 = OrderState.IN;break;
            case "LEAVE":default:state1 = OrderState.LEAVE;break;
        }
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
}
