package rikkei.academy.modules.orderDetail.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.Orders;
import rikkei.academy.modules.orderDetail.OrderDetail;

import org.hibernate.query.Query;
import rikkei.academy.modules.products.Product;

import java.util.List;

@Repository
public class OderDetailDaolmpl {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private OderDetailDaolmpl orderDetailDao;


    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        Session session = sessionFactory.getCurrentSession();
        session.save(orderDetail);
        return orderDetail;
    }
    public List<OrderDetail> getAllOrderDetails() {
        Session session = sessionFactory.getCurrentSession();
        Query<OrderDetail> query = session.createQuery("from OrderDetail", OrderDetail.class);
        return query.getResultList();
    }
//    public List<OrderDetail> findAllActiveByOrderId(int orderId) {
//        Session session = sessionFactory.getCurrentSession();
//        Query<OrderDetail> query = session.createQuery("from OrderDetail where status = true and orderId.id = :orderId", OrderDetail.class);
//        query.setParameter("orderId", orderId);
//        return query.getResultList();
//    }
public List<OrderDetail> findAllActiveByCustomerId(int customerId) {
    Session session = sessionFactory.getCurrentSession();
    // Retrieve the Customer object
    Customer customer = session.get(Customer.class, customerId);
    Query<OrderDetail> query = session.createQuery(
            "from OrderDetail od where od.status = true and od.orderId.customer = :customer",
            OrderDetail.class
    );
    query.setParameter("customer", customer);
    return query.getResultList();
}
    public OrderDetail findByOrderIdAndProductId(Orders order, Product product) {
        Session session = sessionFactory.getCurrentSession();
        Query<OrderDetail> query = session.createQuery("from OrderDetail where orderId = :order and productId = :product", OrderDetail.class);
        query.setParameter("order", order);
        query.setParameter("product", product);
        return query.uniqueResult();
    }

}
