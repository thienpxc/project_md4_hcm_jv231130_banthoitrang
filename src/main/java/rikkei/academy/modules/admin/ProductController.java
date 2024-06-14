package rikkei.academy.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.category.service.ICategoryService;
import rikkei.academy.modules.products.Product;

import rikkei.academy.modules.products.dto.request.ProductRequestAdd;
import rikkei.academy.modules.products.dto.request.ProductRequestUpdate;

import rikkei.academy.modules.products.service.IProductService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private IProductService productService;
    @GetMapping("")
    public String home(){
        return "admin/index";
    }
    @GetMapping("product")
    public String product(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "limit",defaultValue = "3") Integer limit, Model model){
        long totalElements = productService.getTotalsElement();
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;
        model.addAttribute("products",productService.findByPagination(page,limit));
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("product",new ProductRequestAdd());
        return "admin/product/product";
    }
    @GetMapping("product/add")
    public String doAdd(Model model){
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("product",new ProductRequestUpdate());
        return "/admin/product/add";
    }
    @PostMapping("product/add")
    public String addProduct(@Valid @ModelAttribute("product") ProductRequestAdd request, BindingResult result, Model model){
        List<Category> categories = categoryService.findAllCategory();
        System.out.println("request = " + request.toString());
       if (result.hasErrors()) {
           model.addAttribute("categories",categories);
           model.addAttribute("product",request);
           return "admin/product/add";
       }
        productService.save(request);
        return "redirect:/admin/product";
    }

    @GetMapping("product/edit")

    public String editProduct( @RequestParam ("id") Integer id, Model model){
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAllCategory();
        ProductRequestUpdate productRequestUpdate = productService.updatePro(product);
        model.addAttribute("categories",categories);
        model.addAttribute("productEdit", productRequestUpdate);

        return "admin/product/edit";
    }

    @PostMapping("product/edit")
    public String doEdit(@Valid @ModelAttribute("productEdit") ProductRequestUpdate request, BindingResult result, Model model){
        System.out.println("request" + request.toString());
        Product product = productService.findById(request.getId());
        List<Category> categories = categoryService.findAllCategory();
        if (result.hasErrors()) {
            model.addAttribute("categories",categories);
            model.addAttribute("productEdit",request);
            return "redirect:/admin/product/edit?id="+request.getId(); // chuyển hướng về trang edit
        }
        productService.update(productService.updateProduct(request,product));
        return "redirect:/admin/product";
    }
    @GetMapping("product/delete")
    public String deleteProduct(@RequestParam("id") Integer id){
        productService.delete(id);
        return "redirect:/admin/product";
    }
    @PostMapping("/product/deleteImage")
    public ResponseEntity<String> deleteImage(@RequestParam("imageId") Integer imageId) {
        // Xử lý xóa ảnh với imageId
        productService.deleteImage(imageId);

        return ResponseEntity.ok("Xóa ảnh thành công");
    }



}
