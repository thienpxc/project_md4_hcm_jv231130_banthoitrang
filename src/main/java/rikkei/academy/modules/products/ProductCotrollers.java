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

    @GetMapping("/showProducts")
    public String showProducts(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "9") Integer limit) {
        long totalElements = productService.getTotalsElement();
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        List<Product> products = productService.findByPagination(page, limit);
        System.out.println("Products: " + products); // Thêm dòng log này
        model.addAttribute("products", products);
        return "/customer/shop/shop";
    }


    @GetMapping("/product/{id}")
    public String getProductDetails(@PathVariable Integer id, Model model) {
        List<Product> products = productService.findAllProduct();

        Product product = productService.findById(id);
        if (product != null) {

            model.addAttribute("product", product);
        }

        if (products.size() >= 10) {
            List<Product> firstSixProducts = products.subList(0, 6);
            model.addAttribute("firstSixProducts", firstSixProducts);

            // Lấy 6 sản phẩm tiếp theo
            List<Product> nextSixProducts = products.subList(6, 10);
            model.addAttribute("nextSixProducts", nextSixProducts);

        }else {
            model.addAttribute("firstSixProducts", products);
        }

        return "/customer/shop/detail";
    }

    @GetMapping("/productadd/{id}")
    public String showIdProducts(Model model) {
        List<Product> products = productService.findAllProduct();
        model.addAttribute("products", products);
        return "/customer/shop/cart";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchByName(keyword);
        model.addAttribute("products", products);
        return "/customer/shop/product/search";
    }
    @PostMapping("/search")
    public String postSearchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchByName(keyword);
        model.addAttribute("products", products);
        return "redirect:/search?keyword=" + keyword;
    }







}
