package com.example.demo.pattern.build;

/**
 * @author 黄永琦
 * @description https://www.runoob.com/design-pattern/builder-pattern.html
 * @date 2021/7/9
 */
interface Item {
	String name();

	Packing packing();

	float price();
}
