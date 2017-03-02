package hotel.dao;

import hotel.entity.HotelTblEntity;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface HotelDao {

    public HotelTblEntity getHotel(int id);

    public List<HotelTblEntity> getAllHotels();

}
