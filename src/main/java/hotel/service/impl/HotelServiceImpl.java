package hotel.service.impl;

import hotel.dao.HotelDao;
import hotel.entity.HotelTblEntity;
import hotel.service.HotelService;
import hotel.type.HotelState;
import hotel.util.PO2VO;
import hotel.vo.HotelPlanForm;
import hotel.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
@Service
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;
    private PO2VO po2VO;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao, PO2VO po2VO) {
        this.hotelDao = hotelDao;
        this.po2VO = po2VO;
    }

    public List<HotelVO> getAllHotels() {
        List<HotelTblEntity> poList =  hotelDao.getAllHotels();
        List<HotelVO> voList = new ArrayList<HotelVO>();
        for (HotelTblEntity po : poList) {
            voList.add(po2VO.hotelPO2VO(po));
        }
        return voList;
    }

    @Override
    public List<HotelVO> getOpenHotels() {
        List<HotelTblEntity> poList =  hotelDao.getOpenHotels();
        List<HotelVO> voList = new ArrayList<HotelVO>();
        for (HotelTblEntity po : poList) {
            voList.add(po2VO.hotelPO2VO(po));
        }
        return voList;
    }

    public HotelVO getHotel(String id) {
        HotelTblEntity hotel = null;
        if (id != null) {
            try{
                Integer hotelId = Integer.parseInt(id);
                hotel = hotelDao.getHotel(hotelId);
            } catch (NumberFormatException e) {
                //log
            }

        }
        return po2VO.hotelPO2VOWithPlan(hotel);
    }

    @Override
    public HotelVO addHotel(HotelVO hotelVO, int managerId) {
        HotelTblEntity po = new HotelTblEntity();
        po.setName(hotelVO.getName());
        po.setAddress(hotelVO.getAddress());
        po.setState(HotelState.APPLYING);
        HotelTblEntity savedPO = hotelDao.addHotel(po, managerId);
        HotelVO vo = po2VO.hotelPO2VO(savedPO);
        return vo;
    }

    @Override
    public boolean updatePlan(HotelPlanForm planForm) {
        return hotelDao.updateHotelPlan(planForm);
    }

    @Override
    public List<HotelVO> getApplyHotels() {
        List<HotelTblEntity> poList =  hotelDao.getApplyHotels();
        List<HotelVO> voList = new ArrayList<HotelVO>();
        for (HotelTblEntity po : poList) {
            voList.add(po2VO.hotelPO2VOWithManager(po));
        }
        return voList;
    }

    @Override
    public List<HotelVO> handleApply(String action, String hotelId) {
        //null means delete the hotel
        HotelState hotelState = null;
        if (action.equals("prove")) {
            hotelState = HotelState.OPEN;
        }
        int id = Integer.parseInt(hotelId);
        hotelDao.handleApplyHotels(hotelState, id);
        return getApplyHotels();
    }
}
