package rikkei.academy.modules.order.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikkei.academy.modules.customer.Customer;

import rikkei.academy.modules.order.models.OrderStatusCount;
import rikkei.academy.modules.order.models.Orders;

import java.util.ArrayList;

import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;

import javax.transaction.Transactional;

import java.util.List;

@Repository
public class OrderDaolmpl implements IOrderDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private OderDetailDaolmpl orderRepository;


    public Orders saveOrder(Orders order) {
        Session session = sessionFactory.getCurrentSession();
        session.save(order);
        return order;
    }
    public Orders findOrderByCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Query<Orders> query = session.createQuery("from Orders where status = true and customer = :customer", Orders.class);
        query.setParameter("customer", customer);
        return query.uniqueResult();
    }


    @Override
    public List<Orders> findByPagination(Integer page, Integer limit, String status) {
        Session session = sessionFactory.getCurrentSession();
        // Kiểm tra page >= 1
        if (page < 1) {
            page = 1; // Hoặc ném ra một ngoại lệ tùy ý
        }
        // Tính offset
        int offset = (page - 1) * limit;
        // Tạo câu lệnh truy vấn
        if (status.isEmpty()) {
            Query<Orders> query = session.createQuery("from Orders", Orders.class);
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.list();
        } else {
            Query<Orders> query = session.createQuery("from Orders where orderStatus = :status order by orderAt desc", Orders.class);
            query.setParameter("status", Orders.OderStatus.valueOf(status));
            query.setFirstResult(offset);
            query.setMaxResults(limit);
            return query.list();
        }
    }

    public long getTotalsElement() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Orders", Long.class).uniqueResult();
    }

    @Override
    public List<OrderStatusCount> findQuantityOfStatus() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "select orderStatus, count(orderStatus) from Orders group by orderStatus";
        Query query = session.createQuery(hql);
        List<Object[]> resultList = query.list();

        List<OrderStatusCount> orderStatusCounts = new ArrayList<>();
        for (Object[] result : resultList) {
            String orderStatusName = result[0].toString();
            long count = (long) result[1];
            orderStatusCounts.add(new OrderStatusCount(orderStatusName, count));
        }
        return orderStatusCounts;
    }

    @Override
    public Orders findOrderById(Integer orderId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Orders.class, orderId);
    }

    @Override
    public void updateOrder(Orders order) {
        Session session = sessionFactory.getCurrentSession();
        session.update(order);
    }

    public void updateCustomerAndOrders(Customer customer, List<Orders> orders) {
        // Lấy session hiện tại từ sessionFactory
        Session session = sessionFactory.getCurrentSession();

        // Cập nhật thông tin của Customer
        session.update(customer);

        // Cập nhật thông tin của mỗi Order trong danh sách orders
        for (Orders order : orders) {
            session.update(order);
        }

    }

    public Orders findPendingOrderByCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Query<Orders> query = session.createQuery("from Orders where customer = :customer and orderStatus = 'PENDING'", Orders.class);
        query.setParameter("customer", customer);
        return query.uniqueResult();
    }

    public boolean hasPendingOrderForCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        Query<Long> query = session.createQuery("select count(o) from Orders o where o.customer = :customer and o.orderStatus = 'PENDING'", Long.class);
        query.setParameter("customer", customer);
        long count = query.uniqueResult();
        return count > 0;
    }
    public void updateCustomer(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(customer);
    }






}
