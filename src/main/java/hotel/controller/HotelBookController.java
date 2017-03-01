package hotel.controller;

import hotel.entity.HotelTblEntity;
import hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 * This controller handles the book requests and hotel information
 */
@Controller
@RequestMapping(value = "/hotel")
public class HotelBookController {
    private HotelService hotelService;

    @Autowired
    public HotelBookController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String listHotelPlans(Model model) {
        List<HotelTblEntity> hotels = hotelService.getAllHotels();
        model.addAttribute("hotes", hotels);
        return "hotelList";
    }
}
