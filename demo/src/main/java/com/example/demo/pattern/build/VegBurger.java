package com.example.demo.pattern.build;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
public class VegBurger extends Burger {
	@Override
	public float price() {
		return 25.0f;
	}

	@Override
	public String name() {
		return "Veg Burger";
	}
}
