package hotel.controller;

import hotel.service.HotelService;
import hotel.service.ManagerService;
import hotel.type.ManagerType;
import hotel.vo.HotelPlanForm;
import hotel.vo.HotelVO;
import hotel.vo.ManagerVO;
import hotel.vo.PlanVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/4.
 */
@Controller
@RequestMapping(value = "/sbmanager")
public class ManagerController {

    private ManagerService managerService;
    private HotelService hotelService;

    @Autowired
    public ManagerController(ManagerService managerService, HotelService hotelService) {
        this.managerService = managerService;
        this.hotelService = hotelService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String managerHome(HttpSession session) {
        ManagerVO managerVO = (ManagerVO) session.getAttribute("managerInfo");
        ManagerType managerType = managerVO.getType();
        if (managerType == ManagerType.TOP_MANAGER) {
            return "redirect:/topmanager/check-apply";
        }
        else {      //normal manager
            return "forward:/sbmanager/hotel-plan";
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

}
