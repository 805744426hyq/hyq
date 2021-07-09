package com.example.demo;

import com.example.demo.util.AddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// SpringBootTest在启动的时候不会启动服务器，所以WebSocket自然会报错，这个时候需要添加选项webEnvironment，以便提供一个测试的web环境
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner.class)
@Slf4j
class DemoApplicationTests {
	@Autowired
	private AddressUtil addressUtil;

	@Test
	void contextLoads() {
		try {
			log.info(addressUtil.getAddr(119.275934, 26.116632));
			log.info(addressUtil.getCoordinate("福建省福州市鼓楼区永恒新村"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
