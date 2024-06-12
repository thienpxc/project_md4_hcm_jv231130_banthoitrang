package rikkei.academy.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.modules.category.Category;
import rikkei.academy.modules.category.service.ICategoryService;
import rikkei.academy.modules.products.dto.request.ProductRequest;
import rikkei.academy.modules.products.dto.response.ProductResponse;
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
        model.addAttribute("product",new ProductRequest());
        return "admin/product/product";
    }
    @PostMapping("product/add")
    public String addProduct(@Valid @ModelAttribute("product") ProductRequest request, BindingResult result,Model model){
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
    @GetMapping("product/add")
    public String doAdd(Model model){
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("product",new ProductRequest());
        return "/admin/product/add";
    }
    @GetMapping("product/edit")
    public String editProduct(@RequestParam("id") Integer id, Model model){
        ProductResponse product = productService.findById(id);
        model.addAttribute("product",product);
        return "admin/product/edit";
    }

    @PostMapping("product/edit")
    public String doEdit(@Valid @ModelAttribute("product") ProductRequest request, BindingResult result,Model model){
        if (result.hasErrors()) {
            model.addAttribute("product",request);
            return "admin/product/edit";
        }
        productService.save(request);
        return "redirect:/admin/product";
    }
    @GetMapping("product/delete")
    public String deleteProduct(@RequestParam("id") Integer id){
        productService.delete(id);
        return "redirect:/admin/product";
    }




}
