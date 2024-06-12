//package Spring.callRest;
//
//import Spring.client.binance.BinanceHttpClient;
//import Spring.service.external.binance.futures.coinm.rest.BinanceFuturesCoinmRest;
//import Spring.service.external.binance.futures.usdm.rest.BinanceFuturesUsdmRest;
//import Spring.utils.constants.binance.BinanceConstants;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class BinanceMapHandler {
//
//    public void invokeBinance(TextWebSocketFrame frame) {
//        BinanceFuturesUsdmRest usdmClient = new BinanceFuturesUsdmRest(new BinanceHttpClient(BinanceConstants.TESTNET_FUTURE_URL));
//        BinanceFuturesCoinmRest coinmClient = new BinanceFuturesCoinmRest(new BinanceHttpClient(BinanceConstants.TESTNET_FUTURE_URL));
//
//        String receivedText = frame.text();
//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson(receivedText, JsonObject.class);
//
//        try {
//            Map<String, Object> binanceData = createBinanceData(jsonObject);
//            System.out.println("Binance Data: " + binanceData);
//
//            if ("USDM".equals(binanceData.get("contracts"))) {
//
//                //usdmClient.postBinanceCreateOrder(binanceData);
//                //usdmClient.deleteBinanceCancelOrder(binanceData);
//            } else if ("COINM".equals(binanceData.get("contracts"))) {
//                //coinmClient.postBinanceCoinmCreateOrder(binanceData);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Map<String, Object> createBinanceData(JsonObject jsonObject) {
//        Map<String, Object> binanceData = new HashMap<>();
//        binanceData.put("exchange", jsonObject.get("exchange"));
//        binanceData.put("symbol", jsonObject.get("symbol").getAsString().trim());
//        binanceData.put("side", jsonObject.get("side").getAsString().toUpperCase());
//        binanceData.put("type", jsonObject.get("type").getAsString().toUpperCase());
//        binanceData.put("timeInForce", jsonObject.get("timeInForce").getAsString().toUpperCase());
//        binanceData.put("quantity", jsonObject.get("quantity").getAsDouble());
//        binanceData.put("price", jsonObject.get("price").getAsDouble());
//        binanceData.put("contracts", jsonObject.get("contracts"));
//        return binanceData;
//    }
//}
