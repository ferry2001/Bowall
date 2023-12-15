package com.ferry.bowall;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@EnableCaching //开起 Spring Cache 注解方式的缓存功能
public class BoWallApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoWallApplication.class,args);
        log.info("项目启动成功...");
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new WebSocketHandler() {
            @Override
            public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
                // 处理收到的消息
            }
        };
    }

    @Bean
    public WebSocketServer webSocketServer() {
        return new WebSocketServer() {
            @Override
            public void start() {
                // 启动 WebSocket 服务
            }
        };
    }
}
