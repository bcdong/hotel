package hotel.service;

import hotel.entity.HotelTblEntity;
import hotel.vo.HotelVO;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface HotelService {

    public List<HotelVO> getAllHotels();

    public HotelVO getHotel(String id);

    public HotelVO addHotel(HotelVO hotelVO, int managerId);

}
