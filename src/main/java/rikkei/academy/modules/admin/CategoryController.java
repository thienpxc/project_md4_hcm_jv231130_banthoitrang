package rikkei.academy.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.modules.category.dto.request.CategoryRequest;
import rikkei.academy.modules.category.service.ICategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("category")
    public String category(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "limit",defaultValue = "3") Integer limit, Model model){
        long totalElements = categoryService.getTotalsElement();
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;

        model.addAttribute("category",categoryService.findByPagination(page,limit));

        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("newCategory",new CategoryRequest());

        return "admin/category/category";
    }
    @GetMapping("category/add")
    public String addCategory(Model model){
        model.addAttribute("newCategory",new CategoryRequest());
        return "admin/category/add";
    }
    @PostMapping("category/add")
    public String doAdd(@Valid @ModelAttribute("newCategory") CategoryRequest category, BindingResult result, Model model){
        if (result.hasErrors()) {
            model.addAttribute("newCategory",category);
            return "admin/category/add";
        }
        categoryService.save(category);
        return "redirect:/admin/category";
    }
    @GetMapping("category/delete")
    public String deleteCategory(@RequestParam("id") Integer id){
        categoryService.delete(id);
        return "redirect:/admin/category";
    }
    @GetMapping("/product/edit/{id}")
    @ResponseBody
    public CategoryRequest edit(@PathVariable Integer id){
        return categoryService.findById(id);
    }


}
