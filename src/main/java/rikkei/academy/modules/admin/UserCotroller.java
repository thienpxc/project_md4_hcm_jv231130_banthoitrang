package rikkei.academy.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.customer.service.IUserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class UserCotroller {
    @Autowired
    private IUserService userService;

    //user
    @GetMapping("user")
    public String user(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "limit", defaultValue = "3") Integer limit, Model model) {
        long totalElements = userService.getTotalsElement();
        System.out.println("totalElements" + totalElements);
        if (page < 0) {
            page = 0;
        }
        if (page > totalElements) {
            page = (int) totalElements;
        }
        long nguyen = totalElements / limit;
        long du = totalElements % limit;
        long totalPages = du == 0 ? nguyen : nguyen + 1;
        List<Customer> customer = userService.findByPagination(page, limit);
        System.out.println("customer" + customer.size());
        System.out.println(customer.toString());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("page", page);
        model.addAttribute("limit", limit);
        model.addAttribute("users", customer);
        return "admin/user/user";
    }

    @GetMapping("user/chanrole")
    public String chanRole(@RequestParam("id") Integer id) {
        userService.chanRole(id);
        return "redirect:/admin/user";
    }
}
