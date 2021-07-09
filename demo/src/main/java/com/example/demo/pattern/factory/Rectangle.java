package com.example.demo.pattern.factory;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/8
 */
public class Rectangle implements Shape {
	@Override
	public void draw() {
		System.out.println("长方形");
	}
}
