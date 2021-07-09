package com.example.demo.pattern.build;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
public class Coke extends ColdDrink {

	@Override
	public float price() {
		return 30.0f;
	}

	@Override
	public String name() {
		return "Coke";
	}
}
