package hotel.controller;

import hotel.service.HotelService;
import hotel.service.ManagerService;
import hotel.vo.HotelIncomeVO;
import hotel.vo.HotelVO;
import hotel.vo.ManagerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/5.
 */
@Controller
@RequestMapping(value = "/topmanager")
public class TopManagerController {

    private HotelService hotelService;
    private ManagerService managerService;

    @Autowired
    public TopManagerController(HotelService hotelService, ManagerService managerService) {
        this.hotelService = hotelService;
        this.managerService = managerService;
    }

    @RequestMapping(value = "/check-apply", method = RequestMethod.GET)
    public String getCheckApply(Model model){
        List<HotelVO> hotelVOS = hotelService.getApplyHotels();
        model.addAttribute("hotelVOs", hotelVOS);
        return "/manager/topCheckApply";
    }

    @RequestMapping(value = "/handle-request", method = RequestMethod.GET)
    public String handleApply(@RequestParam(value = "action") String action,
                                 @RequestParam(value = "id") String hotelId,
                              Model model) {
        List<HotelVO> hotelVOS = hotelService.handleApply(action, hotelId);
        model.addAttribute("hotelVOs", hotelVOS);
        return "/manager/topCheckApply";
    }

    @RequestMapping(value = "/jiesuan", method = RequestMethod.GET)
    public String getTradeJieSuan(Model model) {
        List<HotelIncomeVO> incomeVOS = hotelService.getHotelIncomes();
        model.addAttribute("incomeVOs", incomeVOS);
        return "manager/topJieSuan";
    }

    @RequestMapping(value = "/jiesuan", method = RequestMethod.POST)
    @ResponseBody
    public List<HotelIncomeVO> postJieSuan(@RequestParam("hotelId") String hotelId) {
        return hotelService.jieSuanHotel(hotelId);
    }

    @RequestMapping(value = "/check-status", method = RequestMethod.GET)
    public String getHotelLiveState(Model model) {
        return "manager/topCheckStatus";
    }
    @RequestMapping(value = "/get-hotel-count", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getHotelLiveCount(){
        return hotelService.getHotelLiveStatus();
    }

    @RequestMapping(value = "check-vip", method = RequestMethod.GET)
    public String getVipStatistic() {
        return "";
    }

    @RequestMapping(value = "check-finance", method = RequestMethod.GET)
    public String getFinance() {
        return "";
    }

//    @RequestMapping(value = "/add-manager", method = RequestMethod.GET)
//    public String getAddManager(Model model) {
//        model.addAttribute("managerForm", new ManagerForm());
//        return "/manager/topAddManager";
//    }
//
//    @RequestMapping(value = "/add-manager", method = RequestMethod.POST)
//    public String postAddManager(@Valid ManagerForm managerForm, Errors errors, Model model) {
//        if (errors.hasErrors()) {
//            return "manager/topAddManager";
//        }
//        boolean result = managerService.addManager(managerForm);
//        if (result) {
//            model.addAttribute("managerForm", new ManagerForm());
//            model.addAttribute("successMessage", "添加成功!");
//        } else {
//            model.addAttribute("errorMessage", "用户名已存在!");
//        }
//        return "/manager/topAddManager";
//    }
}
