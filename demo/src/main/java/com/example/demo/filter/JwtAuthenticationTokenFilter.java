//package com.example.demo.filter;
//
//import com.example.demo.config.JwtSecurityConfig;
//import com.example.demo.util.JwtTokenUtil;
//import com.example.demo.util.SpringContextHolder;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @description:
// * @author: 黄永琦
// * @date: 2021/6/9
// * @version: 1.0
// * @company: 数研院(福建)信息产业发展有限公司
// */
//@Component
//@Slf4j
//public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
//    private final JwtTokenUtil jwtTokenUtil;
//
//    public JwtAuthenticationTokenFilter(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//    FilterChain filterChain) throws ServletException, IOException {
//        JwtSecurityConfig jwtSecurityProperties = SpringContextHolder.getBean(JwtSecurityConfig.class);
//        String requestRri = httpServletRequest.getRequestURI();
//        //获取request token
//        String token = null;
//        String bearerToken = httpServletRequest.getHeader(jwtSecurityProperties.getHeader());
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtSecurityProperties.getTokenStartWith())) {
//            token = bearerToken.substring(jwtSecurityProperties.getTokenStartWith().length());
//        }
//        if (StringUtils.hasText(token) && jwtTokenUtil.validateToken(token)) {
//            Authentication authentication = jwtTokenUtil.getAuthentication(token);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            log.debug("set Authentication to security context for '{}', uri: {}", authentication.getName(),
//            requestRri);
//        } else {
//            log.debug("no valid JWT token found, uri: {}", requestRri);
//        }
//        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
