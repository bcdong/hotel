package hotel.entity;

import hotel.type.RoomType;

import javax.persistence.*;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
@Entity
@Table(name = "plan_tbl", schema = "hotel", catalog = "")
public class PlanTblEntity {
    private int id;
    private RoomType roomType;
    private Integer roomCount;
    private Double roomPrice;
    private HotelTblEntity hotelTblByHotelId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = true, length = 32)
    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Basic
    @Column(name = "room_count", nullable = true)
    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    @Basic
    @Column(name = "room_price", nullable = true, precision = 0)
    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanTblEntity that = (PlanTblEntity) o;

        if (id != that.id) return false;
        if (roomType != null ? !roomType.equals(that.roomType) : that.roomType != null) return false;
        if (roomCount != null ? !roomCount.equals(that.roomCount) : that.roomCount != null) return false;
        if (roomPrice != null ? !roomPrice.equals(that.roomPrice) : that.roomPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (roomCount != null ? roomCount.hashCode() : 0);
        result = 31 * result + (roomPrice != null ? roomPrice.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false)
    public HotelTblEntity getHotelTblByHotelId() {
        return hotelTblByHotelId;
    }

    public void setHotelTblByHotelId(HotelTblEntity hotelTblByHotelId) {
        this.hotelTblByHotelId = hotelTblByHotelId;
    }
}
