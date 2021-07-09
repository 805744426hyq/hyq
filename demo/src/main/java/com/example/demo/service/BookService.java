package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.List;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/6
 */
public interface BookService {
	List<Book> findAll();

	Book findByNameLike(String name);

	Book save(Book book);
}
