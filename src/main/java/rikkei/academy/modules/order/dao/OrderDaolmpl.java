package rikkei.academy.modules.order.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.Orders;

@Repository
public class OrderDaolmpl {
    @Autowired
    private SessionFactory sessionFactory;

    public Orders saveOrder(Orders order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
        return order;
    }
    public Orders findOrderByCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Query<Orders> query = session.createQuery("from Orders where customer = :customer", Orders.class);
        query.setParameter("customer", customer);
        return query.uniqueResult();
    }

}
