package rikkei.academy.modules.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import rikkei.academy.modules.order.Orders;
import rikkei.academy.modules.order.service.OrderServicelmpl;
import rikkei.academy.modules.orderDetail.OrderDetail;
import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;

import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    private OrderServicelmpl orderService;

    @Autowired
    private OderDetailDaolmpl orderDetailDAO;

    @PostMapping("/checkout")
    public String checkout(Model model) {
        // Lấy danh sách OrderDetail từ cơ sở dữ liệu
        List<OrderDetail> cart = orderDetailDAO.getAllOrderDetails();

        // Tạo một đối tượng Orders mới
        Orders order = new Orders();
        // Đặt các thuộc tính cho order tại đây...

        // Lưu order và orderDetails
        orderService.saveOrderAndDetails(order, cart);

        // Chuyển hướng người dùng đến trang xác nhận
        return "redirect:/confirmation";
    }
}
