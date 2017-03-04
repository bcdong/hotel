package hotel.vo;

import hotel.type.ManagerType;

/**
 * Created by Mr.Zero on 2017/3/4.
 */
public class ManagerVO {
    private int id;
    private String name;
    private ManagerType type;
    private HotelVO hotel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }

    public HotelVO getHotel() {
        return hotel;
    }

    public void setHotel(HotelVO hotel) {
        this.hotel = hotel;
    }
}
