package com.nowcoder.mvcdemo;

import com.nowcoder.mvcdemo.controller.interceptor.LoginInterceptor;
import com.nowcoder.mvcdemo.controller.interceptor.MyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MvcdemoApplication implements WebMvcConfigurer {

//    @Autowired
//    private MyInterceptor myInterceptor;

//    @Autowired
//    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor);
//        registry.addInterceptor(new LoginInterceptor())
//                .excludePathPatterns("/images/*", "/scripts/*", "/styles/*", "/", "/user/*", "/demo/*");
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/category/*", "/item/*");
    }

    public static void main(String[] args) {
        SpringApplication.run(MvcdemoApplication.class, args);
    }


}
