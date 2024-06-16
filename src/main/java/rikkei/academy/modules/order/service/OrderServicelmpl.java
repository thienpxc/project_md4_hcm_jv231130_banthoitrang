package rikkei.academy.modules.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.models.OrderStatusCount;
import rikkei.academy.modules.order.models.Orders;
import rikkei.academy.modules.order.dao.OrderDaolmpl;
import rikkei.academy.modules.orderDetail.OrderDetail;
import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderServicelmpl implements IOrderService {
    @Autowired
    private OrderDaolmpl ordersDao;

    @Autowired
    private OderDetailDaolmpl orderDetailDao;


    public void saveOrderAndDetails(Orders order, List<OrderDetail> orderDetails) {
        Orders savedOrder = ordersDao.saveOrder(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(savedOrder);
            orderDetailDao.saveOrderDetail(orderDetail);
        }
    }

    public Orders saveOrder(Orders order) {
        return ordersDao.saveOrder(order);

    }

    public Orders findOrderByCustomer(Customer customer) {
        return ordersDao.findOrderByCustomer(customer);
    }


    @Override
    public List<Orders> findByPagination(Integer page, Integer limit, String status) {
        return ordersDao.findByPagination(page, limit, status);
    }

    @Override
    public long getTotalsElement() {
        return ordersDao.getTotalsElement();
    }

    @Override
    public List<OrderStatusCount> findQuantityOfStatus() {
        return ordersDao.findQuantityOfStatus();
    }

    @Override
    public void confirmOrder(Integer orderId) {
        Orders order = ordersDao.findOrderById(orderId);
        order.setOrderStatus(Orders.OderStatus.CONFIRMED);
        ordersDao.updateOrder(order);
    }

    @Override
    public void shippingOrder(Integer orderId) {
        Orders order = ordersDao.findOrderById(orderId);
        order.setOrderStatus(Orders.OderStatus.SHIPPING);
        ordersDao.updateOrder(order);
    }

    @Override
    public void deliveredOrder(Integer orderId) {
        Orders order = ordersDao.findOrderById(orderId);
        order.setOrderStatus(Orders.OderStatus.DELIVERED);
        order.setDeliverAt(LocalDateTime.now());
        ordersDao.updateOrder(order);
    }

    @Override
    public void cancelOrder(Integer orderId) {
        Orders order = ordersDao.findOrderById(orderId);
        order.setOrderStatus(Orders.OderStatus.CANCELLED);
        ordersDao.updateOrder(order);
    }
    public void updateCustomerAndOrders(Customer customer, List<Orders> orders){
        ordersDao.updateCustomerAndOrders(customer, orders);
    }

    public Orders findPendingOrderByCustomer(Customer customer) {
        return ordersDao.findPendingOrderByCustomer(customer);
    }

    public boolean hasPendingOrderForCustomer(Customer customer) {
        return ordersDao.hasPendingOrderForCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        ordersDao.updateCustomer(customer);

    }

}
