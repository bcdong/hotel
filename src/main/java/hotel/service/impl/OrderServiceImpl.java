package hotel.service.impl;

import hotel.dao.HotelDao;
import hotel.dao.OrderDao;
import hotel.entity.HotelTblEntity;
import hotel.entity.OrderTblEntity;
import hotel.entity.PlanTblEntity;
import hotel.service.OrderService;
import hotel.type.OrderState;
import hotel.type.PayMethod;
import hotel.type.RoomType;
import hotel.util.PO2VO;
import hotel.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Zero on 2017/3/12.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private HotelDao hotelDao;
    private PO2VO po2VO;
    private DateFormat df;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, HotelDao hotelDao, PO2VO po2VO) {
        this.orderDao = orderDao;
        this.hotelDao = hotelDao;
        this.po2VO = po2VO;
        this.df = new SimpleDateFormat("yyyy-MM-dd");
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

    @Override
    public boolean addOrder(OrderVO vo) {
        OrderTblEntity po = new OrderTblEntity();
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate  = df.parse(vo.getFromTime());
            toDate = df.parse(vo.getToTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        po.setFromTime(new Timestamp(fromDate.getTime()));
        po.setToTime(new Timestamp(toDate.getTime()));
        po.setState(OrderState.IN);
        po.setCost(vo.getCostBeforeDiscount());
        po.setRoomId(vo.getRoomId());
        RoomType roomType;
        switch (vo.getRoomType()) {
            case "单人间": roomType = RoomType.SINGLE;break;
            case "双人间": roomType = RoomType.T_DOUBLE;break;
            default:roomType = RoomType.TRIPLE;
        }
        po.setRoomType(roomType);
        po.setCustomer(vo.getCustomer());
        po.setPayMethod(PayMethod.CASH);
        po.setVipTblByVipId(null);
        int hotelId = Integer.parseInt(vo.getHotelId());
        HotelTblEntity hotelPO = hotelDao.getHotel(hotelId);
        po.setHotelTblByHotelId(hotelPO);
        orderDao.addOrder(po);

        for (PlanTblEntity planPO : hotelPO.getPlanTblsById()) {
            if (roomType == planPO.getRoomType()){
                planPO.setRoomCount(planPO.getRoomCount()-1);
                planPO.setHotelTblByHotelId(hotelPO);
                hotelDao.updatePlanByPO(planPO);
                break;
            }
        }
        hotelPO.setTodayIncome(hotelPO.getTodayIncome()+vo.getCostBeforeDiscount());
        hotelDao.updateHotel(hotelPO);
        return true;
    }
}
