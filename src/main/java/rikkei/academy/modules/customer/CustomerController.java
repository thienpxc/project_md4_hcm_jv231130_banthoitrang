package rikkei.academy.modules.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rikkei.academy.modules.category.service.ICategoryService;
import rikkei.academy.modules.products.service.IProductService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = {"/customer", ""})
public class CustomerController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    @RequestMapping(value = {"/", ""})
    public String index(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("loginUser");
        model.addAttribute("customer", customer);
        model.addAttribute("productHome",productService.findAllProduct());
        model.addAttribute("categoryHome",categoryService.findAllCategory());
        return "index";
    }
    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("loginUser");
        if (customer != null) {
            model.addAttribute("customer", customer);
            return "customer/shop/profile";
        } else {
            return "redirect:/auth";
        }
    }
    @GetMapping("/profile/edit")
    public String showRegistrationForm(HttpSession session,Model model) {
        Customer customer = (Customer) session.getAttribute("loginUser");
        model.addAttribute("customer", customer);

        return "customer/shop/profile/edit";
    }
    @GetMapping("/profile/order")
    public String login(HttpSession session,Model model) {
        Customer customer = (Customer) session.getAttribute("loginUser");
        model.addAttribute("customer", customer);

        return "customer/shop/profile/order";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginUser");
        return "redirect:/";
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
    @GetMapping("/product")
    public String shop() {
        return "customer/shop/shop";
    }
    @GetMapping("/detail")
    public String detail() {
        return "customer/shop/detail";
    }

}
