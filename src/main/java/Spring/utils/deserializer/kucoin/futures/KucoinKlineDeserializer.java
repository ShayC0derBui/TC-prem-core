//package Spring.utils.deserializer.kucoin.futures;
//
//import Spring.model.pojo.kucoin.futures.KucoinFuturesKlineEntry;
//import Spring.model.pojo.kucoin.futures.KucoinKline;
//import com.google.gson.*;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//public class KucoinKlineDeserializer implements JsonDeserializer<List<KucoinKline>> {
//    @Override
//    public List<KucoinKline> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        KucoinFuturesKlineEntry response = new KucoinFuturesKlineEntry();
//
//        // Deserialize the JSON into the response object as needed
//        JsonObject jsonObject = json.getAsJsonObject();
//
//        // Deserialize 'list' array (use your existing logic)
//        if (jsonObject.has("data")) {
//            JsonArray listArray = jsonObject.getAsJsonArray("data");
//            List<KucoinKline> klines = new ArrayList<>();
//
//            for (JsonElement element : listArray) {
//                if (!element.isJsonArray()) {
//                    throw new JsonParseException("Invalid element in the 'list' array. Expected an array.");
//                }
//
//                JsonArray klineArray = element.getAsJsonArray();
//
//                // Ensure that the array has at least 7 elements (adjust as needed)
//                if (klineArray.size() < 6) {
//                    throw new JsonParseException("Invalid number of elements in a 'list' array element.");
//                }
//
//                // Extract data from the array and create a BybitKline object
//                KucoinKline kline = new KucoinKline();
//                kline.setTime(klineArray.get(0).getAsString());
//                kline.setEntryPrice(klineArray.get(1).getAsString());
//                kline.setHighPrice(klineArray.get(2).getAsString());
//                kline.setLowPrice(klineArray.get(3).getAsString());
//                kline.setClosePrice(klineArray.get(4).getAsString());
//                kline.setTradingVolume(klineArray.get(5).getAsString());
//
//                // Add the kline object to the list
//                klines.add(kline);
//
//            }
//            response.setData(klines);
//        }
//        return response.getData();
//    }
//}
//
