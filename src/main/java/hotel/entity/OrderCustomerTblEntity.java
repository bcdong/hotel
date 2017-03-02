package hotel.entity;

import javax.persistence.*;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Entity
@Table(name = "order_customer_tbl", schema = "hotel", catalog = "")
@IdClass(OrderCustomerTblEntityPK.class)
public class OrderCustomerTblEntity {
    private int orderId;
    private String customerId;
    private String customerName;
    private OrderTblEntity orderTblByOrderId;

    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "customer_id", nullable = false, length = 32)
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "customer_name", nullable = true, length = 32)
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderCustomerTblEntity that = (OrderCustomerTblEntity) o;

        if (orderId != that.orderId) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    public OrderTblEntity getOrderTblByOrderId() {
        return orderTblByOrderId;
    }

    public void setOrderTblByOrderId(OrderTblEntity orderTblByOrderId) {
        this.orderTblByOrderId = orderTblByOrderId;
    }
}
