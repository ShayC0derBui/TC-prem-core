package Spring.service.external.binance.futures.usdm.ws.handlers;

import Spring.service.external.binance.futures.usdm.ws.BinanceWsMessageHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BinanceUsdmTickersHandler implements BinanceWsMessageHandler {

    @Override
    public void handleMessage(String message) {
        JsonObject tickerUpdate = JsonParser.parseString(message).getAsJsonObject();

        String eventType = tickerUpdate.get("e").getAsString();
        long eventTime = tickerUpdate.get("E").getAsLong();
        String symbol = tickerUpdate.get("s").getAsString();
        String priceChange = tickerUpdate.get("p").getAsString();
        String priceChangePercent = tickerUpdate.get("P").getAsString();
        String weightedAvgPrice = tickerUpdate.get("w").getAsString();
        String lastPrice = tickerUpdate.get("c").getAsString();
        String lastQty = tickerUpdate.get("Q").getAsString();
        String openPrice = tickerUpdate.get("o").getAsString();
        String highPrice = tickerUpdate.get("h").getAsString();
        String lowPrice = tickerUpdate.get("l").getAsString();
        String baseAssetVolume = tickerUpdate.get("v").getAsString();
        String quoteAssetVolume = tickerUpdate.get("q").getAsString();
        long openTime = tickerUpdate.get("O").getAsLong();
        long closeTime = tickerUpdate.get("C").getAsLong();
        long firstTradeId = tickerUpdate.get("F").getAsLong();
        long lastTradeId = tickerUpdate.get("L").getAsLong();
        long totalNumberOfTrades = tickerUpdate.get("n").getAsLong();

        System.out.println("Received ticker update for " + symbol);
        System.out.println("Event type: " + eventType);
        System.out.println("Event Time: " + eventTime);
        System.out.println("Price Change: " + priceChange);
        System.out.println("Price Change Percent: " + priceChangePercent);
        System.out.println("Weighted Average Price: " + weightedAvgPrice);
        System.out.println("Last Price: " + lastPrice);
        System.out.println("Last Quantity: " + lastQty);
        System.out.println("Open Price: " + openPrice);
        System.out.println("High Price: " + highPrice);
        System.out.println("Low Price: " + lowPrice);
        System.out.println("Total Traded Base Asset Volume: " + baseAssetVolume);
        System.out.println("Total Traded Quote Asset Volume: " + quoteAssetVolume);
        System.out.println("Statistics Open Time: " + openTime);
        System.out.println("Statistics Close Time: " + closeTime);
        System.out.println("First Trade ID: " + firstTradeId);
        System.out.println("Last Trade Id: " + lastTradeId);
        System.out.println("Total Number of TradesEntity: " + totalNumberOfTrades);
    }
}