package com.example.demo.controller;

import com.example.demo.bean.PageBean;
import com.example.demo.entity.Book;
import com.example.demo.mapper.BookMapper;
import com.example.demo.service.BookService;
import com.example.demo.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/22
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
@RestController
@RequestMapping("/book")
@Slf4j
@Api(value = "书本控制层")
public class BookController {
	private final BookMapper bookMapper;
	private final BookService bookService;
	private final JsonUtil jsonUtil;

	@Autowired
	public BookController(BookMapper bookMapper, BookService bookService, JsonUtil jsonUtil) {
		this.bookMapper = bookMapper;
		this.bookService = bookService;
		this.jsonUtil = jsonUtil;
	}

	@PostMapping("/add")
	@ApiOperation("添加书")
	public Book addBook(@Validated @RequestBody Book book) {
		return bookService.save(book);

	}

	//当前页 一页多少个  mysql通过limit分页的哈
	@PostMapping("/page")
	@ApiOperation("分页查询")
	public PageBean<Book> findDemoList(@RequestBody PageBean page) {
		// 开启分页插件,放在查询语句上面 帮助生成分页语句,分页插件必须用mybatis查询
		Integer currentPage = page.getCurrentPage();
		Integer pageSize = page.getPageSize();
		//底层实现原理采用改写语句   将下面的方法中的sql语句获取到然后做个拼接 limit
		PageHelper.startPage(currentPage, pageSize);
		List<Book> data = bookMapper.getBooks();

		// 封装分页之后的数据  返回给客户端展示  PageInfo做了一些封装 作为一个类所有分页属性都可以冲pageInfoDemo拿到；
		return new PageBean<>(currentPage, pageSize, data.size(), data);

	}

}
