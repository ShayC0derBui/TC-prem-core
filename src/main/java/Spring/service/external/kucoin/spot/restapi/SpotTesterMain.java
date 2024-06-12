//package Spring.service.external.kucoin.spot.restapi;
//
//import Spring.client.kucoin.KucoinHttpClient;
//
//import java.io.IOException;
//import java.util.Map;
//
//import static org.yaml.snakeyaml.nodes.NodeId.sequence;
//
//public class SpotTesterMain {
//    public static void main(String[] args) throws IOException {
//        KucoinSpotRestClient client = new KucoinSpotRestClient(new KucoinHttpClient("https://api.kucoin.com"));
//
//        try {
////            System.out.println(client.getOrderBook(Map.of("symbol", "BTC-USDT")));
////            client.getKucoinSpotCandlestick(Map.of("symbol", "BTC-USDT", "type", "1day"));
//            client.getKucoinRecentSpotTrades(Map.of("symbol", "BTC-USDT"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
