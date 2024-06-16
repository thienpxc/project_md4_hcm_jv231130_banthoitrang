package rikkei.academy.modules.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import rikkei.academy.modules.order.models.Orders;
import rikkei.academy.modules.order.service.IOrderService;
import rikkei.academy.modules.orderDetail.OrderDetail;
import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;

import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private OderDetailDaolmpl orderDetailDAO;

    @PostMapping("/checkout")
    public String checkout(Model model) {
        // Lấy danh sách OrderDetail từ cơ sở dữ liệu
        List<OrderDetail> cart = orderDetailDAO.getAllOrderDetails();
        // Tạo một đối tượng Orders mới
        Orders order = new Orders();
        // Lưu order và orderDetails
        orderService.saveOrderAndDetails(order, cart);
        return "redirect:/confirmation";
    }
}
