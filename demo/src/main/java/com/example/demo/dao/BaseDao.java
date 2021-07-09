package com.example.demo.dao;

import com.example.demo.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author Administrator
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID>,
                                                                                JpaSpecificationExecutor<T> {
}
