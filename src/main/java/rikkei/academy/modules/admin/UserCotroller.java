package rikkei.academy.modules.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class UserCotroller {
    //user
    @GetMapping("user")
    public String user(){
        return "admin/user/user";
    }
}
