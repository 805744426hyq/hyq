//package com.example.demo.util;
//
//import com.example.demo.config.JwtSecurityConfig;
//import com.example.demo.eneity.User;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.*;
//import java.util.stream.Collectors;
//
///**
// * @description:工具类实现创建token与校验token功能
// * @author: 黄永琦
// * @date: 2021/6/9
// * @version: 1.0
// * @company: 数研院(福建)信息产业发展有限公司
// */
//@Slf4j
//@Component
//public class JwtTokenUtil implements InitializingBean {
//    private final JwtSecurityConfig jwtSecurityProperties;
//    private static final String AUTH_KEY = "auth";
//    private Key key;
//
//    public JwtTokenUtil(JwtSecurityConfig jwtSecurityConfig) {
//        this.jwtSecurityProperties = jwtSecurityConfig;
//    }
//
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtSecurityProperties.getBase64Secret());
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String createToken(Map<String, Object> claims) {
//        return Jwts.builder()
//                .claim(AUTH_KEY, claims)
//                .setId(UUID.randomUUID().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + jwtSecurityProperties.getTokenValidityInSeconds()))
//                .compressWith(CompressionCodecs.DEFLATE)
//                .signWith(SignatureAlgorithm.HS512, key)
//                .compact();
//    }
//
//    public Date getExpirationDateFromToken(String token) {
//        Date expiration;
//        try {
//            final Claims claims = getClaimsFromToken(token);
//            expiration = claims.getExpiration();
//        } catch (Exception e) {
//            expiration = null;
//        }
//        return expiration;
//    }
//
//    public Authentication getAuthentication(String token) {
//        Claims claims = Jwts.parser()
//                .setSigningKey(key)
//                .parseClaimsJws(token)
//                .getBody();
//
//        Collection<? extends GrantedAuthority> authorities =
//                Arrays.stream(claims.get(AUTH_KEY).toString().split(","))
//                        .map(SimpleGrantedAuthority::new)
//                        .collect(Collectors.toList());
//
//        HashMap map = (HashMap) claims.get("auth");
//
//        User principal = new User(map.get("name").toString(), map.get("password").toString(), authorities);
//
//        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
//    }
//
//    public boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
//            return true;
//        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
//            log.info("Invalid JWT signature.");
//            e.printStackTrace();
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token.");
//            e.printStackTrace();
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT token.");
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            log.info("JWT token compact of handler are invalid.");
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    private Claims getClaimsFromToken(String token) {
//        Claims claims;
//        try {
//            claims = Jwts.parser()
//                    .setSigningKey(key)
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            claims = null;
//        }
//        return claims;
//    }
//
//}
