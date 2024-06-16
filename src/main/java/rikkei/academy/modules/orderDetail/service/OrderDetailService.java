package rikkei.academy.modules.orderDetail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import rikkei.academy.modules.customer.Customer;
import rikkei.academy.modules.order.Orders;
import rikkei.academy.modules.orderDetail.OrderDetail;
import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;
import rikkei.academy.modules.products.Product;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@ControllerAdvice
public class OrderDetailService {
    @Autowired
    private OderDetailDaolmpl orderDetailDao;
    @Autowired
    private OrderDetailService orderDetailService;

    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailDao.saveOrderDetail(orderDetail);
    }
    public List<OrderDetail> findAllActiveByOrderId(int orderId) {
        return orderDetailDao.findAllActiveByCustomerId(orderId);
    }
    public OrderDetail findByOrderIdAndProductId(Orders order, Product product) {
        return orderDetailDao.findByOrderIdAndProductId(order, product);
    }
    public OrderDetail deleteOrderDetail(OrderDetail orderDetail) {
        return orderDetailDao.deleteOrderDetail(orderDetail);
    }
    public OrderDetail findById(int id) {
        return orderDetailDao.findById(id);
    }
    public OrderDetail changeQuantity(int orderItemId, int change) {

        return orderDetailDao.changeQuantity(orderItemId, change);
    }
    public double calculateTotalPrice() {
        return orderDetailDao.calculateTotalPrice();
    }

    @ModelAttribute("cartQuantity")
    public int calculateCartQuantity(HttpSession session) {
        Customer customer = (Customer) session.getAttribute("loginUser");
        if (customer != null) {
            List<OrderDetail> orderDetails = orderDetailService.findAllActiveByOrderId(customer.getCustomerId());
            return orderDetails.stream().mapToInt(OrderDetail::getQuantity).sum();
        }
        return 0;
    }


}
