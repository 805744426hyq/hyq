package com.example.demo.config;

import com.example.demo.constant.CommonConstant;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/16
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
//@Configuration
public class TomcatConfig {
    /**
     * 这里首先配置一个TomcatServletWebServerFactory ，然后添加一个Tomcat 中的Connector （监
     * 昕8080 端口〉， 并将请求转发到8081 上去。
     * 请求8080，自动重定向到8081
     * http://localhost:8080/user/hello 自动重定向 https://localhost:8081/user/hello
     *
     * @return
     */
    @Bean
    TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint constraint = new SecurityConstraint();
                constraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection securityCollection = new SecurityCollection();
                securityCollection.addPattern("/*");
                constraint.addCollection(securityCollection);
                context.addConstraint(constraint);
            }
        };
        factory.addAdditionalTomcatConnectors(createTomcatConnector());
        return factory;
    }

    private Connector createTomcatConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(CommonConstant.HTTP_LISTEN_PORT);
        connector.setSecure(false);
        connector.setRedirectPort(CommonConstant.HTTPS_REDIRECT_PORT);
        return connector;
    }
}
