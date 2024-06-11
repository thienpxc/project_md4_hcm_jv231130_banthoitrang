package rikkei.academy.modules.validate.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.dto.loginDto.LoginForm;
import rikkei.academy.modules.customer.service.UserServicelmpl;

import java.util.List;

@Component
public class LoginFormValidate implements Validator {
    @Autowired
    private UserServicelmpl userService;
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm l = (LoginForm) target;
        List<Customer> u = userService.getAll();
        if (l.getUsername().isEmpty()) {
            errors.rejectValue("userName", "mess.err.empty", "Ten dang nhap khong duoc de trong");
        }
        if (l.getPassword().isEmpty()) {
            errors.rejectValue("password", "mess.err.empty", "Mat khau khong duoc de trong");
        }
        if (u.isEmpty()) {
            errors.rejectValue("userName", "mess.err.correct", "Ten dang nhap hoac mat khau khong dung");
            errors.rejectValue("password", "mess.err.correct", "Ten dang nhap hoac mat khau khong dung");
        } else if (u.stream().filter(a -> a.getCustomer_name().equals(l.getUsername()) && a.getPassword().equals(l.getPassword()) && a.getStatus()).findFirst().orElse(null) == null) {
            errors.rejectValue("password", "mess.err.correct", "Ten dang nhap hoac mat khau khong dung");
            errors.rejectValue("userName", "mess.err.correct", "Ten dang nhap hoac mat khau khong dung");
        }
    }
}
