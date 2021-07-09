package com.example.demo.mapper;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 黄永琦
 * @version 1.0
 * @description
 * @date 2021/6/22
 * @company 数研院(福建)信息产业发展有限公司
 */
@Repository
@Mapper
public interface BookMapper {
	List<Book> getBooks();
}
