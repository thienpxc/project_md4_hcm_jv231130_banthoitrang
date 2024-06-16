package rikkei.academy.modules.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.dao.IUserDao;
import rikkei.academy.modules.customer.dao.UserDaolmpl;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;
import rikkei.academy.modules.products.Product;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServicelmpl implements IUserService {
    @Autowired
    private IUserDao userDao;
    @Override
    public Customer getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public void save(Customer customer) {
        userDao.save(customer);
    }
    @Override
    public Customer converFormRegisterToUser(RegisterForm form) {
        return new Customer(null, form.getUsername(), form.getEmail(), form.getPassword(), null, null, null, false, true, new Date(),null, null);
    }
    public long getTotalsElement(){
        return userDao.getTotalsElement();
    }
    @Override
    public List<Customer> findByPagination(Integer page, Integer limit) {
        return userDao.findByPagination(page, limit);
    }
    @Override
    public void chanRole(Integer id) {
        userDao.chanRole(id);
    }
}
