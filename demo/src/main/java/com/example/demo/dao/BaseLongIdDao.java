package com.example.demo.dao;

import com.example.demo.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @description:
 * @author: 黄永琦
 * @date: 2021/6/8
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
@NoRepositoryBean
public interface BaseLongIdDao<T extends BaseEntity> extends BaseDao<T, Long> {
}
