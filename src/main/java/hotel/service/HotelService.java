package hotel.service;

import hotel.entity.HotelTblEntity;
import hotel.vo.HotelPlanForm;
import hotel.vo.HotelVO;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface HotelService {

    public List<HotelVO> getAllHotels();

    public List<HotelVO> getOpenHotels();

    public HotelVO getHotel(String id);

    public HotelVO addHotel(HotelVO hotelVO, int managerId);

    public boolean updatePlan(HotelPlanForm planForm);

    public List<HotelVO> getApplyHotels();

    public List<HotelVO> handleApply(String action, String hotelId);

}
