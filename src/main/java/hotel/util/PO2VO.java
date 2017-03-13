package hotel.util;

import hotel.entity.*;
import hotel.type.RoomType;
import hotel.type.VipState;
import hotel.vo.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Service
public class PO2VO {

    private DecimalFormat df;
    private DateFormat dateFormat;

    public PO2VO() {
        String formatStr = "0000000";
        this.df = new DecimalFormat(formatStr);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    public VipVO vipPO2VO(VipTblEntity po) {
        if (po == null) {
            return null;
        }
        VipVO vo = new VipVO();
        vo.setId(df.format(po.getId()));
        vo.setName(po.getName());
        vo.setBalance(po.getBalance());
        vo.setLevel(po.getLevel());
        vo.setExperience(po.getExperience());
        vo.setScore(po.getScore());
        vo.setBankId(po.getBankId());
        vo.setUsername(po.getUsername());
        VipState state = po.getState();
        switch (state){
            case PRE_ACTIVE:vo.setState("未激活");break;
            case ACTIVE:vo.setState("正常");break;
            case SUSPEND:vo.setState("欠费暂停");break;
            case STOP:vo.setState("失效");break;
        }
        return vo;
    }

    public PlanVO planPO2VO(PlanTblEntity po) {
        if (po == null) {
            return null;
        }
        PlanVO vo = new PlanVO();
        vo.setRoomCount(po.getRoomCount());
        vo.setRoomPrice(po.getRoomPrice());
        RoomType roomType = po.getRoomType();
        switch (roomType) {
            case SINGLE:vo.setRoomType("单人间");break;
            case T_DOUBLE:vo.setRoomType("双人间");break;
            case TRIPLE:vo.setRoomType("三人间");break;
        }
        return vo;
    }

    public HotelVO hotelPO2VO(HotelTblEntity po) {
        if (po == null) {
            return null;
        }
        HotelVO vo = new HotelVO();
        vo.setId(String.valueOf(po.getId()));
        vo.setName(po.getName());
        vo.setAddress(po.getAddress());
        String state = "";
        switch (po.getState()) {
            case APPLYING:state = "申请中";break;
            case SUSPEND:state = "暂停营业";break;
            case CLOSE:state = "已关闭";break;
            case OPEN:state = "营业中";break;
        }
        vo.setState(state);

        return vo;
    }

    public HotelVO hotelPO2VOWithPlan(HotelTblEntity po) {
        HotelVO vo = hotelPO2VO(po);
        Collection<PlanTblEntity> planPOs = po.getPlanTblsById();
        if (planPOs != null && planPOs.size() > 0) {
            List<PlanVO> planVOS = new ArrayList<PlanVO>();
            for (PlanTblEntity planPO : po.getPlanTblsById()){
                planVOS.add(planPO2VO(planPO));
            }
            vo.setPlans(planVOS);
        }
        return vo;
    }

    public HotelVO hotelPO2VOWithManager(HotelTblEntity po) {
        HotelVO vo = hotelPO2VO(po);
        vo.setManagerName(po.getManagerTblByManagerId().getName());
        return vo;
    }

    public ManagerVO managerPO2VO(ManagerTblEntity po) {
        if (po == null) {
            return null;
        }
        ManagerVO vo = new ManagerVO();
        vo.setId(po.getId());
        vo.setName(po.getName());
        vo.setType(po.getType());
        Collection<HotelTblEntity> hotels = po.getHotelTblsById();
        if (hotels != null){
            Iterator<HotelTblEntity> iterator = hotels.iterator();
            if(iterator.hasNext()) {
                HotelTblEntity hotelPO = iterator.next();
                vo.setHotel(hotelPO2VO(hotelPO));
            }
        }
        return vo;
    }

    public OrderVO orderPO2VO(OrderTblEntity po) {
        OrderVO vo = new OrderVO();
        vo.setId(df.format(po.getId()));
        vo.setHotelId(df.format(po.getHotelTblByHotelId().getId()));
        vo.setHotelName(po.getHotelTblByHotelId().getName());
        vo.setFromTime(dateFormat.format(new Date(po.getFromTime().getTime())));
        vo.setToTime(dateFormat.format(new Date(po.getToTime().getTime())));
        String orderState;
        switch (po.getState()) {
            case BOOK:orderState = "已预订";break;
            case IN:orderState = "已入住";break;
            case LEAVE:
            default:orderState = "已离店";break;
        }
        vo.setState(orderState);
        vo.setCostBeforeDiscount(po.getCost());
        vo.setDiscount(0.0);
        vo.setRoomId(po.getRoomId());
        switch(po.getRoomType()) {
            case SINGLE:vo.setRoomType("单人间");break;
            case T_DOUBLE:vo.setRoomType("双人间");break;
            case TRIPLE:
                default:vo.setRoomType("三人间");
        }
        vo.setCustomer(po.getCustomer());
        switch (po.getPayMethod()) {
            case CASH:vo.setPayMethod("现金");break;
            case VIP_CARD:
                default:vo.setPayMethod("会员卡");break;
        }
        vo.setVipId(df.format(po.getVipTblByVipId().getId()));
        return vo;
    }
}
