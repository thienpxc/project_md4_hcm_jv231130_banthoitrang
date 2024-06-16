package rikkei.academy.modules.orderDetail;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.Orders;
import rikkei.academy.modules.order.service.OrderServicelmpl;
import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;
import rikkei.academy.modules.orderDetail.service.OrderDetailService;
import rikkei.academy.modules.products.Product;
import rikkei.academy.modules.products.service.IProductService;
import rikkei.academy.modules.products.service.ProductServicelmpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Transactional
public class CartController {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderServicelmpl orderService;
    @Autowired
    private IProductService productService;

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("loginUser");
        // Nếu Customer không tồn tại, chuyển hướng người dùng về trang đăng nhập hoặc trang khác tùy thuộc vào yêu cầu của bạn
        if (customer == null) {
            return "redirect:/login";
        }
        // Tìm đối tượng Orders tương ứng với Customer này
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
      // Tìm đối tượng Orders tương ứng với Customer này
        Orders order = orderService.findOrderByCustomer(customer);
        // Lấy thông tin sản phẩm từ ID sản phẩm
        Product product = productService.findById(productId);
        // Tìm OrderDetail với cùng productId
        OrderDetail existingOrderDetail = orderDetailService.findByOrderIdAndProductId(order, product);
        if (existingOrderDetail != null) {
            // Nếu OrderDetail với cùng productId đã tồn tại, cập nhật số lượng và tổng giá tiền
            existingOrderDetail.setQuantity(existingOrderDetail.getQuantity() + quantity);
            existingOrderDetail.setPrice(existingOrderDetail.getPrice() + product.getPrice() * quantity);
            orderDetailService.save(existingOrderDetail);
        } else {
            // Nếu không, tạo một OrderDetail mới
            double total = product.getPrice() * quantity;
            orderDetail.setOrderId(order);
            orderDetail.setProductId(product);
            orderDetail.setPrice(total);
            orderDetail.setQuantity(quantity);
            orderDetailService.save(orderDetail);
        }

        // Chuyển hướng người dùng về trang giỏ hàng hoặc một trang khác tùy thuộc vào yêu cầu của bạn
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
