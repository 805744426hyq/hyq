package com.example.demo.pattern.factory;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/8
 */
public class ShapeFactory extends AbstractFactory {

	@Override
	public Color getColor(String color) {
		return null;
	}

	public Shape getShape(String shape) {
		if (StringUtils.isEmpty(shape)) {
			return null;
		}
		switch (shape) {
			case "长方形":
				return new Rectangle();
			case "正方形":
				return new Square();
			case "圆形":
				return new Circle();
		}
		return null;

	}
}
