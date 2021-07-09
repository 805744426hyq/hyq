package com.example.demo.pattern.factory;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/8
 */
public class FactoryPatternDemo {
	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();

		//获取 Circle 的对象，并调用它的 draw 方法
		Shape shape1 = shapeFactory.getShape("圆形");

		//调用 Circle 的 draw 方法
		shape1.draw();

		//获取 Rectangle 的对象，并调用它的 draw 方法
		Shape shape2 = shapeFactory.getShape("长方形");

		//调用 Rectangle 的 draw 方法
		shape2.draw();

		//获取 Square 的对象，并调用它的 draw 方法
		Shape shape3 = shapeFactory.getShape("正方形");

		//调用 Square 的 draw 方法
		shape3.draw();
	}
}
