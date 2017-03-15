package hotel.controller;

import hotel.service.ManagerService;
import hotel.service.VipService;
import hotel.vo.*;
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
                return "redirect:/sbmanager";
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
        RegisterForm form = new RegisterForm();
        model.addAttribute("regForm", form);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@Valid RegisterForm regForm, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            return "register";
        }
        if (regForm.getUserType().equals("manager")) {
            ManagerForm managerForm = new ManagerForm();
            managerForm.setName(regForm.getName());
            managerForm.setUsername(regForm.getUsername());
            managerForm.setPassword(regForm.getPassword());
            ManagerVO vo = managerService.addManager(managerForm);
            if (vo == null) {
                model.addAttribute("errorMessage", "该用户名已存在");
                return "register";
            } else {
                session.setAttribute("managerInfo", vo);
                return "redirect:/sbmanager/open-hotel";
            }
        } else if (regForm.getUserType().equals("vip")) {
            VipVO vipVO = new VipVO();
            vipVO.setUsername(regForm.getUsername());
            vipVO.setPassword(regForm.getPassword());
            vipVO.setName(regForm.getName());
            VipVO resultVO = vipService.register(vipVO);
            if (resultVO == null){
                model.addAttribute("errorMessage", "该用户名已存在");
                return "register";
            }
            else {
                session.setAttribute("vipInfo", resultVO);
                return "redirect:/vip/info";
            }
        } else {
            return "register";
        }

    }
}
