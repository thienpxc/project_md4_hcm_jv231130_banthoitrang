package rikkei.academy.modules.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.modules.orderDetail.OrderDetail;
import rikkei.academy.modules.products.dto.response.ProductResponse;
import rikkei.academy.modules.products.service.IProductService;

import java.util.List;

@Controller
@RequestMapping
public class ProductCotrollers {
    @Autowired
    private IProductService productService;

    @GetMapping("/product/{id}")
    public String getProductDetails(@PathVariable Integer id, Model model) {
        List<Product> products = productService.findAllProduct();

        Product product = productService.findById(id);
        if (product != null) {

            model.addAttribute("product", product);
        }
        // Kiểm tra xem có đủ 10 sản phẩm không
        if (products.size() > 10) {
            // Nếu có, chỉ lấy 10 sản phẩm đầu tiên
            products = products.subList(0, 10);
        }

        model.addAttribute("products", products);

        return "/customer/shop/detail";
    }

    @GetMapping("/productadd/{id}")
    public String showProducts(Model model) {
        List<Product> products = productService.findAllProduct();
        model.addAttribute("products", products);
        return "/customer/shop/cart";
    }

//    @GetMapping("/productadd/{id}")
//    public String showProducts(Model model) {
//        List<Product> products = productService.findAllProduct();
//        model.addAttribute("products", products);
//        return "/customer/shop/cart";
//    }


}
