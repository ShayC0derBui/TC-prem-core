//package Spring.callRest;
//
//import Spring.client.bybit.BybitHttpClient;
//import Spring.service.external.bybit.futures.perpetual.rest.*;
//import Spring.utils.constants.bybit.BybitConstants;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ByBitMapHandler {
//
//    public void invokeBybit(TextWebSocketFrame frame) {
//        BybitLinearRest client = new BybitLinearRest(new BybitHttpClient(BybitConstants.TESTNET_BASE_URL));
//        String receivedText = frame.text();
//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson(receivedText, JsonObject.class);
//
//        try {
//            Map<String, Object> byBitData = createByBitData(jsonObject);
//            System.out.println("ByBit Data: " + byBitData);
//
//            //client.postLinearBybitCreateOrder(byBitData);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Map<String, Object> createByBitData(JsonObject jsonObject) {
//        Map<String, Object> byBitData = new HashMap<>();
//        byBitData.put("category", jsonObject.get("category").getAsString());
//        byBitData.put("symbol", jsonObject.get("symbol").getAsString());
//        byBitData.put("price", jsonObject.get("price"));
//        byBitData.put("side", jsonObject.get("side").getAsString());
//        byBitData.put("orderType", jsonObject.get("orderType").getAsString());
//        byBitData.put("qty", jsonObject.get("qty").getAsString());
//        return byBitData;
//    }
//
//
//
//}