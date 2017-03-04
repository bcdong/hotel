package hotel.service.impl;

import hotel.dao.ManagerDao;
import hotel.entity.ManagerTblEntity;
import hotel.service.ManagerService;
import hotel.util.PO2VO;
import hotel.vo.ManagerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Zero on 2017/3/4.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    private ManagerDao managerDao;
    private PO2VO po2VO;

    @Autowired
    public ManagerServiceImpl(ManagerDao managerDao, PO2VO po2VO) {
        this.managerDao = managerDao;
        this.po2VO = po2VO;
    }

    public ManagerVO login(String username, String password) {
        ManagerTblEntity po = managerDao.getManagerByUsername(username);
        if ((po != null) && (po.getPassword().equals(password))) {
            return po2VO.managerPO2VO(po);
        }
        else {
            return null;
        }
    }
}
