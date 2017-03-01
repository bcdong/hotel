package hotel.dao;

import hotel.entity.VipTblEntity;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface VipDao {

    public boolean addVip(VipTblEntity vipTblEntity);

    public VipTblEntity getVip(String id);
}
