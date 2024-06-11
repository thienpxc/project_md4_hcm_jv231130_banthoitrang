package rikkei.academy.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.modules.products.dto.request.ProductRequest;
import rikkei.academy.modules.products.service.IProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IProductService productService;
    @GetMapping()
    public String home(){

        return "admin/index1";
    }
    @GetMapping("/product")
    public String product(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "limit",defaultValue = "3") Integer limit, Model model){
        long totalElements = productService.getTotalsElement();
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;

        model.addAttribute("products",productService.findByPagination(page,limit));
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("product",new ProductRequest());


        return "admin/product/product";
    }


}
