package com.example.demo.pattern.build;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
@Slf4j
public class BuilderPatternDemo {
	public static void main(String[] args) {
		MealBuilder mealBuilder = new MealBuilder();

		Meal vegMeal = mealBuilder.prepareVegMeal();
		log.info("Veg Meal");
		vegMeal.showItems();
		log.info("Total Cost:{} ", vegMeal.getCost());

		Meal chickenVegMeal = mealBuilder.prepareChickenVegMeal();
		log.info("\n\nchicken Meal");
		chickenVegMeal.showItems();
		log.info("Total Cost:{} ", chickenVegMeal.getCost());
	}
}
