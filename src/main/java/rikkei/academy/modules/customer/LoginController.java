package rikkei.academy.modules.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.dto.loginDto.LoginForm;
import rikkei.academy.modules.customer.dto.loginDto.RegisterForm;
import rikkei.academy.modules.customer.service.IUserService;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private IUserService userService;



    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        RegisterForm registerForm = new RegisterForm();
        LoginForm loginForm = new LoginForm();
        model.addAttribute("registerForm", registerForm);
        model.addAttribute("loginForm", loginForm);
        return "customer/shop/login/login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        RegisterForm registerForm = new RegisterForm();
        model.addAttribute("registerForm", registerForm);
        return "customer/shop/login/create-account";
    }
    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute("loginForm") LoginForm form, HttpSession session) {
        Customer customer = userService.getUserByUserName(form.getUsername());
        session.setAttribute("loginUser", customer);
        session.setAttribute("login", "login");
        if(customer.getRole()){
            return "redirect:/admin"; // admin page
        }
        return "redirect:/"; // customer page
    }

    @PostMapping("/doRegister")
    public String doRegister(@ModelAttribute("registerForm") RegisterForm form) {
        Customer customer = new Customer();
        customer.setCustomerName(form.getUsername());
        customer.setPassword(form.getPassword());
        customer.setEmail(form.getEmail());
        customer.setPhoneNumber(form.getPhone());
        customer.setAddress(form.getAddress());
        customer.setAvatar(form.getAvatar());
        customer.setGender(form.getGender());
        userService.save(customer);
        return "redirect:/login";
    }

}
