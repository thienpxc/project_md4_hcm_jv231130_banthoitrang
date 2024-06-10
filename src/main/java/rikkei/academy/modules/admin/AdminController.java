package rikkei.academy.modules.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping()
    public String home(){

        return "admin/index1";
    }

}
