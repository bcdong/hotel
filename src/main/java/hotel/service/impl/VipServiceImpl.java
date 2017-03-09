package hotel.service.impl;

import hotel.dao.VipDao;
import hotel.entity.VipTblEntity;
import hotel.service.VipService;
import hotel.type.VipState;
import hotel.util.PO2VO;
import hotel.vo.VipBasicInfo;
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
        if ((vip != null) && (vip.getState()!=VipState.STOP) && (vip.getPassword().equals(password))){
            return po2VO.vipPO2VO(vip);
        }
        else {
            return null;
        }
    }

    @Override
    public VipVO update(VipBasicInfo vipBasicInfo) {
        int id = Integer.parseInt(vipBasicInfo.getId());
        VipTblEntity po = vipDao.getVipById(id);
        if (po!=null) {
            po.setName(vipBasicInfo.getName());
            po.setBankId(vipBasicInfo.getBankId());
            vipDao.updateVip(po);
        }
        return po2VO.vipPO2VO(po);
    }

    @Override
    public boolean changePassword(String id, String oldPass, String newPass) {
        int vipId = Integer.parseInt(id);
        return vipDao.updatePassword(vipId, oldPass, newPass);
    }

    @Override
    public VipVO charge(String id, double money) {
        int vipId = Integer.parseInt(id);
        VipTblEntity po = vipDao.getVipById(vipId);
        if (po!=null) {
            //此处调用银行api来扣钱
            if (money >= 1000 && po.getState() == VipState.PRE_ACTIVE) {
                po.setState(VipState.ACTIVE);
            }
            double balance = po.getBalance()+money;
            po.setBalance(balance);
            vipDao.updateVip(po);
            return po2VO.vipPO2VO(po);
        }
        return null;
    }

    @Override
    public boolean stopVip(String id) {
        int vipId = Integer.parseInt(id);
        VipTblEntity po = vipDao.getVipById(vipId);
        if (po!=null && po.getState()!=VipState.STOP) {
            po.setState(VipState.STOP);
            vipDao.updateVip(po);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public VipVO dealScore(String id, int score) {
        int vipId = Integer.parseInt(id);
        VipTblEntity po = vipDao.getVipById(vipId);
        if (po!=null) {
            if ((score>0) && (score<=po.getScore())) {
                double balance = po.getBalance();
                po.setScore(po.getScore()-score);
                po.setBalance(balance+score/100);
                vipDao.updateVip(po);
                return po2VO.vipPO2VO(po);
            }
        }
        return null;
    }
}
