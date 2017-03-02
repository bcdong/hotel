package hotel.entity;

import hotel.type.RoomState;
import hotel.type.RoomType;

import javax.persistence.*;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Entity
@Table(name = "room_tbl", schema = "hotel", catalog = "")
@IdClass(RoomTblEntityPK.class)
public class RoomTblEntity {
    private int hotelId;
    private String roomId;
    private RoomState state;
    private RoomType type;
    private Integer capacity;
    private HotelTblEntity hotelTblByHotelId;

    @Id
    @Column(name = "hotel_id", nullable = false)
    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    @Id
    @Column(name = "room_id", nullable = false, length = 16)
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Basic
    @Enumerated(value = EnumType.STRING)
    @Column(name = "state", nullable = false, length = 32)
    public RoomState getState() {
        return state;
    }

    public void setState(RoomState state) {
        this.state = state;
    }

    @Basic
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type", nullable = true, length = 32)
    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    @Basic
    @Column(name = "capacity", nullable = true)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomTblEntity that = (RoomTblEntity) o;

        if (hotelId != that.hotelId) return false;
        if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (capacity != null ? !capacity.equals(that.capacity) : that.capacity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelId;
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (capacity != null ? capacity.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id", nullable = false)
    public HotelTblEntity getHotelTblByHotelId() {
        return hotelTblByHotelId;
    }

    public void setHotelTblByHotelId(HotelTblEntity hotelTblByHotelId) {
        this.hotelTblByHotelId = hotelTblByHotelId;
    }
}
