package hotel.vo;

import javax.validation.constraints.Min;

/**
 * Created by Mr.Zero on 2017/3/5.
 */
public class HotelPlanForm {
    @Min(value = 0, message = "房间数量不能小于0")
    private int singleRoomCount;
    @Min(value = 0, message = "房间价格不能小于0")
    private double singleRoomPrice;
    @Min(value = 0, message = "房间数量不能小于0")
    private int doubleRoomCount;
    @Min(value = 0, message = "房间价格不能小于0")
    private double doubleRoomPrice;
    @Min(value = 0, message = "房间数量不能小于0")
    private int tripleRoomCount;
    @Min(value = 0, message = "房间价格不能小于0")
    private double tripleRoomPrice;

    private String hotelId;

    public int getSingleRoomCount() {
        return singleRoomCount;
    }

    public void setSingleRoomCount(int singleRoomCount) {
        this.singleRoomCount = singleRoomCount;
    }

    public double getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(double singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    public int getDoubleRoomCount() {
        return doubleRoomCount;
    }

    public void setDoubleRoomCount(int doubleRoomCount) {
        this.doubleRoomCount = doubleRoomCount;
    }

    public double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(double doubleRoomPrice) {
        this.doubleRoomPrice = doubleRoomPrice;
    }

    public int getTripleRoomCount() {
        return tripleRoomCount;
    }

    public void setTripleRoomCount(int tripleRoomCount) {
        this.tripleRoomCount = tripleRoomCount;
    }

    public double getTripleRoomPrice() {
        return tripleRoomPrice;
    }

    public void setTripleRoomPrice(double tripleRoomPrice) {
        this.tripleRoomPrice = tripleRoomPrice;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
