package hotel.controller;

import hotel.service.HotelService;
import hotel.service.OrderService;
import hotel.service.VipService;
import hotel.type.OrderResult;
import hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Zero on 2017/3/2.
 * This controller handles request about vip information
 */
@Controller
@RequestMapping(value = "/vip")
public class VipController {
    private VipService vipService;
    private HotelService hotelService;
    private DateFormat df;
    private OrderService orderService;

    @Autowired
    public VipController(VipService vipService, HotelService hotelService, OrderService orderService) {
        this.vipService = vipService;
        this.hotelService = hotelService;
        this.orderService = orderService;
        this.df = new SimpleDateFormat("yyyy-MM-dd");
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getVipInfo(Model model, HttpSession session){
        //vip info is already in HttpSession
        VipVO vipVO = (VipVO) session.getAttribute("vipInfo");
        VipBasicInfo basicInfo = new VipBasicInfo();
        basicInfo.setId(vipVO.getId());
        basicInfo.setName(vipVO.getName());
        basicInfo.setBankId(vipVO.getBankId());
        model.addAttribute("basicInfo", basicInfo);
        return "vip/vipInfo";
    }

    @RequestMapping(value = "/info", method = RequestMethod.POST)
    public String updateVipInfo(@Valid VipBasicInfo vip, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "vip/vipInfo";
        }
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");
        vip.setId(vipInfo.getId());
        VipVO vipVO = vipService.update(vip);
        session.setAttribute("vipInfo", vipVO);
        model.addAttribute("successMessage", "更新成功");
        return getVipInfo(model, session);
    }

    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String getChangePassword(Model model) {
        model.addAttribute("passwordForm", new PasswordForm());
        return "vip/changePassword";
    }

    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public String postChangePassword(@Valid PasswordForm passwordForm, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "/vip/changePassword";
        }
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");
        String id = vipInfo.getId();
        boolean result = vipService.changePassword(id, passwordForm.getOldPassword(), passwordForm.getNewPassword());
        if (!result) {
            model.addAttribute("errorMessage", "旧密码错误！");
        }
        else {
            model.addAttribute("successMessage", "密码更新成功！");
        }
        model.addAttribute("passwordForm", new PasswordForm());
        return "vip/changePassword";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String accountInfo(HttpSession session) {
        VipVO oldInfo = (VipVO) session.getAttribute("vipInfo");
        VipVO vipInfo = vipService.getVipById(oldInfo.getId());
        session.setAttribute("vipInfo", vipInfo);
        return "vip/account";
    }

    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public String dealScore(@RequestParam("score") String scoreStr,
                            Model model, HttpSession session) {
        int score = -1;
        try {
            score = Integer.parseInt(scoreStr);
            if (score <= 0) {
                model.addAttribute("scoreError", "兑换积分必须为正数");
                return "vip/account";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("scoreError", "兑换积分必须为正数");
            return "vip/account";
        }
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");
        VipVO vo = vipService.dealScore(vipInfo.getId(), score*100);
        if (vo == null) {
            model.addAttribute("scoreError", "兑换失败");
        } else {
            model.addAttribute("scoreSuccess", "兑换成功");
            session.setAttribute("vipInfo", vo);
        }
        return "vip/account";
    }

    @RequestMapping(value = "/charge", method = RequestMethod.POST)
    public String charge(@RequestParam("money") String moneyStr,
                         HttpSession session, Model model) {
        VipVO vipVO = (VipVO) session.getAttribute("vipInfo");
        double money = -1.0;
        try {
            money = Double.parseDouble(moneyStr);
            if (money <= 0) {
                model.addAttribute("chargeError", "充值金额必须为正数");
                return "vip/account";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("chargeError", "充值金额必须为正数");
            return "vip/account";
        }
        VipVO vo = vipService.charge(vipVO.getId(), money);
        if (vo == null) {
            model.addAttribute("chargeError", "充值失败");
        } else {
            model.addAttribute("chargeSuccess", "充值成功");
            session.setAttribute("vipInfo", vo);
        }
        return "vip/account";
    }

    @RequestMapping(value = "/delete-vip")
    public String deleteVip(HttpSession session) {
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");
        boolean result = vipService.stopVip(vipInfo.getId());
        if (result) {
            session.removeAttribute("vipInfo");
            session.invalidate();
            return "redirect:/hotel";
        }
        else {
            return "vip/account";
        }
    }

    @RequestMapping(value = "/book-hotel", method = RequestMethod.GET)
    public String getBookHotel(@RequestParam("hotelId") String hotelId,
                            @RequestParam("roomType") String roomType,
                            Model model) {
        HotelVO hotelVO = hotelService.getHotel(hotelId);
        BookHotelForm bookForm = new BookHotelForm();
        bookForm.setHotelId(hotelId);
        bookForm.setHotelName(hotelVO.getName());
        bookForm.setHotelAddress(hotelVO.getAddress());
        bookForm.setRoomType(roomType);
        for (PlanVO vo : hotelVO.getPlans()) {
            if (vo.getRoomType().equals(roomType)) {
                bookForm.setRoomPrice(vo.getRoomPrice());
            }
        }
        bookForm.setToday(df.format(new Date()));
        model.addAttribute("bookForm", bookForm);
        return "vip/bookHotel";
    }

    @RequestMapping(value = "/book-hotel", method = RequestMethod.POST)
    public String postBookHotel(BookHotelForm bookForm,
                                Model model,
                                HttpSession session) {
        Date fromDate;
        Date toDate;
        try {
            fromDate = df.parse(bookForm.getFromTime());
            toDate = df.parse(bookForm.getToTime());
            if (fromDate.after(toDate)) {
                model.addAttribute("errorMessage", "退房日期不得早于入住日期");
                model.addAttribute("bookForm", bookForm);
                return "vip/bookHotel";
            }
        } catch (ParseException e) {
            model.addAttribute("errorMessage", "日期格式错误");
            model.addAttribute("bookForm", bookForm);
            return "vip/bookHotel";
        }
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");
        OrderVO orderVO = vipService.bookHotel(bookForm.getHotelId(), bookForm.getRoomType(), fromDate, toDate, vipInfo.getId(), bookForm.getCustomer());
        model.addAttribute("orderVO", orderVO);
        return "vip/order";
    }

    @RequestMapping(value = "/confirm-order")
    public String confirmOrder(OrderVO orderVO, Model model, HttpSession session) {
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");
        orderVO.setVipId(vipInfo.getId());
        OrderResult result = vipService.confirmOrder(orderVO);
        switch(result) {
            case LACK_MONEY:model.addAttribute("errorMessage", "余额不足，支付失败");break;
            case LACK_ROOM:model.addAttribute("errorMessage", "您预定的房间已被订完，请选择其他类型房间");
            case SUCCESS:
            default:{
                model.addAttribute("successMessage", "预订成功");
                VipVO newInfo = vipService.getVipById(vipInfo.getId());
                session.setAttribute("vipInfo", newInfo);
            }
        }
        return "vip/orderResult";
    }

    @RequestMapping(value = "/record")
    public String getMyOrder(Model model, HttpSession session) {
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");
        Map<String,Object> map = orderService.getVipStatistic(vipInfo.getId());
        model.addAttribute("totalCost", map.get("totalCost"));
        model.addAttribute("orderCount", map.get("orderCount"));
        String DEFAULT_ORDER_TYPE = "BOOK";
        List<OrderVO> bookOrders = orderService.getOrderByVipAndState(vipInfo.getId(), DEFAULT_ORDER_TYPE);
        model.addAttribute("bookOrders", bookOrders);
        return "vip/record";
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ResponseBody
    public List<OrderVO> getOrderByState(@RequestParam("state") String state, HttpSession session) {
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");
        return orderService.getOrderByVipAndState(vipInfo.getId(), state);
    }
}
