package rikkei.academy.modules.order;

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
        CONFIRMED,
        SHIPPING,
        DELIVERED,
        CANCEL
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
    private Status shippingMethod = Status.WAITING;
    @OneToMany(mappedBy = "orderId",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;
    private LocalDateTime orderAt;
    private LocalDateTime deliverAt;

    public Orders(Customer customer) {
        this.customer = customer;
        this.orderDate = new Date(); // Khởi tạo ngày đặt hàng là ngày hiện tại
        this.totalAmount = "0"; // Khởi tạo tổng số tiền là 0
        this.shippingAddress = ""; // Khởi tạo địa chỉ giao hàng là rỗng
        this.paymentMethod = ""; // Khởi tạo phương thức thanh toán là rỗng
        this.orderStatus = OderStatus.PENDING; // Khởi tạo trạng thái đơn hàng là PENDING
        this.shippingMethod = Status.WAITING; // Khởi tạo phương thức giao hàng là WAITING
        this.orderDetails = new ArrayList<>(); // Khởi tạo danh sách chi tiết đơn hàng là rỗng
        this.orderAt = LocalDateTime.now(); // Khởi tạo thời gian đặt hàng là thời gian hiện tại
        this.deliverAt = null; // Khởi tạo thời gian giao hàng là null
    }
}
