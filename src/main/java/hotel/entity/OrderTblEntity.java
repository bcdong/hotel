package hotel.entity;

import hotel.type.OrderState;
import hotel.type.PayMethod;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Entity
@Table(name = "order_tbl", schema = "hotel", catalog = "")
public class OrderTblEntity {
    private int id;
    private Timestamp fromTime;
    private Timestamp toTime;
    private OrderState state;
    private double cost;
    private PayMethod payMethod;
    private Collection<OrderCustomerTblEntity> orderCustomerTblsById;
    private VipTblEntity vipTblByVipId;
    private HotelTblEntity hotelTblByHotelId;
    private RoomTblEntity roomTblEntity;

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
    @Column(name = "from_time", nullable = true)
    public Timestamp getFromTime() {
        return fromTime;
    }

    public void setFromTime(Timestamp fromTime) {
        this.fromTime = fromTime;
    }

    @Basic
    @Column(name = "to_time", nullable = true)
    public Timestamp getToTime() {
        return toTime;
    }

    public void setToTime(Timestamp toTime) {
        this.toTime = toTime;
    }

    @Basic
    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false, length = 32)
    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    @Basic
    @Column(name = "cost", nullable = false, precision = 0)
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Basic
    @Enumerated(value = EnumType.STRING)
    @Column(name = "pay_method", nullable = false, length = 32)
    public PayMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PayMethod payMethod) {
        this.payMethod = payMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderTblEntity that = (OrderTblEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.cost, cost) != 0) return false;
        if (fromTime != null ? !fromTime.equals(that.fromTime) : that.fromTime != null) return false;
        if (toTime != null ? !toTime.equals(that.toTime) : that.toTime != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (payMethod != null ? !payMethod.equals(that.payMethod) : that.payMethod != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (fromTime != null ? fromTime.hashCode() : 0);
        result = 31 * result + (toTime != null ? toTime.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (payMethod != null ? payMethod.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "orderTblByOrderId", fetch = FetchType.EAGER)
    public Collection<OrderCustomerTblEntity> getOrderCustomerTblsById() {
        return orderCustomerTblsById;
    }

    public void setOrderCustomerTblsById(Collection<OrderCustomerTblEntity> orderCustomerTblsById) {
        this.orderCustomerTblsById = orderCustomerTblsById;
    }

    @ManyToOne
    @JoinColumn(name = "vip_id", referencedColumnName = "id")
    public VipTblEntity getVipTblByVipId() {
        return vipTblByVipId;
    }

    public void setVipTblByVipId(VipTblEntity vipTblByVipId) {
        this.vipTblByVipId = vipTblByVipId;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false)
    public HotelTblEntity getHotelTblByHotelId() {
        return hotelTblByHotelId;
    }

    public void setHotelTblByHotelId(HotelTblEntity hotelTblByHotelId) {
        this.hotelTblByHotelId = hotelTblByHotelId;
    }

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "hotel_id", referencedColumnName = "hotel_id"),
            @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    })
    public RoomTblEntity getRoomTblByHotelIdAndRoomId() {
        return roomTblEntity;
    }

    public void setRoomTblByHotelIdAndRoomId(RoomTblEntity roomTblEntityByHotelIdAndRoomId) {
        this.roomTblEntity = roomTblEntityByHotelIdAndRoomId;
    }
}
