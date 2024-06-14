package rikkei.academy.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.modules.customer.service.IUserService;

@Controller
@RequestMapping("/admin")
public class UserCotroller {
    @Autowired
    private IUserService userService;
    //user
    @GetMapping("user")
    public String user(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "limit",defaultValue = "3") Integer limit, Model model){
        long totalElements = userService.getTotalsElement();
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;


        return "admin/user/user";
    }
}
