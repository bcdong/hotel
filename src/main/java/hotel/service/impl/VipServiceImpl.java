package hotel.service.impl;

import hotel.dao.HotelDao;
import hotel.dao.OrderDao;
import hotel.dao.VipDao;
import hotel.entity.HotelTblEntity;
import hotel.entity.OrderTblEntity;
import hotel.entity.PlanTblEntity;
import hotel.entity.VipTblEntity;
import hotel.service.HotelService;
import hotel.service.VipLevelStrategy;
import hotel.service.VipService;
import hotel.type.*;
import hotel.util.PO2VO;
import hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Service
public class VipServiceImpl implements VipService {

    private VipDao vipDao;
    private PO2VO po2VO;
    private HotelService hotelService;
    private HotelDao hotelDao;
    private DateFormat df;
    private VipLevelStrategy levelStrategy;
    private OrderDao orderDao;

    @Autowired
    public VipServiceImpl(VipDao vipDao, PO2VO po2VO, HotelService hotelService, HotelDao hotelDao, VipLevelStrategy levelStrategy, OrderDao orderDao) {
        this.vipDao = vipDao;
        this.po2VO = po2VO;
        this.hotelService = hotelService;
        this.hotelDao = hotelDao;
        this.levelStrategy = levelStrategy;
        this.orderDao = orderDao;
        this.df = new SimpleDateFormat("yyyy-MM-dd");
    }

    public VipVO register(VipVO vipVO) {
        VipTblEntity vipTblEntity = vipDao.getVipByUsername(vipVO.getUsername());
        if (vipTblEntity == null) {
            VipTblEntity po = new VipTblEntity();
            po.setName(vipVO.getName());
            po.setBalance(0.0);
            po.setLevel(1);
            po.setExperience(0);
            po.setScore(0);
            po.setState(VipState.PRE_ACTIVE);
            po.setBankId(vipVO.getBankId());
            po.setUsername(vipVO.getUsername());
            po.setPassword(vipVO.getPassword());
            VipTblEntity newVip = vipDao.addVip(po);
            return po2VO.vipPO2VO(newVip);
        }
        return null;
    }

    public VipVO login(String username, String password) {
        VipTblEntity vip = vipDao.getVipByUsername(username);
        if ((vip != null) && (vip.getState()!=VipState.STOP) && (vip.getPassword().equals(password))){
            return po2VO.vipPO2VO(vip);
        }
        else {
            return null;
        }
    }

    @Override
    public VipVO getVipById(String vipId) {
        int id = Integer.parseInt(vipId);
        VipTblEntity vip = vipDao.getVipById(id);
        return po2VO.vipPO2VO(vip);
    }

    @Override
    public VipVO update(VipBasicInfo vipBasicInfo) {
        int id = Integer.parseInt(vipBasicInfo.getId());
        VipTblEntity po = vipDao.getVipById(id);
        if (po!=null) {
            po.setName(vipBasicInfo.getName());
            po.setBankId(vipBasicInfo.getBankId());
            vipDao.updateVip(po);
        }
        return po2VO.vipPO2VO(po);
    }

    @Override
    public boolean changePassword(String id, String oldPass, String newPass) {
        int vipId = Integer.parseInt(id);
        return vipDao.updatePassword(vipId, oldPass, newPass);
    }

    @Override
    public VipVO charge(String id, double money) {
        int vipId = Integer.parseInt(id);
        VipTblEntity po = vipDao.getVipById(vipId);
        if (po!=null) {
            //此处调用银行api来扣钱
            if (money >= 1000 && po.getState() == VipState.PRE_ACTIVE) {
                po.setState(VipState.ACTIVE);
            }
            double balance = po.getBalance()+money;
            po.setBalance(balance);
            vipDao.updateVip(po);
            return po2VO.vipPO2VO(po);
        }
        return null;
    }

    @Override
    public boolean stopVip(String id) {
        int vipId = Integer.parseInt(id);
        VipTblEntity po = vipDao.getVipById(vipId);
        if (po!=null && po.getState()!=VipState.STOP) {
            po.setState(VipState.STOP);
            vipDao.updateVip(po);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public VipVO dealScore(String id, int score) {
        int vipId = Integer.parseInt(id);
        VipTblEntity po = vipDao.getVipById(vipId);
        if (po!=null) {
            if ((score>0) && (score<=po.getScore())) {
                double balance = po.getBalance();
                po.setScore(po.getScore()-score);
                po.setBalance(balance+score/100);
                vipDao.updateVip(po);
                return po2VO.vipPO2VO(po);
            }
        }
        return null;
    }

    @Override
    public OrderVO bookHotel(String hotelId, String roomType, Date fromTime, Date toTime, String vipId, String customer) {
        OrderVO orderVO = new OrderVO();
        HotelVO hotelVO = hotelService.getHotel(hotelId);
        for (PlanVO planVO : hotelVO.getPlans()) {
            if (planVO.getRoomType().equals(roomType)){
                int days = (int) ((toTime.getTime()-fromTime.getTime())/(1000*60*60*24));
                orderVO.setCostBeforeDiscount(planVO.getRoomPrice()*days);
                break;
            }
        }

        orderVO.setHotelId(hotelId);
        orderVO.setHotelName(hotelVO.getName());
        orderVO.setRoomType(roomType);
        orderVO.setCustomer(customer);
        orderVO.setFromTime(df.format(fromTime));
        orderVO.setToTime(df.format(toTime));
        orderVO.setVipId(vipId);

        int iVipId = Integer.parseInt(vipId);
        VipTblEntity vipPO = vipDao.getVipById(iVipId);
        int vipLevel = vipPO.getLevel();
        double discount = orderVO.getCostBeforeDiscount()*0.05*(vipLevel>5?5:vipLevel);
        orderVO.setDiscount(discount);
        return orderVO;
    }

    @Override
    public OrderResult confirmOrder(OrderVO orderVO) {
        PayMethod payMethod = orderVO.getPayMethod().equals("VIP_CARD")?PayMethod.VIP_CARD:PayMethod.CASH;

        //酒店房间不足，订单失败
        int hotelId = Integer.parseInt(orderVO.getHotelId());
        HotelTblEntity hotelPO = hotelDao.getHotel(hotelId);
        RoomType type = transferRoomType(orderVO.getRoomType());
        PlanTblEntity orderPlanPO = null;
        for (PlanTblEntity planPO : hotelPO.getPlanTblsById()) {
            if (planPO.getRoomType() == type) {
                if (planPO.getRoomCount()<=0) {
                    return OrderResult.LACK_ROOM;
                } else { //money enough
                    planPO.setRoomCount(planPO.getRoomCount()-1);
                    planPO.setHotelTblByHotelId(hotelPO);
                    orderPlanPO = planPO;
                }
                break;
            }
        }


        int vipId = Integer.parseInt(orderVO.getVipId());
        VipTblEntity vipPO = vipDao.getVipById(vipId);
        double cost = orderVO.getCostBeforeDiscount()-orderVO.getDiscount();
        if (payMethod==PayMethod.VIP_CARD) {
            if (cost > vipPO.getBalance()) {
                //金额不足，订单失败，不存储
                return OrderResult.LACK_MONEY;
            } else {
                //扣除vip卡余额，存储订单
                vipPO.setBalance(vipPO.getBalance()-cost);
            }
        }
        else {
            //现金付款需要调用外部接口，例如支付宝、银行卡等
            //如果支付失败，则return失败
        }

        vipPO.setScore((int) (vipPO.getScore()+cost));
        vipPO.setExperience((int) (vipPO.getExperience()+cost));
        vipPO.setLevel(levelStrategy.experience2Level(vipPO.getExperience()));
        vipDao.updateVip(vipPO);
        hotelDao.updatePlanByPO(orderPlanPO);
        //存储订单
        OrderTblEntity orderPO = orderVO2PO(orderVO, payMethod, vipPO, hotelPO);
        orderDao.addOrder(orderPO);
        return OrderResult.SUCCESS;
    }

    private RoomType transferRoomType(String ch) {
        RoomType en;
        switch (ch) {
            case "单人间": en=RoomType.SINGLE;break;
            case "双人间": en = RoomType.T_DOUBLE;break;
            default: en = RoomType.TRIPLE;
        }
        return en;
    }

    private OrderTblEntity orderVO2PO (OrderVO vo, PayMethod payMethod, VipTblEntity vipPO, HotelTblEntity hotelPO) {
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
        po.setState(OrderState.BOOK);
        po.setCost(vo.getCostBeforeDiscount()-vo.getDiscount());
        po.setRoomId("TBD");
        RoomType roomType;
        switch (vo.getRoomType()) {
            case "单人间": roomType = RoomType.SINGLE;break;
            case "双人间": roomType = RoomType.T_DOUBLE;break;
            default:roomType = RoomType.TRIPLE;
        }
        po.setRoomType(roomType);
        po.setCustomer(vo.getCustomer());
        po.setPayMethod(payMethod);
        po.setVipTblByVipId(vipPO);
        po.setHotelTblByHotelId(hotelPO);
        return po;
    }
}
