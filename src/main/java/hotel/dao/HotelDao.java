package hotel.dao;

import hotel.entity.HotelTblEntity;
import hotel.entity.PlanTblEntity;
import hotel.type.HotelState;
import hotel.vo.HotelPlanForm;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface HotelDao {

    public HotelTblEntity getHotel(int id);

    public List<HotelTblEntity> getAllHotels();

    public List<HotelTblEntity> getOpenHotels();

    public HotelTblEntity addHotel(HotelTblEntity hotelTblEntity, int managerId);

    public boolean updateHotelPlan(HotelPlanForm planForm);

    public void updatePlanByPO(PlanTblEntity planPO);

    public List<HotelTblEntity> getApplyHotels();

    public void handleApplyHotels(HotelState state, int hotelId);

    public boolean jieSuanHotel(int hotelId);

    public List<HotelTblEntity> getHotelWithITodayncome();
}
