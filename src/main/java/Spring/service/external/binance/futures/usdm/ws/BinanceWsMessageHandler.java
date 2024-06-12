package Spring.service.external.binance.futures.usdm.ws;

public interface BinanceWsMessageHandler {
    void handleMessage(String message);
}