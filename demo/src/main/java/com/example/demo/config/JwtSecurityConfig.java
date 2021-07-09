//package com.example.demo.config;
//
//import lombok.Data;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @description:
// * @author: 黄永琦
// * @date: 2021/6/9
// * @version: 1.0
// * @company: 数研院(福建)信息产业发展有限公司
// */
//@Data
//@Configuration
//@ConfigurationProperties(prefix = "jwt")
//public class JwtSecurityConfig {
//    /**
//     * Request Headers ： Authorization
//     */
//    private String header;
//
//    /**
//     * 令牌前缀，最后留个空格 Bearer
//     */
//    private String tokenStartWith;
//
//    /**
//     * Base64对该令牌进行编码
//     */
//    private String base64Secret;
//
//    /**
//     * 令牌过期时间 此处单位/毫秒
//     */
//    private Long tokenValidityInSeconds;
//
//    /**
//     * 返回令牌前缀
//     */
//    public String getTokenStartWith() {
//        return tokenStartWith + StringUtils.SPACE;
//    }
//}
