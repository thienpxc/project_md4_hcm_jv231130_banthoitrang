package rikkei.academy.modules.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rikkei.academy.modules.category.service.ICategoryService;
import rikkei.academy.modules.customer.dto.loginDto.LoginForm;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;
import rikkei.academy.modules.products.service.IProductService;

@Controller
@RequestMapping(value = {"/customer", ""})
public class CustomerController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    @RequestMapping(value = {"/", ""})
    public String index(Model model) {
        model.addAttribute("productHome",productService.findAllProduct());
        model.addAttribute("categoryHome",categoryService.findAllCategory());
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
