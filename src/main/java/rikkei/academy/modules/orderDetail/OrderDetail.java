package rikkei.academy.modules.orderDetail;
import rikkei.academy.modules.order.Orders;
import lombok.*;
import rikkei.academy.modules.products.Product;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "OrderDetail")
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orderId;
    @ManyToOne
    @JoinColumn(name = "productId")
    private Product productId;
    private double price;
    private int quantity;
}
