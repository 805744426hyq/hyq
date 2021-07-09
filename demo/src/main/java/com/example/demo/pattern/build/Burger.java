package com.example.demo.pattern.build;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
public abstract class Burger implements Item {
	@Override
	public Packing packing() {
		return new Wrapper();
	}

	@Override
	public abstract float price();
}
