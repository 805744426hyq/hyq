package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author 黄永琦
 * @description 多数据源配置
 * @date 2021/6/21
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
//@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.one")
    DataSource dsOne() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.two")
    DataSource dsTwo() {
        return DruidDataSourceBuilder.create().build();
    }
}
