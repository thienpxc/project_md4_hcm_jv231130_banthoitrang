package rikkei.academy.modules.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rikkei.academy.generic.Status;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Order {
    private Integer order_id;
    private Integer customer_id;
    private String order_date;
    private String total_amount;
    private String shipping_address;
    private boolean payment_method;
    private double order_status;
    private Status shipping_method;
    private LocalDateTime order_at;
    private LocalDateTime deliver_at;
}
