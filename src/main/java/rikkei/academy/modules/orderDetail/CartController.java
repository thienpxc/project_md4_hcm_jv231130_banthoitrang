package rikkei.academy.modules.orderDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.models.Orders;
import rikkei.academy.modules.order.service.IOrderService;
import rikkei.academy.modules.orderDetail.service.OrderDetailService;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.service.IProductService;

import javax.servlet.http.HttpSession;

@Controller
@Transactional
public class CartController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IProductService productService;

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("loginUser");
        if (customer == null) {
            return "redirect:/login";
        }
        model.addAttribute("orderDetail", orderDetailService.findAllActiveByOrderId(customer.getCustomerId()));

        double totalPrice = orderDetailService.calculateTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        return "customer/shop/cart";
    }

    @PostMapping("/productadd/{id}")
    public String addToCart(@PathVariable("id") int productId, @RequestParam("quantity") int quantity, HttpSession session, OrderDetail orderDetail,Model model) {
        // Lấy đối tượng Customer từ phiên
        Customer customer = (Customer) session.getAttribute("loginUser");
        if (customer == null) {
            return "redirect:/login";
        }
        model.addAttribute("orderDetail", orderDetailService.findAllActiveByOrderId(customer.getCustomerId()));

        Orders order = orderService.findOrderByCustomer(customer);

        Product product = productService.findById(productId);

        OrderDetail existingOrderDetail = orderDetailService.findByOrderIdAndProductId(order, product);
        if (existingOrderDetail != null) {
            existingOrderDetail.setQuantity(existingOrderDetail.getQuantity() + quantity);
            existingOrderDetail.setPrice(existingOrderDetail.getPrice() + product.getPrice() * quantity);
            orderDetailService.save(existingOrderDetail);
        } else {

            double total = product.getPrice() * quantity;
            orderDetail.setOrderId(order);
            orderDetail.setProductId(product);
            orderDetail.setPrice(total);
            orderDetail.setQuantity(quantity);
            orderDetailService.save(orderDetail);
        }

        return "redirect:/cart";
    }


    @PostMapping("/deleteOrderDetail/{id}")
    public String deleteOrderDetail(@PathVariable("id") int id) {

        OrderDetail orderDetail = orderDetailService.findById(id);
        if (orderDetail != null) {
            orderDetailService.deleteOrderDetail(orderDetail);
        }
        return "redirect:/cart";
    }

    @PostMapping("/increaseQuantity")
    public String increaseQuantity(@RequestParam("orderItemId") int orderItemId) {
        orderDetailService.changeQuantity(orderItemId, 1);
        return "redirect:/cart";
    }

    @PostMapping("/decreaseQuantity")
    public String decreaseQuantity(@RequestParam("orderItemId") int orderItemId) {
        orderDetailService.changeQuantity(orderItemId, -1);
        return "redirect:/cart";
    }










}
