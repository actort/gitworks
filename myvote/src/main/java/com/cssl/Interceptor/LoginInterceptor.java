package com.cssl.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if (uri.endsWith("login.action") || uri.endsWith("register") || uri.endsWith("getUser") || uri.endsWith("getUserByUname") || uri.endsWith("loginValidate") || uri.endsWith("getSub")) {
            return true;
        } else {
            HttpSession session = request.getSession();
            Object user = session.getAttribute("user");
            if (user == null) {
                response.sendRedirect("http://localhost:8080/vote/login.html");
                return false;
            } else {
                return true;
            }
        }
    }
}
