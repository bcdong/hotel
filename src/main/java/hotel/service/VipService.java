package hotel.service;

import hotel.type.OrderResult;
import hotel.vo.OrderVO;
import hotel.vo.VipBasicInfo;
import hotel.vo.VipVO;

import java.util.Date;
import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
public interface VipService {

    public VipVO register(VipVO vipVO);

    public VipVO login(String username, String password);

    public VipVO getVipById(String vipId);

    public VipVO update(VipBasicInfo vipBasicInfo);

    public boolean changePassword(String id, String oldPass, String newPass);

    public VipVO charge(String id, double money);

    public boolean stopVip(String id);

    public VipVO dealScore(String id, int score);

    public OrderVO bookHotel(String hotelId, String roomType, Date fromTime, Date toTime, String vipId, String customer);

    public OrderResult confirmOrder(OrderVO orderVO);

    public List<Object[]> getVipOrderCount();

    public List<Object[]> getVipCost();
}
