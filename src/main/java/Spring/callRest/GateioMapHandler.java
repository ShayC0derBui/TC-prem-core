//package Spring.callRest;
//
//import Spring.client.gateio.GateioHttpClient;
//import Spring.service.external.gateio.futures.delivery.rest.GateioFuturesDeliveryRest;
//import Spring.service.external.gateio.futures.perpetual.rest.GateioFuturesPerpetualRest;
//import Spring.utils.constants.gateio.GateioConstants;
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//public class GateioMapHandler {
//
//    public void invokeGateio(TextWebSocketFrame frame) throws IOException {
//        GateioFuturesPerpetualRest perpsClient = new GateioFuturesPerpetualRest(new GateioHttpClient(GateioConstants.GATEIO_BASE_URL));
//        GateioFuturesDeliveryRest deliveryClient = new GateioFuturesDeliveryRest(new GateioHttpClient(GateioConstants.GATEIO_BASE_URL));
//
//        String receivedText = frame.text();
//        Gson gson = new Gson();
//        JsonObject jsonObject = gson.fromJson(receivedText, JsonObject.class);
//
//        try {
//            Map<String, Object> gateioData = createGateioData(jsonObject);
//            System.out.println("Gateio Data: " + gateioData);
//
//            if (jsonObject.has("settle")) {
//                gateioData.put("settle", jsonObject.get("settle").getAsString().trim());
//                //perpsClient.postSinglePerpetualFuturesOrder(gateioData);
//            } else if (jsonObject.has("settleCurrency")) {
//                gateioData.put("settleCurrency", jsonObject.get("settleCurrency").getAsString().trim());
//                //deliveryClient.postSingleDeliveryFuturesOrder(gateioData);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Map<String, Object> createGateioData(JsonObject jsonObject) {
//        Map<String, Object> gateioData = new HashMap<>();
//        gateioData.put("contract", jsonObject.get("contract").getAsString().trim());
//        gateioData.put("size", jsonObject.get("size").getAsDouble());
//        gateioData.put("iceberg", jsonObject.get("iceberg").getAsDouble());
//        gateioData.put("price", jsonObject.get("price").getAsDouble());
//        gateioData.put("tif", jsonObject.get("tif").getAsString().toUpperCase());
//        return gateioData;
//    }
//}
