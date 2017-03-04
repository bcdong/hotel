package hotel.controller;

import hotel.service.ManagerService;
import hotel.type.ManagerType;
import hotel.vo.ManagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Mr.Zero on 2017/3/4.
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {

    private ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String managerHome(HttpSession session) {
        ManagerVO managerVO = (ManagerVO) session.getAttribute("managerInfo");
        ManagerType managerType = managerVO.getType();
        if (managerType == ManagerType.TOP_MANAGER) {
            return "topManagerHome";
        }
        else {      //normal manager
            return "managerHome";
        }
    }
}
