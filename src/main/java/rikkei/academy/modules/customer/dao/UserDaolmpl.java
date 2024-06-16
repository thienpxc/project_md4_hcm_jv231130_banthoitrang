package rikkei.academy.modules.customer.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.dto.loginDto.LoginForm;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;

import java.util.Date;
import java.util.List;

@Repository
public class UserDaolmpl implements IUserDao{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void register(RegisterForm registerForm) {

    }

    @Override
    public Customer login(String token) {
        return new Customer();
    }

    @Override
    public Customer getUserByUserName(String userName) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "from Customer where customerName = :userName";
        return (Customer) session.createQuery(sql).setParameter("userName", userName).getSingleResult();
    }

    @Override
    public void save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        session.save(customer);
    }

    @Override
    public long getTotalsElement() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select count(*) from Customer where role = false";
        return (long) session.createQuery(sql).getSingleResult();
    }

    @Override
    public List<Customer> findByPagination(Integer page, Integer size) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Customer where role = false and status = true", Customer.class)
                .setMaxResults(size)
                .setFirstResult(page*size)
                .list();
    }
    @Override
    public void chanRole(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Customer customer = session.get(Customer.class, id);
        customer.setStatus(false);
        session.update(customer);
    }
}
