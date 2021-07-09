//package com.example.demo.controller;
//
//import com.example.demo.util.JwtTokenUtil;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @description: 系统授权接口
// * @author: 黄永琦
// * @date: 2021/6/9
// * @version: 1.0
// * @company: 数研院(福建)信息产业发展有限公司
// */
//@Slf4j
//@RestController
//@RequestMapping("/api/auth")
//@Api(tags = "系统授权接口")
//public class AuthController {
//    private final JwtTokenUtil jwtTokenUtil;
//
//    public AuthController(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    @ApiOperation("登录授权")
//    @GetMapping("/login")
//    public String login(String name, String password) {
//        Map map = new HashMap();
//        map.put("name", name);
//        map.put("password", password);
//        return jwtTokenUtil.createToken(map);
//    }
//
//    @ApiOperation("获取身份")
//    @GetMapping(value = "/principal")
//    public Object principal(String token) {
//        return jwtTokenUtil.getAuthentication(token).getPrincipal();
//    }
//}
