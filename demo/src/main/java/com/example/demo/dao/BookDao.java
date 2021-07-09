package com.example.demo.dao;

import com.example.demo.entity.Book;
import org.springframework.stereotype.Repository;

/**
 * @author 黄永琦
 * @version 1.0
 * @description
 * @date 2021/6/22
 * @company 数研院(福建)信息产业发展有限公司
 */
@Repository
public interface BookDao extends BaseLongIdDao<Book> {
	Book findByNameLike(String name);

}
