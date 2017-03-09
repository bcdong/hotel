package hotel.service;

import hotel.vo.VipBasicInfo;
import hotel.vo.VipVO;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
public interface VipService {

    public VipVO register(VipVO vipVO);

    public VipVO login(String username, String password);

    public VipVO update(VipBasicInfo vipBasicInfo);

    public boolean changePassword(String id, String oldPass, String newPass);

    public VipVO charge(String id, double money);

    public boolean stopVip(String id);

    public VipVO dealScore(String id, int score);
}
