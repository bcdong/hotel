package hotel.entity;

import hotel.type.VipState;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
@Entity
@Table(name = "vip_tbl", schema = "hotel", catalog = "")
public class VipTblEntity {
    private String id;
    private String name;
    private double balance;
    private int level;
    private int experience;
    private int score;
    private VipState state;
    private Collection<OrderTblEntity> orderTblsById;

    @Id
    @Column(name = "id", nullable = false, length = 16)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "balance", nullable = false, precision = 0)
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "experience", nullable = false)
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Basic
    @Column(name = "score", nullable = false)
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false, length = 32)
    public VipState getState() {
        return state;
    }

    public void setState(VipState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VipTblEntity that = (VipTblEntity) o;

        if (Double.compare(that.balance, balance) != 0) return false;
        if (level != that.level) return false;
        if (experience != that.experience) return false;
        if (score != that.score) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(balance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + level;
        result = 31 * result + experience;
        result = 31 * result + score;
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "vipTblByVipId")
    public Collection<OrderTblEntity> getOrderTblsById() {
        return orderTblsById;
    }

    public void setOrderTblsById(Collection<OrderTblEntity> orderTblsById) {
        this.orderTblsById = orderTblsById;
    }
}
