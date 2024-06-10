package rikkei.academy.modules.orderDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderDetail {
    private Integer order_item_id;
    private Integer order_id;
    private String product_id;
    private double price;
    private int quantity;
}
