package io.github.zbytes.demo.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class HelloClient {

    private static final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
    private static final Logger LOG = LoggerFactory.getLogger(HelloClient.class);

    public static void main(String[] args) throws Exception {
        HelloClient helloClient = new HelloClient();

        ListenableFuture<StompSession> f = helloClient.connect();
        StompSession stompSession = f.get();

        LOG.info("Subscribing to greeting topic using session {}", stompSession);
        helloClient.subscribeGreetings(stompSession);

        LOG.info("Sending hello message {}", stompSession);
        helloClient.sendHello(stompSession);
        Thread.sleep(60000);
    }

    public ListenableFuture<StompSession> connect() {

        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);

        String url = "ws://{host}:{port}/zsockets";
        return stompClient.connect(url, headers, new MyHandler(), "localhost", 8080);
    }

    public void subscribeGreetings(StompSession stompSession) {
        stompSession.subscribe("/topic/greetings", new StompFrameHandler() {

            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            public void handleFrame(StompHeaders stompHeaders, Object o) {
                LOG.info("Received greeting {}", new String((byte[]) o));
            }
        });
    }

    public void sendHello(StompSession stompSession) {
        String jsonHello = "{ \"from\" : \"suraj\", \"text\" : \"Hi zbytes!\" }";
        stompSession.send("/zbytes/hello", jsonHello.getBytes());
    }

    private static class MyHandler extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
            LOG.info("Now connected");
        }
    }

}