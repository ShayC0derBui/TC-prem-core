package Spring.service.external.binance.futures.usdm.ws;

import javax.websocket.*;

import java.net.URI;

@ClientEndpoint
public class BinanceWsClient {
    private Session userSession = null;
    private BinanceWsMessageHandler messageHandler;

    public BinanceWsClient(URI endpointURI, BinanceWsMessageHandler messageHandler) {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.userSession = container.connectToServer(this, endpointURI);
            this.messageHandler = messageHandler;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("Opening WebSocket connection");
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("Closing WebSocket connection due to " + reason);
        this.userSession = null;
    }
//TODO Based on the event type in the response json, we need to handle the message and call the respective handlers
//Event type: 24hrTicker
//Event type: aggTrade
    @OnMessage
    public void onMessage(String message) {
        if (messageHandler != null) {
            messageHandler.handleMessage(message);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("Error on WebSocket connection: " + throwable.getMessage());
    }

    public void subscribeToChannel(String channel) {
        userSession.getAsyncRemote().sendText(channel);
    }
}