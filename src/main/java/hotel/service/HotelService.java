package hotel.service;

import hotel.entity.HotelTblEntity;
import hotel.vo.HotelIncomeVO;
import hotel.vo.HotelPlanForm;
import hotel.vo.HotelVO;

import java.util.List;
import java.util.Map;

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

    public List<HotelIncomeVO> getHotelIncomes();

    public List<HotelIncomeVO> jieSuanHotel(String hotelId);

    public Map<String, Object> getHotelStatistic(String hotelId);

    public List<Object[]> getHotelLiveStatus();
}
