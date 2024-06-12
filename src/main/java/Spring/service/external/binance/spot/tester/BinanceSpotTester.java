//package Spring.service.external.binance.spot.tester;
//
//import Spring.client.binance.BinanceHttpClient;
//import Spring.service.external.binance.spot.rest.BinanceSpotRestClient;
//import Spring.utils.constants.binance.BinanceConstants;
//
//import java.util.HashMap;
//
//public class BinanceSpotTester {
//    public static void main(String[] args) {
//        BinanceSpotRestClient client = new BinanceSpotRestClient(new BinanceHttpClient(BinanceConstants.BASE_URL));
//
//        try {
//            client.getBybitSpotTickers();
//            client.getSpotCandlesticks(new HashMap<>() {{
//                put("symbol", "BTCUSDT");
//                put("interval", "1m");
//            }});
//            client.getSpotOrderBook(new HashMap<>() {{
//                put("symbol", "BTCUSDT");
//            }});
//            client.getSpotRecentTrades(new HashMap<>() {{
//                put("symbol", "BTCUSDT");
//            }});
//            client.getSpotAggregateTrades(new HashMap<String, Object>() {{
//                put("symbol", "BTCUSDT");
//            }});
//
//            client.getAllSpotOrders(new HashMap<String, Object>() {{
//                put("symbol", "BTCUSDT");
//            }});
//            client.getOpenOrders(new HashMap<String, Object>() {{
//                put("symbol", "BTCUSDT");
//            }});
//            //TODO: Create Order
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
