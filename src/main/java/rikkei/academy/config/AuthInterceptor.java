package rikkei.academy.config;

import org.springframework.web.servlet.HandlerInterceptor;
import rikkei.academy.modules.customer.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// dùng để kiểm tra xem người dùng đã đăng nhập hay chưa
public class AuthInterceptor implements HandlerInterceptor {
    // kiểm tra xem người dùng đã đăng nhập hay chưa
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // lấy thông tin người dùng từ session
    Customer user = (Customer) request.getSession().getAttribute("loginUser");
    // nếu người dùng chưa đăng nhập thì chuyển hướng về trang login
    if (user == null) {
        response.sendRedirect("/login");
        return false;
    }
    // nếu người dùng đã đăng nhập và có quyền truy cập thì cho phép truy cập
    if (user != null && user.getRole()) {
        // lúc này role = true nghĩa là người dùng có quyền truy cập admin
        return true;
    }
    // nếu người dùng đã đăng nhập nhưng không có quyền truy cập thì chuyển hướng về trang 403
    response.sendRedirect("/403");
    return false;
}
}
