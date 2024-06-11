package rikkei.academy.modules.customer.dao;

import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;

import java.util.List;

public interface IUserDao {
    List<Customer> getAll();
    void register(RegisterForm registerForm);
    Customer login (String token);
    Customer getUserByUserName(String userName);
    void save(Customer customer);
}
