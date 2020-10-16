package com.cssl.Interceptor;

import com.cssl.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PowerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            if (user.getStatus() == 1) {
                if (uri.endsWith("getItem") || uri.endsWith("getItemByUid") || uri.endsWith("startVote") || uri.endsWith("getSubBySid") || uri.endsWith("getUser") || uri.endsWith("getSub") || uri.endsWith("del") || uri.endsWith("votelist.html") || uri.endsWith("error.html")) {
                    return true;
                } else {
                    response.sendRedirect("error.html");
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
