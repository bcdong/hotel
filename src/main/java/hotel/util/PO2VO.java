package hotel.util;

import hotel.entity.VipTblEntity;
import hotel.type.VipState;
import hotel.vo.VipVO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Service
public class PO2VO {

    private DecimalFormat df;

    public PO2VO() {
        String formatStr = "0000000";
        this.df = new DecimalFormat(formatStr);
    }

    public VipVO vipPO2VO(VipTblEntity po) {
        VipVO vo = new VipVO();
        vo.setId(df.format(vo.getId()));
        vo.setName(po.getName());
        vo.setBalance(po.getBalance());
        vo.setLevel(po.getLevel());
        vo.setExperience(po.getExperience());
        vo.setScore(po.getScore());
        vo.setBankId(po.getBankId());
        vo.setUsername(po.getUsername());
        VipState state = po.getState();
        switch (state){
            case PRE_ACTIVE:vo.setState("未激活");
            case ACTIVE:vo.setState("正常");break;
            case SUSPEND:vo.setState("欠费暂停");break;
            case STOP:vo.setState("失效");break;
            default:break;
        }
        return vo;
    }
}
