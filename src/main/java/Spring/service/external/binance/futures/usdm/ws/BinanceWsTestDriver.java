package Spring.service.external.binance.futures.usdm.ws;

import Spring.service.external.binance.futures.usdm.ws.handlers.BinanceUsdmAggTradesHandler;
import Spring.service.external.binance.futures.usdm.ws.handlers.BinanceUsdmTickersHandler;
import Spring.service.external.binance.futures.usdm.ws.handlers.BinanceUsdmOrderBookHandler;

import java.net.URI;
import java.net.URISyntaxException;

public class BinanceWsTestDriver {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        URI tickerEndpointURI = new URI("wss://stream.binance.com:443/ws/bnbbtc@ticker");
        BinanceWsMessageHandler tickerMessageHandler = new BinanceUsdmTickersHandler();

        URI aggTradeEndpointURI = new URI("wss://stream.binance.com:443/ws/bnbbtc@aggTrade");
        BinanceWsMessageHandler aggTradeMessageHandler = new BinanceUsdmAggTradesHandler();

        URI orderBookEndpointURI = new URI("wss://stream.binance.com:443/ws/bnbbtc@bookTicker");
        BinanceWsMessageHandler orderBookMessageHandler = new BinanceUsdmOrderBookHandler();

        BinanceWsExecutor executor = BinanceWsExecutor.getInstance();
        executor.startWsConnection(tickerEndpointURI, tickerMessageHandler);
        executor.startWsConnection(aggTradeEndpointURI, aggTradeMessageHandler);
        executor.startWsConnection(orderBookEndpointURI, orderBookMessageHandler);
        while (true) {
            Thread.sleep(10000);
        }
    }
}