package rikkei.academy.modules.order.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderStatusCount {
    private String status;
    private long count;
}
