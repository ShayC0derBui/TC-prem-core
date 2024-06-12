//package Spring.callRest;
//
//import Spring.client.kucoin.KucoinHttpClient;
//import Spring.service.external.kucoin.futures.KucoinFuturesRest;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
//
//public class KucoinMapHandler {
//    public void invokeKucoin(TextWebSocketFrame frame) {
//                try {
//
//        KucoinFuturesRest client = new KucoinFuturesRest(new KucoinHttpClient("https://api-futures.kucoin.com"));
//        String receivedText = frame.text();
//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson(receivedText, JsonObject.class);
//
//        String clientOid = jsonObject.get("clientOid").getAsString();
//        String side = jsonObject.get("side").getAsString();
//        String symbol = jsonObject.get("symbol").getAsString();
//        String leverage = jsonObject.get("leverage").getAsString();
//        String type = jsonObject.get("type").getAsString();
//        String price = jsonObject.get("price").getAsString();
//        String size = jsonObject.get("size").getAsString();
//
//            //client.postKucoinFuturesCreateOrder(clientOid, side, symbol, leverage, type, price, size);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
