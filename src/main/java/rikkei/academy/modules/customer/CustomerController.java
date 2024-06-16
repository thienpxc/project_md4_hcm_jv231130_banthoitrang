package rikkei.academy.modules.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.modules.category.service.ICategoryService;
import rikkei.academy.modules.orderDetail.service.OrderDetailService;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.service.IProductService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = {"/customer", ""})
public class CustomerController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    @Autowired
    private OrderDetailService orderDetailService;
    @RequestMapping(value = {"/", ""})
    public String index(HttpSession session, Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                        @RequestParam(value = "size", defaultValue = "8") Integer limit) {
        long totalElements = productService.getTotalsElement();
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;

        List<Product> product = productService.findByPagination(page, limit, null);

        List<Product> productHome = productService.findNewestProduct(page, limit);


        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        Customer customer = (Customer) session.getAttribute("loginUser");
        model.addAttribute("customer", customer);
        model.addAttribute("productHome",productHome);
        model.addAttribute("products",product);
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
