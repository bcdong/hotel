package hotel.service.impl;

import hotel.dao.VipDao;
import hotel.entity.VipTblEntity;
import hotel.service.VipService;
import hotel.type.VipState;
import hotel.util.PO2VO;
import hotel.vo.VipVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Service
public class VipServiceImpl implements VipService {

    private VipDao vipDao;
    private PO2VO po2VO;

    @Autowired
    public VipServiceImpl(VipDao vipDao, PO2VO po2VO) {
        this.vipDao = vipDao;
        this.po2VO = po2VO;
    }

    public VipVO register(VipVO vipVO) {
        VipTblEntity vipTblEntity = vipDao.getVipByUsername(vipVO.getUsername());
        if (vipTblEntity == null) {
            VipTblEntity po = new VipTblEntity();
            po.setName(vipVO.getName());
            po.setBalance(0.0);
            po.setLevel(1);
            po.setExperience(0);
            po.setScore(0);
            po.setState(VipState.PRE_ACTIVE);
            po.setBankId(vipVO.getBankId());
            po.setUsername(vipVO.getUsername());
            po.setPassword(vipVO.getPassword());
            VipTblEntity newVip = vipDao.addVip(po);
            return po2VO.vipPO2VO(newVip);
        }
        return null;
    }

    public VipVO login(String username, String password) {
        VipTblEntity vip = vipDao.getVipByUsername(username);
        if ((vip != null) && (vip.getPassword().equals(password))){
            return po2VO.vipPO2VO(vip);
        }
        else {
            return null;
        }
    }
}
