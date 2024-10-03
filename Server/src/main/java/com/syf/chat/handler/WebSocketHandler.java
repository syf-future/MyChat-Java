package com.syf.chat.handler;

import com.google.gson.Gson;
import com.syf.chat.common.enums.EnumSendType;
import com.syf.chat.entity.Message;
import com.syf.chat.entity.MessageInfo;
import com.syf.chat.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {
    private Map<String, WebSocketSession> socketMap = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("新客户端连接: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        try {
            System.out.println("接收到消息: " + textMessage.getPayload());
            Gson gson = new Gson();
            Message message = gson.fromJson(textMessage.getPayload(), Message.class);
            switch (EnumSendType.valueOf(message.getSendType())) {
                // 处理登录消息的逻辑
                case LOGIN ->
                {
                    UserInfo userInfo = gson.fromJson(gson.toJson(message.getData()), UserInfo.class);
                    socketMap.put(message.getMessageId(), session);
                    session.sendMessage(new TextMessage(gson.toJson(message)));
                }
                // 处理好友消息的逻辑
                case FRIEND -> {
                    String messageId = message.getMessageId();
                    MessageInfo messageInfo = gson.fromJson(gson.toJson(message.getData()), MessageInfo.class);
                    WebSocketSession webSocketSession = socketMap.get(messageId);
                    if(null == webSocketSession){
                        log.error("{} 的用户未登录", messageId);
                        return;
                    }
                    log.info("开始向{} 发消息",messageId);
                    webSocketSession.sendMessage(new TextMessage(gson.toJson(message)));
                }
                // 处理群消息的逻辑
                case GROUP ->
                        System.out.println("用户登录");
                default ->
                    // 处理其他发送类型
                        System.out.println("其他消息类型");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        System.out.println("客户端已断开连接: " + session.getId());
    }
}
