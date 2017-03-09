package hotel.util;

import hotel.entity.HotelTblEntity;
import hotel.entity.ManagerTblEntity;
import hotel.entity.PlanTblEntity;
import hotel.entity.VipTblEntity;
import hotel.type.RoomType;
import hotel.type.VipState;
import hotel.vo.HotelVO;
import hotel.vo.ManagerVO;
import hotel.vo.PlanVO;
import hotel.vo.VipVO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Service
public class PO2VO {

    private DecimalFormat df;

    public PO2VO() {
        String formatStr = "0000000";
        this.df = new DecimalFormat(formatStr);
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
}
