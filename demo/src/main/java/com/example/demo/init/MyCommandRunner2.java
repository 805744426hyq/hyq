package com.example.demo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/21
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
@Component
@Order(2)
public class MyCommandRunner2 implements CommandLineRunner {
    /**
     * 如果整个系统中有多个CommandLineRunner 的实现类，那么可以使用＠Order 注解对这些实现类的
     * 调用顺序进行排序。
     * Order(l）注解用来描述CommandLineRunner 的执行顺序，数字越小越先执行
     */
    @Override
    public void run(String... args) throws Exception {
        // System.out.println("MyCommandRunner2"+ Arrays.toString(args));
    }
}
