package hotel.service.impl;

import hotel.dao.HotelDao;
import hotel.entity.HotelTblEntity;
import hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
@Service
public class HotelServiceImpl implements HotelService {

    private HotelDao hotelDao;

    @Autowired
    public HotelServiceImpl(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    public List<HotelTblEntity> getAllHotels() {
        return hotelDao.getAllHotels();
    }
}
