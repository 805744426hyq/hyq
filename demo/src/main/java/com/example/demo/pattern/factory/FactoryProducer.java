package com.example.demo.pattern.factory;

import java.util.Objects;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {
		return Objects.equals("形状", choice) ? new ShapeFactory() : new ColorFactory();
	}
}
