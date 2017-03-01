package hotel.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Mr.Zero on 2017/3/1.
 */
public class OrderCustomerTblEntityPK implements Serializable {
    private int orderId;
    private String customerId;

    @Column(name = "order_id", nullable = false)
    @Id
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Column(name = "customer_id", nullable = false, length = 32)
    @Id
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderCustomerTblEntityPK that = (OrderCustomerTblEntityPK) o;

        if (orderId != that.orderId) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        return result;
    }
}
