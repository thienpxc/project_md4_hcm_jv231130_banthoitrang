package rikkei.academy.modules.customer.dao;

import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.dto.loginDto.LoginForm;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;

import java.util.List;

public interface IUserDao {
    void register(RegisterForm registerForm);
    Customer login (String token);
    Customer getUserByUserName(String userName);
    void save(Customer customer);
    public long getTotalsElement();
    public List<Customer> findByPagination(Integer page, Integer limit);
}
