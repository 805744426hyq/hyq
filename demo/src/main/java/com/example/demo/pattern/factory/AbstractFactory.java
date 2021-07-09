package com.example.demo.pattern.factory;

/**
 * @author 黄永琦
 * @description https://www.runoob.com/design-pattern/abstract-factory-pattern.html
 * @date 2021/7/9
 */
public abstract class AbstractFactory {
	public abstract Color getColor(String color);

	public abstract Shape getShape(String shape);
}
