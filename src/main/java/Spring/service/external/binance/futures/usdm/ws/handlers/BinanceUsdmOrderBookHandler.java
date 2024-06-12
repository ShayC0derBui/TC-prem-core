package Spring.service.external.binance.futures.usdm.ws.handlers;

import Spring.service.external.binance.futures.usdm.ws.BinanceWsMessageHandler;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
public class BinanceUsdmOrderBookHandler implements BinanceWsMessageHandler {
    @Override
    public void handleMessage(String message)  {
        JsonObject tradeUpdate = JsonParser.parseString(message).getAsJsonObject();
        String symbol = tradeUpdate.get("s").getAsString();
        String bidPrice = tradeUpdate.get("b").getAsString();
        String bidQuantity = tradeUpdate.get("B").getAsString();
        String askPrice = tradeUpdate.get("a").getAsString();
        String askQuantity = tradeUpdate.get("A").getAsString();

        System.out.println("Received Order Book update for " + symbol);
        System.out.println("Bid Price " + bidPrice);
        System.out.println("Bid Quantity: " + bidQuantity);
        System.out.println("Ask Price: " + askPrice);
        System.out.println("Ask Quantity: " + askQuantity);
    }
}

