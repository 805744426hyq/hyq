package com.example.demo.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {
	private static final Logger log = LoggerFactory.getLogger(WebSocketServer.class);

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;
	/**
	 * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	 */
	private static ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	// 接收sid
	private String userId = StringUtils.EMPTY;

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message, @PathParam("userId") String userId) throws IOException {
		log.info("发送消息到:{},报文:{}", userId, message);
		if (StringUtils.isNotBlank(userId) && webSocketMap.containsKey(userId)) {
			webSocketMap.get(userId).sendMessage(message);
		} else {
			log.error("用户:{},不在线！", userId);
		}
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("userId") String userId) {
		this.session = session;
		this.userId = userId;
		if (webSocketMap.containsKey(userId)) {
			webSocketMap.remove(userId);
		} else {
			// 在线数+1
			addOnlineCount();
			;
		}
		// 加入集合
		webSocketMap.put(userId, this);
		log.info("有新窗口开始监听:{},当前在线人数为{}", userId, getOnlineCount());
		try {
			sendMessage("连接成功");
		} catch (IOException e) {
			log.error("websocket IO异常", e);
		}

	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		if (webSocketMap.containsKey(userId)) {
			webSocketMap.remove(userId);
			//从set中删除
			subOnlineCount();
		}
		log.info("用户id退出:{},当前在线人数为:{}", userId, getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("收到来自窗口{},的信息{}", session.getId(), message);
		//可以群发消息
		//消息保存到数据库、redis
		if (StringUtils.isNotEmpty(message)) {
			try {
				//解析发送的报文
				JSONObject jsonObject = JSON.parseObject(message);
				//追加发送人(防止串改)
				jsonObject.put("fromUserId", this.userId);
				String toUserId = jsonObject.getString("toUserId");
				//传送给对应toUserId用户的websocket
				if (StringUtils.isNotBlank(toUserId) && webSocketMap.containsKey(toUserId)) {
					webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
				} else {
					log.error("请求的userId:{}不在该服务器上", toUserId);
					//否则不在这个服务器上，发送到mysql或者redis
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("websocket发生错误,原因:{}", error);
	}

	/**
	 * 实现服务器主动推送
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}
}
