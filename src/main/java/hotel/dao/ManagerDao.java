package hotel.dao;

import hotel.entity.ManagerTblEntity;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface ManagerDao {

    public ManagerTblEntity getManagerByUsername(String username);

    public Integer addManager(ManagerTblEntity manager);
}
