package com.example.demo.service.impl;

import com.example.demo.dao.BookDao;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/6
 */
@Service
@CacheConfig(cacheNames = "book_cache")
public class BookServiceImpl implements BookService {
	private final BookDao bookDao;

	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	@Cacheable
	public Book findByNameLike(String name) {
		return bookDao.findByNameLike(name);
	}

	@Override
	public Book save(Book book) {
		return bookDao.saveAndFlush(book);
	}
}
