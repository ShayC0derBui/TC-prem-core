//package Spring.service.external.bybit.futures.perpetual.inverse;
//
//import Spring.client.bybit.BybitHttpClient;
//import Spring.service.external.bybit.futures.perpetual.rest.BybitLinearRest;
//import Spring.utils.constants.bybit.BybitConstants;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//public class BybitInversePerpsTester {
//    private static final BybitLinearRest client = new BybitLinearRest(new BybitHttpClient(BybitConstants.TESTNET_BASE_URL));
//
//
//
//    public static void main(String[] args) throws IOException {
//        try {
////            client.getBybitPerpetualFuturesSymbols(new HashMap<String, Object>() {{
////                put("category", "inverse");
////            }});
////            client.getBybitFundingHistory(new HashMap<String, Object>() {{
////                put("category", "inverse");
////                put("symbol", "BTCUSD");
////            }});
////            client.getBybitPublicTradingHistory(new HashMap<String, Object>() {{
////                put("category", "inverse");
////                put("symbol", "BTCUSD");
////            }});
////            client.getBybitOpenInterest(new HashMap<String, Object>() {{
////                put("category", "inverse");
////                put("symbol", "BTCUSD");
////                put("intervalTime", "1d");
////            }});
////            client.getBybitOrderBook(new HashMap<String, Object>() {{
////                put("category", "inverse");
////                put("symbol", "BTCUSD");
////            }});
////            client.getBybitFuturesKline(new HashMap<String, Object>() {{
////                put("category", "inverse");
////                put("symbol", "BTCUSD");
////                put("intervalTime", "D");
////            }});
////            client.getBybitPerpetualFuturesInstrumentsInfo(Category.inverse, null);
////            client.getBybitPerpetualFuturesPositionInfo(Category.inverse, null);
////            client.postBybitCreateOrder(Category.inverse, Symbol._BTCUSD, OrderSide.Buy, OrderType.Limit, "100",
////                    "10000");
////            client.postBybitCreateOrder(new HashMap<String,Object>(){{
////                put("category", "inverse");
////                put("symbol", "BTCUSD");
////                put("side", "Buy");
////                put("type", "Limit");
////                put("qty", "100");
////                put("price", "10000");
////            }});
////            client.getBybitPosition(new HashMap<String, Object>() {{
////                put("category", "linear");
////                put("symbol", "BTCUSDT");
////            }});
////            client.postBybitCreateOrder(queryParams);
////            client.postBybitCancelOrder(Category.inverse, Symbol._BTCUSD, "9500c13c-6444-453c-abbf-c494c0669b9a");
////            client.postBybitAmendOrder(Category.inverse, Symbol._BTCUSD, "21793087-2745-405f-aed6-c4f8475e2f0e", "101",
////                    "10001");
////            client.postBybitCancelAll(Category.inverse, Symbol._BTCUSD);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
