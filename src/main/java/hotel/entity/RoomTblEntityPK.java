package hotel.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public class RoomTblEntityPK implements Serializable {
    private String hotelId;
    private String roomId;

    @Column(name = "hotel_id", nullable = false, length = 16)
    @Id
    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Column(name = "room_id", nullable = false, length = 16)
    @Id
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomTblEntityPK that = (RoomTblEntityPK) o;

        if (hotelId != null ? !hotelId.equals(that.hotelId) : that.hotelId != null) return false;
        if (roomId != null ? !roomId.equals(that.roomId) : that.roomId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hotelId != null ? hotelId.hashCode() : 0;
        result = 31 * result + (roomId != null ? roomId.hashCode() : 0);
        return result;
    }
}
