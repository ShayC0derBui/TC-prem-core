//package Spring.service.external.bybit.spot.tester;
//
//import Spring.client.bybit.BybitHttpClient;
//import Spring.service.external.bybit.spot.BybitSpotRest;
//import Spring.utils.constants.bybit.BybitConstants;
//
//import java.util.HashMap;
//
//public class BybitSpotTester {
//    public static void main(String[] args) {
//        BybitSpotRest client = new BybitSpotRest(new BybitHttpClient(BybitConstants.TESTNET_BASE_URL));
//
//        try {
//            client.getBybitSpotTickers(new HashMap<>() {{
//                put("category", "spot");
//            }});
////            client.getBybitSpotOrderBook(new HashMap<>() {{
////               put("category", "spot");
////               put("symbol", "BTCUSDT");
////            }});
////            client.getBybitSpotKline(new HashMap<String, Object>() {{
////                put("category", "spot");
////                put("symbol", "BTCUSDT");
////                put("interval", "D");
////            }});
////            client.postBybitSpotCreateOrder(new HashMap<String, Object>(){{
////                put("category", "spot");
////                put("symbol", "ADAUSDT");
////                put("side", "Buy");
////                put("type", "Limit");
////                put("qty", "10");
////                put("price", "0.35");
////            }});
////            client.getBybitSpotOpenOrders(new HashMap<String, Object>() {{
////                put("category", "spot");
////            }});
////            client.getBybitSpotRecentTrades(new HashMap<String, Object>() {{
////                put("category", "spot");
////            }});
////            client.postBybitSpotCancelOrder(new HashMap<String,Object>(){{
////                put("category", "spot");
////                put("symbol", "BTCUSDT");
////                put("orderId", "1519852569258710272");
////            }});
////            client.postBybitSpotCancelAll(new HashMap<String,Object>(){{
////                put("category", "spot");
////            }});
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
