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


    @PostMapping("/productadd/{id}")
    public String addToCart(@PathVariable("id") int productId, @RequestParam("quantity") int quantity, HttpSession session, OrderDetail orderDetail,Model model) {
        // Lấy đối tượng Customer từ phiên

        Customer customer = (Customer) session.getAttribute("loginUser");
      List<OrderDetail> orderDetails = orderDetailService.findAllActiveByOrderId(customer.getCustomerId());
       System.out.println("code đay"+orderDetails.size());

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
//        model.addAttribute("orderDetails", orderDetails);
        // Chuyển hướng người dùng về trang giỏ hàng hoặc một trang khác tùy thuộc vào yêu cầu của bạn
        return "redirect:/cart";
    }



}
