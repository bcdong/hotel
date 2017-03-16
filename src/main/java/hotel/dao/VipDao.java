package hotel.dao;

import hotel.entity.VipTblEntity;

import java.util.List;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public interface VipDao {

    public VipTblEntity addVip(VipTblEntity vipTblEntity);

    public VipTblEntity getVipById(int id);

    public VipTblEntity getVipByUsername(String username);

    public boolean updateVip(VipTblEntity entity);

    public boolean updatePassword(int id, String oldPass, String newPass);

    public List<Object[]> getVipOrderCount();

    public List<Object[]> getVipCost();
}
