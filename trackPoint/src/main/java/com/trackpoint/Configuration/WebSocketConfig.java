package com.trackpoint.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 注册 STOMP 端点，客户端通过 SockJS 连接
        registry.addEndpoint("/ws").withSockJS(); // /ws 为 WebSocket 连接端点
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 配置消息代理
        registry.enableSimpleBroker("/topic"); // 简单的消息代理，订阅路径以 "/topic" 开头
        registry.setApplicationDestinationPrefixes("/app"); // 配置发送消息的前缀
    }
}

