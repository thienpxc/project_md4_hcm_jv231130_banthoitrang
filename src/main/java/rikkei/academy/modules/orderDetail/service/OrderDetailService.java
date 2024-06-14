package rikkei.academy.modules.orderDetail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rikkei.academy.modules.order.Orders;
import rikkei.academy.modules.orderDetail.OrderDetail;
import rikkei.academy.modules.orderDetail.dao.OderDetailDaolmpl;
import rikkei.academy.modules.products.Product;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OderDetailDaolmpl orderDetailDao;

    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailDao.saveOrderDetail(orderDetail);
    }
    public List<OrderDetail> findAllActiveByOrderId(int orderId) {
        return orderDetailDao.findAllActiveByCustomerId(orderId);
    }
    public OrderDetail findByOrderIdAndProductId(Orders order, Product product) {
        return orderDetailDao.findByOrderIdAndProductId(order, product);
    }

}
