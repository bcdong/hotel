package hotel.service;

import hotel.entity.HotelTblEntity;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface HotelService {

    public List<HotelTblEntity> getAllHotels();

    public HotelTblEntity getHotel(String id);

}
