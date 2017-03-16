package hotel.controller;

import hotel.service.HotelService;
import hotel.service.VipService;
import hotel.vo.HotelIncomeVO;
import hotel.vo.HotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Zero on 2017/3/5.
 */
@Controller
@RequestMapping(value = "/topmanager")
public class TopManagerController {

    private HotelService hotelService;
    private VipService vipService;

    @Autowired
    public TopManagerController(HotelService hotelService, VipService vipService) {
        this.hotelService = hotelService;
        this.vipService = vipService;
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

    @RequestMapping(value = "/check-vip", method = RequestMethod.GET)
    public String getVipStatistic() {
        return "manager/topCheckVip";
    }

    @RequestMapping(value = "/get-vip-statistic", method = RequestMethod.GET)
    @ResponseBody
    public Map getVipOrderCount() {
        List<Object[]> orderCount = vipService.getVipOrderCount();
        List<Object[]> vipCost = vipService.getVipCost();
        Map<String,List> map = new HashMap<>();
        map.put("orderCount", orderCount);
        map.put("vipCost", vipCost);
        return map;
    }

    @RequestMapping(value = "check-finance", method = RequestMethod.GET)
    public String getFinance() {
        return "manager/topCheckFinance";
    }

    @RequestMapping(value = "get-hotel-income", method = RequestMethod.GET)
    @ResponseBody
    public List<Object[]> getAllHotelIncome() {
        return hotelService.getAllHotelIncome();
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
