package com.example.demo.pattern.build;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
@Slf4j
public class Meal {
	private List<Item> items = new ArrayList<>();

	public void addItem(Item item) {
		items.add(item);
	}

	public float getCost() {
		float cost = 0.0f;
		for (Item item : items) {
			cost += item.price();
		}
		return cost;
	}

	public void showItems() {
		for (Item item : items) {
			log.info("Item :{} ", item.name());
			log.info(", Packing : {}", item.packing().pack());
			log.info(", Price : {}", item.price());
		}
	}
}
