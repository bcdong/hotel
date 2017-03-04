package hotel.controller;

import hotel.service.ManagerService;
import hotel.service.VipService;
import hotel.vo.LoginForm;
import hotel.vo.ManagerVO;
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
 * Created by Mr.Zero on 2017/3/4.
 */
@Controller
@RequestMapping(value = "/auth")
public class AuthController {

    private VipService vipService;
    private ManagerService managerService;

    @Autowired
    public AuthController(VipService vipService, ManagerService managerService) {
        this.vipService = vipService;
        this.managerService = managerService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model){
        LoginForm loginForm = new LoginForm();
        loginForm.setUserType("vip");
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(@Valid LoginForm loginForm, Errors errors, Model model, HttpSession session){
        if (errors.hasErrors()){
            return "login";
        }
        String userType = loginForm.getUserType();
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if (userType.equals("vip")) {
            VipVO vipVO = vipService.login(username, password);
            if (vipVO == null) {
                model.addAttribute("errorMessage", "对比起，用户名或密码错误");
                return "login";
            }
            else {
                session.setAttribute("vipInfo", vipVO);
                return "redirect:/hotel";
            }
        }
        else {
            ManagerVO managerVO = managerService.login(username, password);
            if (managerVO == null) {
                model.addAttribute("errorMessage", "对比起，用户名或密码错误");
                return "login";
            }
            else {
                session.setAttribute("managerInfo", managerVO);
                return "redirect:/manager";
            }
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.removeAttribute("vipInfo");
        session.removeAttribute("managerInfo");
        return "redirect:/hotel";
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
            model.addAttribute("errorMessage", "该用户名已存在");
            return "register";
        }
        else {
            session.setAttribute("vipInfo", resultVO);
            return "forward:/vip/info";
        }
    }
}
