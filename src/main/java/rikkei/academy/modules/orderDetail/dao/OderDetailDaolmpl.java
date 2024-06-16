package rikkei.academy.modules.orderDetail.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.models.Orders;
import rikkei.academy.modules.orderDetail.OrderDetail;

import org.hibernate.query.Query;
import rikkei.academy.modules.products.Product;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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
    public OrderDetail deleteOrderDetail(OrderDetail orderDetail) {
        Session session = sessionFactory.getCurrentSession();

        // Retrieve the Order object
        Orders order = orderDetail.getOrderId();

        // Remove the OrderDetail from the Order
        order.getOrderDetails().remove(orderDetail);

        // Delete the OrderDetail
        session.delete(orderDetail);

        return orderDetail;
    }
    public OrderDetail findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(OrderDetail.class, id);
    }
    public OrderDetail changeQuantity(int orderItemId, int change) {
        Session session = sessionFactory.getCurrentSession();
        OrderDetail orderItem = findById(orderItemId);
        int newQuantity = Math.max(1, orderItem.getQuantity() + change);
        double newPrice = newQuantity * orderItem.getProductId().getPrice();

        orderItem.setQuantity(newQuantity);
        orderItem.setPrice(newPrice);
        session.save(orderItem);
        return orderItem;
    }
    public double calculateTotalPrice() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT SUM(price) FROM OrderDetail");
        Double totalPrice = (Double) query.uniqueResult();
        return (totalPrice != null) ? totalPrice : 0.0;
    }

    public int calculateCartQuantity() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT SUM(quantity) FROM OrderDetail");
        Long quantity = (Long) query.uniqueResult();
        return (quantity != null) ? quantity.intValue() : 0;
    }



}
