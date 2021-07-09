//package com.example.demo.Handler;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Objects;
//
///**
// * @description:认证失败处理类
// * @author: 黄永琦
// * @date: 2021/6/9
// * @version: 1.0
// * @company: 数研院(福建)信息产业发展有限公司
// */
//@Component
//public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    private static final String UNAUTHORIZED = "Unauthorized";
//
//    @Override
//    public void commence(HttpServletRequest request,
//                         HttpServletResponse response,
//                         AuthenticationException authException) throws IOException {
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, Objects.isNull(authException) ? UNAUTHORIZED :
//        authException.getMessage());
//    }
//
//}
