package com.syf.chat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.syf.chat.common.enums.EnumChatType;
import com.syf.chat.entity.Message;
import com.syf.chat.entity.UserInfo;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketHandler extends TextWebSocketHandler {
    private Map<String, WebSocketSession> socketMap = new ConcurrentHashMap<>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        System.out.println("新客户端连接: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        System.out.println("接收到消息: " + textMessage.getPayload());
        try {
            Gson gson = new Gson();
            Message message = gson.fromJson(textMessage.getPayload(), Message.class);
            if(EnumChatType.CHAT0.getCode().equals(message.getChatType())){
                UserInfo userInfo = (UserInfo) message.getData();
                socketMap.put(message.getMessageId(), session);
                session.sendMessage(new TextMessage(gson.toJson(message)));
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
