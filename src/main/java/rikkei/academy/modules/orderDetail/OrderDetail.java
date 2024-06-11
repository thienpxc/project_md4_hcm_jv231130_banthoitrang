package rikkei.academy.modules.orderDetail;

import lombok.*;

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
    private Integer order_item_id;
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order_id;
    private String product_id;
    private double price;
    private int quantity;
}
