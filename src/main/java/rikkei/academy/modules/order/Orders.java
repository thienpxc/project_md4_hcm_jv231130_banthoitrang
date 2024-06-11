package rikkei.academy.modules.order;

import lombok.*;
import rikkei.academy.generic.Status;
import rikkei.academy.modules.customer.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

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
    private Integer order_id;
//    @ManyToOne
////    @JoinColumn(name = "customer_id")
////    private Customer customer_id;
    private Date order_date;
    private String total_amount;
    private String shipping_address;
    private String payment_method;
    private OderStatus order_status;
    private Status shipping_method;
    private LocalDateTime order_at;
    private LocalDateTime deliver_at;
}
