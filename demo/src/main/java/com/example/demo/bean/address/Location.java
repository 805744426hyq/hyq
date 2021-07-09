package com.example.demo.bean.address;

import lombok.Data;

import java.util.StringJoiner;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
@Data
public class Location {
	// 纬度 26.152741
	private Double lat;
	// 精度 119.231245
	private Double lng;

	@Override
	public String toString() {
		return new StringJoiner(", ", "[", "]")
				.add("lat:" + lat)
				.add("lng:" + lng)
				.toString();
	}
}
