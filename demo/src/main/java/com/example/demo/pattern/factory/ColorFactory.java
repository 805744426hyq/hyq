package com.example.demo.pattern.factory;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/8
 */
public class ColorFactory extends AbstractFactory {

	@Override
	public Shape getShape(String shape) {
		return null;
	}

	public Color getColor(String color) {
		if (StringUtils.isEmpty(color)) {
			return null;
		}
		switch (color) {
			case "红色":
				return new Red();
			case "绿色":
				return new Green();
			case "蓝色":
				return new Blue();
		}
		return null;

	}
}
