package hotel.dao.impl;

import hotel.dao.ManagerDao;
import hotel.entity.ManagerTblEntity;
import hotel.type.ManagerType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Mr.Zero on 2017/3/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
public class ManagerDaoImplTest {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    public ManagerDaoImplTest() {
    }

    @Test
    public void getManagerByUsername() throws Exception {

    }

    @Test
    public void addManager() throws Exception {
        ManagerTblEntity manager = new ManagerTblEntity();
        manager.setName("戴新颜");
        manager.setType(ManagerType.MANAGER);
        manager.setUsername("daixinyan");
        manager.setPassword("1234qwer");
        ManagerTblEntity savedManager = managerDao.addManager(manager);
        assertNotNull(savedManager);
    }

}