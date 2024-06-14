package rikkei.academy.modules.order.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.Orders;
import rikkei.academy.modules.order.dao.OrderDaolmpl;
import rikkei.academy.modules.orderDetail.OrderDetail;
import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;
import org.hibernate.query.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServicelmpl {
    @Autowired
    private OrderDaolmpl ordersDao;
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private OderDetailDaolmpl orderDetailDao;

    @Transactional
    public void saveOrderAndDetails(Orders order, List<OrderDetail> orderDetails) {
        Orders savedOrder = ordersDao.saveOrder(order);
        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setOrderId(savedOrder);
            orderDetailDao.saveOrderDetail(orderDetail);
        }
    }
    public Orders findOrderByCustomer(Customer customer) {
        return ordersDao.findOrderByCustomer(customer);
    }

}
