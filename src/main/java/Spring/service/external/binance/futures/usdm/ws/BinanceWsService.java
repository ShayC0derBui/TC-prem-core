package Spring.service.external.binance.futures.usdm.ws;

import java.net.URI;

public class BinanceWsService {
    private BinanceWsClient binanceWsClient;

    public BinanceWsService(URI endpointURI, BinanceWsMessageHandler messageHandler) {
        binanceWsClient = new BinanceWsClient(endpointURI, messageHandler);
    }

    public void subscribeToChannel(String channel) {
        binanceWsClient.subscribeToChannel(channel);
    }
}