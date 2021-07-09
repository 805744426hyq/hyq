package com.example.demo;

import com.example.demo.handler.CustomMultipartResolver;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, MultipartAutoConfiguration.class})
@EnableAsync
@EnableJpaAuditing
@EnableJpaRepositories
@EnableConfigurationProperties
@EnableCaching
@EnableScheduling
@EnableBatchProcessing
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	/**
	 * 根据SpringBoot官方让重复执行的filter实现一次执行过程的解决方案，参见官网地址：https://docs.spring
	 * .io/spring-boot/docs/current/reference/htmlsingle/#howto-disable-registration-of-a-servlet-or-filter
	 * 在SpringBoot启动类中，加入以下代码：
	 */
//    @Bean
//    public FilterRegistrationBean registration(JwtAuthenticationTokenFilter filter) {
//        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
//        registration.setEnabled(false);
//        return registration;
//    }
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		return new CustomMultipartResolver();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
