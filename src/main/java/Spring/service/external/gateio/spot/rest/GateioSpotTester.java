//package Spring.service.external.gateio.spot.rest;
//
//import Spring.client.gateio.GateioHttpClient;
//import Spring.utils.constants.gateio.GateioConstants;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.Map;
//
//public class GateioSpotTester {
//    private static final String CURRENCY_PAIR = "BTC_USDT";
//
//    public static void testGetSpotCandlesticks(GateioSpotRestClient client) {
//        try {
//            client.getSpotCandlesticks(Map.of("currency_pair", CURRENCY_PAIR));
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testGetSpotOrderbook(GateioSpotRestClient client) {
//        try {
//            client.getSpotOrderbook(Map.of("currency_pair", CURRENCY_PAIR));
//        } catch (IOException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void testGetSpotTradeHistory(GateioSpotRestClient client) {
//        try {
//            client.getSpotTradingHistory(Map.of("currency_pair", CURRENCY_PAIR));
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        GateioHttpClient httpClient = new GateioHttpClient(GateioConstants.GATEIO_BASE_URL);
//        GateioSpotRestClient client = new GateioSpotRestClient(httpClient);
//
////        testGetSpotCandlesticks(client);
////        testGetSpotOrderbook(client);
//        testGetSpotTradeHistory(client);
//    }
//}