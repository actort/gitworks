package com.cssl.config;

import com.cssl.Interceptor.LoginInterceptor;
import com.cssl.Interceptor.PowerInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Component
public class MyConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> strings = Arrays.asList("/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png",
                "/**/*.gif", "/login.html", "/regist.html");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(strings).order(1);
        registry.addInterceptor(new PowerInterceptor()).addPathPatterns("/**").excludePathPatterns(strings).order(2);
    }
}
