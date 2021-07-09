package com.example.demo.pattern.factory;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
public class Red implements Color {
	@Override
	public void fill() {
		System.out.println("红色");
	}
}
