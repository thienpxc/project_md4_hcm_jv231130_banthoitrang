package rikkei.academy.modules.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.service.IProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryControllers {
    @Autowired
    private IProductService productService;
    @GetMapping("/products")
    public String showProductsByCategory(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "limit",defaultValue = "9") Integer limit,
                                         Model model) {
        long totalElements = productService.getTotalsElement();
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;
        List<Product> products ;
        if (categoryId != null) {
            products = productService.findByCategory(categoryId);
        } else {
            products = productService.findByPagination(page, limit);
        }
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("products", products);
        return "/customer/shop/product/productShop";
    }


}
