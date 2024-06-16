package rikkei.academy.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.modules.category.models.Category;
import rikkei.academy.modules.category.service.ICategoryService;
import rikkei.academy.modules.products.Product;

import rikkei.academy.modules.products.dto.request.ProductRequestAdd;
import rikkei.academy.modules.products.dto.request.ProductRequestUpdate;

import rikkei.academy.modules.products.service.IProductService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String product(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "limit",defaultValue = "3") Integer limit,@RequestParam(name = "categoryId", defaultValue = "") String category, Model model){
        long totalElements = productService.getTotalsElement();
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;
        model.addAttribute("products",productService.findByPagination(page,limit,category));
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("category",category);
        model.addAttribute("limit",limit);
        model.addAttribute("listCategory",categoryService.findIdAndNameOfCategory());
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
        System.out.println("da vao add 1");
        List<Category> categories = categoryService.findAllCategory();
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

    @PostMapping(value = "product/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> doEdit(@Valid @ModelAttribute("productEdit") ProductRequestUpdate request, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        Product product = productService.findById(request.getId());
        List<Category> categories = categoryService.findAllCategory();

        if (result.hasErrors()) {
            response.put("errors", result.getAllErrors());
            response.put("categories", categories);
            response.put("productEdit", request);
            return ResponseEntity.badRequest().body(response);
        }

        if (product == null) {
            response.put("message", "Không tìm thấy sản phẩm");
            return ResponseEntity.status(404).body(response);
        }
        productService.update(productService.updateProduct(request, product));
        response.put("message", "Cập nhật sản phẩm thành công");
        return ResponseEntity.status(200).body(response);
    }
    @GetMapping("product/deleteImage/{id}")
    public ResponseEntity<String> deleteImage(@PathVariable("id") Integer imageId) {
        System.out.println("imageId: " + imageId);
        try {
            productService.deleteImage(imageId);
            return ResponseEntity.ok("Xóa ảnh thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Có lỗi xảy ra: " + e.getMessage());
        }
    }
    @GetMapping("product/delete")
    public String deleteProduct(@RequestParam("id") Integer id){
        productService.delete(id);
        return "redirect:/admin/product";
    }
}
