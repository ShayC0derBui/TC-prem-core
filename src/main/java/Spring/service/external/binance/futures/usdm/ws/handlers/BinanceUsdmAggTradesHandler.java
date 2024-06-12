package Spring.service.external.binance.futures.usdm.ws.handlers;

import Spring.service.external.binance.futures.usdm.ws.BinanceWsMessageHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BinanceUsdmAggTradesHandler implements BinanceWsMessageHandler {

    @Override
    public void handleMessage(String message) {
        JsonObject tradeUpdate = JsonParser.parseString(message).getAsJsonObject();

        String eventType = tradeUpdate.get("e").getAsString();
        long eventTime = tradeUpdate.get("E").getAsLong();
        String symbol = tradeUpdate.get("s").getAsString();
        long aggTradeId = tradeUpdate.get("a").getAsLong();
        String price = tradeUpdate.get("p").getAsString();
        String quantity = tradeUpdate.get("q").getAsString();
        long firstTradeId = tradeUpdate.get("f").getAsLong();
        long lastTradeId = tradeUpdate.get("l").getAsLong();
        long tradeTime = tradeUpdate.get("T").getAsLong();
        boolean isBuyerMarketMaker = tradeUpdate.get("m").getAsBoolean();

        System.out.println("Received aggregate trade update for " + symbol);
        System.out.println("Event type: " + eventType);
        System.out.println("Event Time: " + eventTime);
        System.out.println("Aggregate Trade ID: " + aggTradeId);
        System.out.println("Price: " + price);
        System.out.println("Quantity: " + quantity);
        System.out.println("First Trade ID: " + firstTradeId);
        System.out.println("Last Trade ID: " + lastTradeId);
        System.out.println("Trade Time: " + tradeTime);
        System.out.println("Is the Buyer the Market Maker? " + isBuyerMarketMaker);
    }
}