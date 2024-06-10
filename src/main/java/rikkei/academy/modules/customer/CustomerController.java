package rikkei.academy.modules.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/customer", ""})
public class CustomerController {
    @GetMapping(value = {"/", ""})
    public String index() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "customer/shop/login/index";
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
