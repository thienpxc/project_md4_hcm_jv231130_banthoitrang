package rikkei.academy.modules.order.service;

import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.models.OrderStatusCount;
import rikkei.academy.modules.order.models.Orders;
import rikkei.academy.modules.orderDetail.OrderDetail;

import java.util.List;

public interface IOrderService {
    Orders findOrderByCustomer(Customer customer);

    List<Orders> findByPagination(Integer page, Integer limit, String status);

    void saveOrderAndDetails(Orders order, List<OrderDetail> orderDetails);

    void updateCustomerAndOrders(Customer customer, List<Orders> orders);
    Orders saveOrder(Orders order);

    void updateCustomer(Customer customer);

    boolean hasPendingOrderForCustomer(Customer customer);

    Orders findPendingOrderByCustomer(Customer customer);

    long getTotalsElement();

    public List<OrderStatusCount> findQuantityOfStatus();

    void confirmOrder(Integer orderId);

    void shippingOrder(Integer orderId);

    void deliveredOrder(Integer orderId);

    void cancelOrder(Integer orderId);
}
