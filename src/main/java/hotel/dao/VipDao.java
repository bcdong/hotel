package hotel.dao;

import hotel.entity.VipTblEntity;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface VipDao {

    public VipTblEntity addVip(VipTblEntity vipTblEntity);

    public VipTblEntity getVipById(int id);

    public VipTblEntity getVipByUsername(String username);
}
