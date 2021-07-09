//package com.example.demo.config;
//
//import com.example.demo.Handler.JwtAccessDeniedHandler;
//import com.example.demo.Handler.JwtAuthenticationEntryPoint;
//import com.example.demo.filter.JwtAuthenticationTokenFilter;
//import com.example.demo.util.JwtTokenUtil;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * @description: Spring Security配置类
// * @author: 黄永琦
// * @date: 2021/6/9
// * @version: 1.0
// * @company: 数研院(福建)信息产业发展有限公司
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtTokenUtil jwtTokenUtil;
//
//    public WebSecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtAuthenticationEntryPoint
//    jwtAuthenticationEntryPoint, JwtTokenUtil jwtTokenUtil) {
//        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity
//                // 禁用 CSRF
//                .csrf().disable()
//
//                // 授权异常
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)
//
//                // 防止iframe 造成跨域
//                .and()
//                .headers()
//                .frameOptions()
//                .disable()
//
//                // 不创建会话
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                .and()
//                .authorizeRequests()
//
//                // 放行静态资源
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/*.html",
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/webSocket/**"
//                ).permitAll()
//
//                // 放行swagger
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
//                .antMatchers("/*/api-docs").permitAll()
//
//                // 放行文件访问
//                .antMatchers("/file/**").permitAll()
//
//                // 放行druid
//                .antMatchers("/druid/**").permitAll()
//
//                // 放行OPTIONS请求
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//
//                //允许匿名及登录用户访问
//                .antMatchers("/api/auth/**", "/error/**").permitAll()
//                // 所有请求都需要认证
//                .anyRequest().authenticated();
//
//        // 禁用缓存
//        httpSecurity.headers().cacheControl();
//
//        // 添加JWT filter
//        httpSecurity
//                .apply(new TokenConfigurer(jwtTokenUtil));
//
//    }
//
//    public class TokenConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//        private final JwtTokenUtil jwtTokenUtils;
//
//        public TokenConfigurer(JwtTokenUtil jwtTokenUtils) {
//            this.jwtTokenUtils = jwtTokenUtils;
//        }
//
//        @Override
//        public void configure(HttpSecurity http) {
//            JwtAuthenticationTokenFilter customFilter = new JwtAuthenticationTokenFilter(jwtTokenUtils);
//            http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
//        }
//    }
//
//
//}
