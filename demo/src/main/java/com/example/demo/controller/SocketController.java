package com.example.demo.controller;

import com.example.demo.aop.WebLog;
import com.example.demo.websocket.WebSocketServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/11
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
@RestController
@RequestMapping("/socket")
@Api(tags = "测试websocket")
public class SocketController {
	private final WebSocketServer webSocketServer;

	public SocketController(WebSocketServer webSocketServer) {
		this.webSocketServer = webSocketServer;
	}

	@GetMapping("/push/{toUserId}")
	@ApiOperation("测试推送")
	@WebLog
	public ResponseEntity<String> pushToWeb(String message, @PathVariable String toUserId) throws IOException {
		WebSocketServer.sendInfo(message, toUserId);
		return ResponseEntity.ok("MSG SEND SUCCESS");
	}
}
