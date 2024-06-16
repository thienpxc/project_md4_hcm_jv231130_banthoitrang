package rikkei.academy.modules.order.dao;

import rikkei.academy.modules.order.models.OrderStatusCount;
import rikkei.academy.modules.order.models.Orders;

import java.util.List;

public interface IOrderDao {
    List<Orders> findByPagination(Integer page , Integer limit, String status);
    long getTotalsElement();
    public List<OrderStatusCount> findQuantityOfStatus();

    Orders findOrderById(Integer orderId);

    void updateOrder(Orders order);
}
