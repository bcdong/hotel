package hotel.controller;

import hotel.service.HotelService;
import hotel.service.ManagerService;
import hotel.service.OrderService;
import hotel.type.ManagerType;
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
import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/4.
 */
@Controller
@RequestMapping(value = "/sbmanager")
public class ManagerController {

    private HotelService hotelService;
    private OrderService orderService;

    @Autowired
    public ManagerController(ManagerService managerService, HotelService hotelService, OrderService orderService) {
        this.hotelService = hotelService;
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String managerHome(HttpSession session) {
        ManagerVO managerVO = (ManagerVO) session.getAttribute("managerInfo");
        ManagerType managerType = managerVO.getType();
        if (managerType == ManagerType.TOP_MANAGER) {
            return "redirect:/topmanager/check-apply";
        }
        else {      //normal manager
            return "forward:/sbmanager/orders";
        }
    }

    @RequestMapping(value = "/statistic", method = RequestMethod.GET)
    public String statistic(HttpSession session) {
        ManagerVO managerVO = (ManagerVO) session.getAttribute("managerInfo");
        //todo

        return "manager/statistic";
    }

    @RequestMapping(value = "/open-hotel", method = RequestMethod.GET)
    public String getOpenHotel(Model model) {
        HotelVO hotel = new HotelVO();
        model.addAttribute("hotelVO", hotel);
        return "manager/openHotel";
    }

    @RequestMapping(value = "/open-hotel", method = RequestMethod.POST)
    public String postOpenHotel(@Valid HotelVO hotel, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return "manager/openHotel";
        }
        ManagerVO managerInfo = (ManagerVO) session.getAttribute("managerInfo");
        int managerId = managerInfo.getId();
        HotelVO hotelVO = hotelService.addHotel(hotel, managerId);
        managerInfo.setHotel(hotelVO);
        session.setAttribute("managerInfo", managerInfo);
        return "manager/hotelPlan";
    }

    @RequestMapping(value = "/hotel-plan", method = RequestMethod.GET)
    public String getHotelPlan(Model model, HttpSession session){
        ManagerVO managerVO = (ManagerVO) session.getAttribute("managerInfo");
        if (managerVO.getHotel() != null) {
            String  hotelId = managerVO.getHotel().getId();
            HotelVO hotelVOWithPlan = hotelService.getHotel(hotelId);
            HotelPlanForm planForm = new HotelPlanForm();
            planForm.setHotelId(hotelId);
            List<PlanVO> plans =  hotelVOWithPlan.getPlans();
            for (PlanVO planVO : plans) {
                switch (planVO.getRoomType()) {
                    case "单人间":
                        planForm.setSingleRoomCount(planVO.getRoomCount());
                        planForm.setSingleRoomPrice(planVO.getRoomPrice());
                        break;
                    case "双人间":
                        planForm.setDoubleRoomCount(planVO.getRoomCount());
                        planForm.setDoubleRoomPrice(planVO.getRoomPrice());
                        break;
                    case "三人间":
                        planForm.setTripleRoomCount(planVO.getRoomCount());
                        planForm.setTripleRoomPrice(planVO.getRoomPrice());
                        break;
                    default:break;
                }
            }
            model.addAttribute("planForm", planForm);
        }
        return "manager/hotelPlan";
    }

    @RequestMapping(value = "/hotel-plan", method = RequestMethod.POST)
    public String postHotelPlan(@Valid HotelPlanForm planForm, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "manager/hotelPlan";
        }
        ManagerVO managerVO = (ManagerVO) session.getAttribute("managerInfo");
        planForm.setHotelId(managerVO.getHotel().getId());
        hotelService.updatePlan(planForm);
        return getHotelPlan(model, session);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String getHotelOrderPage(Model model, HttpSession session) {
        ManagerVO managerVO = (ManagerVO) session.getAttribute("managerInfo");
        List<OrderVO> bookOrders = orderService.getOrderByHotelAndState(managerVO.getHotel().getId(), "BOOK");
        model.addAttribute("orderList", bookOrders);
        return "manager/orders";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    @ResponseBody
    public List<OrderVO> getOrderByHotelAndState(@RequestParam("state") String state,
                                                 HttpSession session) {
        ManagerVO managerVO = (ManagerVO) session.getAttribute("managerInfo");
        return orderService.getOrderByHotelAndState(managerVO.getHotel().getId(), state);
    }

    @RequestMapping(value = "/postLiveIn", method = RequestMethod.POST)
    @ResponseBody
    public boolean handleLiveIn(@RequestParam("orderId") String orderId,
                               @RequestParam("roomId") String roomId) {
        return orderService.updateOrderState(orderId, "IN", roomId);
    }

    @RequestMapping(value = "/postLeave", method = RequestMethod.POST)
    @ResponseBody
    public boolean handleLeave(@RequestParam("orderId") String orderId) {
        return orderService.updateOrderState(orderId, "LEAVE");
    }
}
