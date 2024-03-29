package hotel.service.impl;

import hotel.dao.ManagerDao;
import hotel.entity.ManagerTblEntity;
import hotel.service.ManagerService;
import hotel.type.ManagerType;
import hotel.util.PO2VO;
import hotel.vo.ManagerForm;
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

    @Override
    public ManagerVO addManager(ManagerForm form) {
        ManagerTblEntity entity = new ManagerTblEntity();
        entity.setName(form.getName());
        entity.setUsername(form.getUsername());
        entity.setPassword(form.getPassword());
        entity.setType(ManagerType.MANAGER);
        Integer id = managerDao.addManager(entity);
        if (id < 0) {
            return null;
        } else {
            ManagerVO vo = new ManagerVO();
            vo.setId(id);
            vo.setName(form.getName());
            vo.setType(ManagerType.MANAGER);
            return vo;
        }
    }

    @Override
    public ManagerVO getManagerById(int id) {
        ManagerTblEntity po = managerDao.getManagerById(id);
        return po2VO.managerPO2VO(po);
    }
}
