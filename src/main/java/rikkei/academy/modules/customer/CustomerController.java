package rikkei.academy.modules.customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rikkei.academy.modules.customer.dto.loginDto.LoginForm;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;

@Controller
@RequestMapping(value = {"/customer", ""})
public class CustomerController {
    @RequestMapping(value = {"/", ""})
    public String index() {
        return "index";
    }
    @GetMapping("/cart")
    public String cart() {
        return "customer/shop/cart";
    }
    @GetMapping("/checkout")
    public String checkout() {
        return "customer/shop/checkout";
    }
    @GetMapping("/contact")
    public String contact() {
        return "customer/shop/contact";
    }

    @GetMapping("/shop")
    public String shop() {
        return "customer/shop/shop";
    }
    @GetMapping("/detail")
    public String detail() {
        return "customer/shop/detail";
    }

}
