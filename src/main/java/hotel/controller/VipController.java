package hotel.controller;

import hotel.service.VipService;
import hotel.vo.LoginForm;
import hotel.vo.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by Mr.Zero on 2017/3/2.
 * This controller handles request about vip information
 */
@Controller
@RequestMapping(value = "/vip")
public class VipController {
    private VipService vipService;

    @Autowired
    public VipController(VipService vipService) {
        this.vipService = vipService;
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getVipInfo(){
        //vip info is already in HttpSession
        return "vipInfo";
    }


    public String bookHotel() {
        return "";
    }
}
