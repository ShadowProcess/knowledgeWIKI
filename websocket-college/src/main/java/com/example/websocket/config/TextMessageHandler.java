package com.example.websocket.config;

import net.sf.json.JSONObject;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TextMessageHandler extends TextWebSocketHandler {

    private Map<String,WebSocketSession> allClients = new ConcurrentHashMap<>();

    /**
     * 当建立连接调用
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String name = (String)session.getAttributes().get("name"); //获取到在拦截器中设置的name
        if (name!=null) {
            allClients.put(name,session);
        }
        //super.afterConnectionEstablished(session);
    }

    /**
     * 当关闭连接时调用
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    /**
     * 当前发送消息的用户的连接
     * @param session
     * @param message 发送的消息是什么
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        JSONObject jsonObject = JSONObject.fromObject(new String(message.asBytes()));

        String to = jsonObject.getString("toUser"); //找到接收者
        String toMessage = jsonObject.getString("toMessage");//获取到发送的内容

        String fromUser = (String)session.getAttributes().get("name");

        String content = "收到来自"+fromUser+"的消息，内容是："+toMessage;

        //创建消息对象
        TextMessage toTextMessage = new TextMessage(content);
        sendMessage(to,toTextMessage);
        //super.handleTextMessage(session, message);
    }


    public void sendMessage(String toUser,TextMessage message){
        //获取到对方的连接
        WebSocketSession session = allClients.get(toUser);

        if (session != null && session.isOpen()) {
            try {
                //发送消息
                session.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
