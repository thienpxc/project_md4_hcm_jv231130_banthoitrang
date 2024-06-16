package rikkei.academy.modules.customer.service;

import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;

import java.util.List;

public interface IUserService {
    Customer getUserByUserName(String userName);
    void save(Customer registerForm);
    Customer converFormRegisterToUser(RegisterForm form);
    public long getTotalsElement();
    public List<Customer> findByPagination(Integer page, Integer limit);
    void chanRole(Integer id);
}
