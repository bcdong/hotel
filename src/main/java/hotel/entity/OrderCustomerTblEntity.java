package hotel.entity;

import javax.persistence.*;

/**
 * Created by Mr.Zero on 2017/3/2.
 */
@Entity
@Table(name = "order_customer_tbl", schema = "hotel", catalog = "")
public class OrderCustomerTblEntity {
    private int id;
    private String customerId;
    private String customerName;
    private OrderTblEntity orderTblByOrderId;

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

        if (id != that.id) return false;
        if (customerId != null ? !customerId.equals(that.customerId) : that.customerId != null) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
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
