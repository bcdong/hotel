package hotel.service;

import hotel.vo.VipVO;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
public interface VipService {

    public VipVO register(VipVO vipVO);

    public VipVO login(String username, String password);
}
