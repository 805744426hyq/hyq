package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * Spring 框架提供了基于WebSocket 的STOMP 支持， STOMP 是一个简单的可互操作的协议，
 * 通常被用于通过中间服务器在客户端之间进行异步消息传递
 *
 * @author Administrator
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		/**
		 config.enableSimpleBroker（”／topic”）表示设直消息代理的前缀， flp如果消息的前缀是“／topic飞
		 就会将消息转发给消息代理（ broker ），再由消息代理将消息广播给当前连接的客户端。
		 */
		config.enableSimpleBroker("/topic");
		/**
		 * conf1g.setApplicationDestinationPrefixes（”／app”）表示配直一个或多个前缀，通过这些前缀过滤
		 * 出需要被注解方法处理的消息。例如，前级为“／app”的destination 可以通过＠MessageMapping
		 * 注解的方法处理，而其他destination （例如“／ topic”“I queue”）将被直接交给broker 处理。
		 */
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		/**
		 * registry.addEndpoint("/chat”）.withSockJS()则表示定义一个前缀为“／chat”的endPoint，并开启
		 * sockjs 支持， sockjs 可以解决浏览器对WebSocket 的兼容性问题，客户端将通过这里配直的
		 * URL 来建立WebSocket 连接。
		 */
		registry.addEndpoint("/chat").withSockJS();
	}

}
