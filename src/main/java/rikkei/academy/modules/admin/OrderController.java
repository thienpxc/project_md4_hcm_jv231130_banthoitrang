package rikkei.academy.modules.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.modules.order.service.IOrderService;
import rikkei.academy.modules.order.service.OrderServicelmpl;

@Controller
@RequestMapping("/admin")
public class OrderController {
    @Autowired
    private IOrderService oderService;
    @GetMapping("order")
    public String order(@RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "limit",defaultValue = "3") Integer limit,  @RequestParam(name = "status", defaultValue = "") String status,Model model){
        long totalElements = oderService.getTotalsElement();
        if (page < 0) {
            page = 0;
        }
        if (page > totalElements) {
            page = (int) totalElements;
        }
        long nguyen = totalElements/limit;
        long du = totalElements%limit;
        long totalPages = du==0?nguyen:nguyen+1;
        model.addAttribute("orders",oderService.findByPagination(page,limit,status));
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("page",page);
        model.addAttribute("limit",limit);
        model.addAttribute("status",status);
        model.addAttribute("listStatus",oderService.findQuantityOfStatus());
        return "admin/order/order";
    }
    @GetMapping("order/confirm/{id}")
    public String confirm(@PathVariable("id") Integer id){
        oderService.confirmOrder(id);
        return "redirect:/admin/order";
    }
    @GetMapping("order/cancel/{id}")
    public String cancel(@PathVariable("id") Integer id){
        oderService.cancelOrder(id);
        return "redirect:/admin/order";
    }
    @GetMapping("order/shipping/{id}")
    public String shipping(@PathVariable("id") Integer id){
        oderService.shippingOrder(id);
        return "redirect:/admin/order";
    }

    @GetMapping("order/delivered/{id}")
    public String delivered(@PathVariable("id") Integer id){
        oderService.deliveredOrder(id);
        return "redirect:/admin/order";
    }


}
