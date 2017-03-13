package hotel.service;

import hotel.vo.OrderVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Mr.Zero on 2017/3/12.
 */
public interface OrderService {
    public List<OrderVO> getOrderByVip(String vipId);

    public List<OrderVO> getOrderByVipAndState(String vipId, String state);

    public Map<String,Object> getVipStatistic(String vipId);
}
