package rikkei.academy.config;

import org.springframework.web.servlet.HandlerInterceptor;
import rikkei.academy.modules.customer.Customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    Customer user = (Customer) request.getSession().getAttribute("loginUser");
    System.out.println(user);
    if (user == null) {
        response.sendRedirect("/auth");
        return false;
    }
    if (user != null && user.getRole()) {
        return true;
    }else if(user != null && !user.getRole()){
        response.sendRedirect("");
        return false;
    }
    response.sendRedirect("/403");
    return false;
}
}
