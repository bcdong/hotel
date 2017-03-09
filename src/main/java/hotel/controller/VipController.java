package hotel.controller;

import hotel.service.VipService;
import hotel.vo.PasswordForm;
import hotel.vo.VipBasicInfo;
import hotel.vo.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        VipVO vipInfo = (VipVO) session.getAttribute("vipInfo");

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
}
