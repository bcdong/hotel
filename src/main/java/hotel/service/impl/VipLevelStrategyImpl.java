package hotel.service.impl;

import hotel.service.VipLevelStrategy;
import org.springframework.stereotype.Service;

/**
 * Created by Mr.Zero on 2017/3/11.
 */
@Service
public class VipLevelStrategyImpl implements VipLevelStrategy {
    @Override
    public int experience2Level(int experience) {
        if (experience < 1000) {
            return 1;
        } else if (experience < 5000) {
            return 2;
        } else if (experience < 10000) {
            return 3;
        } else if (experience < 20000) {
            return 4;
        } else {
            return 5;
        }
    }
}
