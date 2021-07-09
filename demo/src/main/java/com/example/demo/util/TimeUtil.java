package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.time.Clock;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/11
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
@Component
public class TimeUtil {
	private TimeUtil() {

	}

	public static long getNowMills() {
		return Clock.systemDefaultZone().millis();
	}
}
