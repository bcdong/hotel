package hotel.type;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public enum OrderState {
    BOOK, IN, LEAVE;

    OrderState() {
    }

    public static OrderState str2Enum(String str) {
        OrderState e = BOOK;
        switch (str) {
            case "BOOK": e = BOOK;break;
            case "IN":e = IN;break;
            case "LEAVE":e = LEAVE;break;
        }
        return e;
    }
}
