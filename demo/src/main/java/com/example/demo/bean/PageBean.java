package com.example.demo.bean;

import lombok.Data;

import java.util.List;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/7
 */
@Data
public class PageBean<T> {
	// 当前页
	private Integer currentPage = 1;
	// 每页显示的总条数
	private Integer pageSize = 10;
	// 总条数
	private Integer totalNum;
	// 是否有下一页
	private Integer isMore;
	// 总页数
	private Integer totalPage;
	// 开始索引
	private Integer startIndex;

	// 分页结果
	private List<T> data;

	public PageBean() {
		super();
	}

	public PageBean(Integer currentPage, Integer pageSize, Integer totalNum, List<T> data) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.totalPage = (this.totalNum + this.pageSize - 1) / this.pageSize;
		this.startIndex = (this.currentPage - 1) * this.pageSize;
		this.isMore = this.currentPage >= this.totalPage ? 0 : 1;
		this.data = data;
	}
}
