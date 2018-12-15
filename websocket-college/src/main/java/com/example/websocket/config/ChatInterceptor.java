package com.example.websocket.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * webSocket握手的拦截器，检查握手请求和响应
 * 对webSocketHandler传递属性，用于区别webSocket
 */
public class ChatInterceptor extends HttpSessionHandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        //我们用来区分链接，之前是不是通过用户名来区分是谁的，此处我们还是一样的逻辑，通过用户名区分
        //现在我们需要获取到用户的名字，因为我们的地址使用的rest风格，定义的地址的最后一位是名字，所以此处我们只需要找到请求地址，然后拿到最后一位
        System.out.println("握手");
        String url = request.getURI().toString();
        String name = url.substring(url.lastIndexOf("/") + 1);
        //给当前连接设置名字
        attributes.put("name",name);//建议将name抽取为静态常量

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {

        System.out.println("握手之后");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
