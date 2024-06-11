package rikkei.academy.modules.customer.service;

import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;

import java.util.List;

public interface IUserService {
    List<Customer> getAll();
    Customer getUserByUserName(String userName);
    void save(RegisterForm registerForm);
    Customer converFormRegisterToUser(RegisterForm form);
}
