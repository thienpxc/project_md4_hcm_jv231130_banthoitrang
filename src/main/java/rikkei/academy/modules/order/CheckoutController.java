package rikkei.academy.modules.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PostMapping;
import rikkei.academy.modules.order.models.Orders;
import rikkei.academy.modules.order.service.IOrderService;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.modules.customer.Customer;

import rikkei.academy.modules.order.service.OrderServicelmpl;

import rikkei.academy.modules.orderDetail.OrderDetail;
import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;
import rikkei.academy.modules.orderDetail.service.OrderDetailService;
import rikkei.academy.modules.products.service.IProductService;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@Transactional
public class CheckoutController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private OderDetailDaolmpl orderDetailDAO;
    private OrderDetailService orderDetailService;
    @Autowired
    private IOrderService orderServices;
    @Autowired
    private IProductService productService;

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




    @GetMapping("/checkout")
    public String checkout(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("loginUser");


        if (customer == null) {
            return "redirect:/login";
        }
        // Tìm đối tượng Orders tương ứng với Customer này
        model.addAttribute("orderDetail", orderDetailService.findAllActiveByOrderId(customer.getCustomerId()));


        model.addAttribute("totalPrice", orderDetailService.calculateTotalPrice(customer.getCustomerId()));

        return "/customer/shop/checkout";
    }
    @Transactional
    @PostMapping("/updateOrder")
    public String updateOrderAndCustomer(HttpSession session,
                                         @RequestParam("address") String address,
                                         @RequestParam("country") String country,
                                         @RequestParam("city") String city,
                                         @RequestParam("payment") String paypal,
                                         @RequestParam("phoneNumber") String phoneNumber,
                                         @RequestParam("email") String email) {
        // Get the logged-in Customer's information
        Customer customer = (Customer) session.getAttribute("loginUser");

        // Update the Customer's information
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(email);
        customer.setAddress(address + ", " + city + ", " + country);

        // Calculate the total price
        Double totalPrice = orderDetailService.calculateTotalPrice(customer.getCustomerId());

        // Check if there are any Orders in PENDING status
        Orders currentOrder = orderServices.findPendingOrderByCustomer(customer);

        if (currentOrder != null) {
            // Update the current Orders' information
            currentOrder.setShippingAddress(address + ", " + city + ", " + country);
            currentOrder.setOrderDate(new Date());
            currentOrder.setPaymentMethod(paypal);
            currentOrder.setTotalAmount(String.valueOf(totalPrice + 10));
            currentOrder.setOrderStatus(Orders.OderStatus.WAITING);  // Change to WAITING
            System.out.println("currentOrder: " + currentOrder);
            // Update the status of all OrderDetail
            for (OrderDetail orderDetail : currentOrder.getOrderDetails()) {
                orderDetail.setStatus(false);
                orderDetailService.save(orderDetail);
                System.out.println("orderDetail: " + orderDetail);
            }

            // Save the updated Orders
            orderServices.saveOrder(currentOrder);
        }

        // Check if there are any Orders in PENDING status
        boolean hasPendingOrder = orderServices.hasPendingOrderForCustomer(customer);

        if (!hasPendingOrder) {
            // Create a new Orders if there are no Orders in PENDING status
            Orders newOrder = new Orders();
            newOrder.setCustomer(customer);
            newOrder.setOrderDate(new Date());
            newOrder.setOrderStatus(Orders.OderStatus.PENDING);
            newOrder.setStatus(true);
            newOrder.setShippingAddress(address + ", " + city + ", " + country);
            newOrder.setPaymentMethod(paypal);
            newOrder.setTotalAmount(String.valueOf(totalPrice + 10));
            orderServices.saveOrder(newOrder);
            System.out.println("newOrder: " + newOrder);
        }

        // Update Customer
        orderServices.updateCustomer(customer);

        return "redirect:/";

    }
}
