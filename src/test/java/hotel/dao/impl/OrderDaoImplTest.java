package hotel.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mr.Zero on 2017/3/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class OrderDaoImplTest {

    @Autowired
    private OrderDaoImpl orderDao;

    @Test
    public void getHotelLiveCount() throws Exception {
        List<Object[]> list = orderDao.getHotelLiveCount();
        for (Object[] o : list) {
            System.out.println(o[0]+"============="+o[1]);
        }
    }

}