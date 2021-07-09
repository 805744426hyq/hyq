package com.example.demo.pattern.build;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
public class ChickenBurger extends Burger {
	@Override
	public float price() {
		return 50.5f;
	}

	@Override
	public String name() {
		return "Chicken  Burger";
	}
}
