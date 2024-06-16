package rikkei.academy.modules.order.models;

import lombok.*;
import rikkei.academy.generic.Status;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.orderDetail.OrderDetail;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "Orders")
@Entity
public class Orders {

    public enum OderStatus {
        PENDING,

        WAITING,

        CONFIRMED,
        SHIPPING,
        DELIVERED,
        CANCELLED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private Date orderDate;
    private String totalAmount;
    private String shippingAddress;
    private String paymentMethod;
    @Enumerated(EnumType.STRING)
    private OderStatus orderStatus = OderStatus.PENDING;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    private String shippingMethod;
    @OneToMany(mappedBy = "orderId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
    private LocalDateTime orderAt;
    private LocalDateTime deliverAt;
    private Boolean status ;

    public Orders(Customer customer) {
        this.customer = customer;
        this.orderDate = new Date();
        this.totalAmount = "0";
        this.shippingAddress = "";
        this.paymentMethod = "";
        this.orderStatus = OderStatus.PENDING;
        this.shippingMethod = "";
        this.orderDetails = new ArrayList<>();
        this.orderAt = LocalDateTime.now();
        this.deliverAt = null;
        this.status = true;

    }
}
