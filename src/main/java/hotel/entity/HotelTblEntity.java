package hotel.entity;

import hotel.type.HotelState;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Entity
@Table(name = "hotel_tbl", schema = "hotel", catalog = "")
public class HotelTblEntity {
    private int id;
    private String name;
    private String address;
    private HotelState state;
    private ManagerTblEntity managerTblByManagerId;
    private Collection<OrderTblEntity> orderTblsById;
    private Collection<PlanTblEntity> planTblsById;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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
    @Column(name = "address", length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false, length = 32)
    public HotelState getState() {
        return state;
    }

    public void setState(HotelState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HotelTblEntity entity = (HotelTblEntity) o;

        if (id != entity.id) return false;
        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        if (address != null ? !address.equals(entity.address) : entity.address != null) return false;
        if (state != null ? !state.equals(entity.state) : entity.state != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    public ManagerTblEntity getManagerTblByManagerId() {
        return managerTblByManagerId;
    }

    public void setManagerTblByManagerId(ManagerTblEntity managerTblByManagerId) {
        this.managerTblByManagerId = managerTblByManagerId;
    }

    @OneToMany(mappedBy = "hotelTblByHotelId")
    public Collection<OrderTblEntity> getOrderTblsById() {
        return orderTblsById;
    }

    public void setOrderTblsById(Collection<OrderTblEntity> orderTblsById) {
        this.orderTblsById = orderTblsById;
    }

    @OneToMany(mappedBy = "hotelTblByHotelId")
    public Collection<PlanTblEntity> getPlanTblsById() {
        return planTblsById;
    }

    public void setPlanTblsById(Collection<PlanTblEntity> planTblsById) {
        this.planTblsById = planTblsById;
    }

}
