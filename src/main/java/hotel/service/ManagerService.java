package hotel.service;

import hotel.entity.ManagerTblEntity;
import hotel.vo.ManagerForm;
import hotel.vo.ManagerVO;

/**
 * Created by Mr.Zero on 2017/3/4.
 */
public interface ManagerService {
    public ManagerVO login(String username, String password);

    public boolean addManager(ManagerForm form);
}
