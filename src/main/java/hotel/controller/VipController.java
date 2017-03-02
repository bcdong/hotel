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
 */
@Controller
@RequestMapping(value = "/vip")
public class VipController {
    private VipService vipService;

    @Autowired
    public VipController(VipService vipService) {
        this.vipService = vipService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@Valid LoginForm loginForm, Errors errors, Model model, HttpSession session){
        if (errors.hasErrors()){
            return "login";
        }
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        VipVO vo = vipService.login(username, password);
        if (vo == null) {
            model.addAttribute("errorPassword", "对比起，密码错误");
            return "login";
        }
        else {
            session.setAttribute("vipInfo", vo);
            return "redirect:/hotel";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model){
        VipVO vipVO = new VipVO();
        model.addAttribute("vip", vipVO);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@Valid VipVO vip, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "register";
        }
        VipVO resultVO = vipService.register(vip);
        if (resultVO == null){
            model.addAttribute("duplicateUsername", "该用户名已存在");
            return "register";
        }
        else {
            session.setAttribute("vipInfo", resultVO);
            return "forward:/vip/info";
        }
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getVipInfo(){
        //vip info is already in HttpSession
        return "vipInfo";
    }
}
