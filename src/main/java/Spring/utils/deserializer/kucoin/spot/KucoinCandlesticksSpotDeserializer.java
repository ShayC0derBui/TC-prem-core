//package Spring.utils.deserializer.kucoin.spot;
//
//import Spring.model.pojo.kucoin.spot.KucoinSpotCandlesticksGeneric;
//import com.google.gson.*;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//public class KucoinCandlesticksSpotDeserializer implements JsonDeserializer<List<KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks>> {
//    @Override
//    public List<KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//        KucoinSpotCandlesticksGeneric response = new KucoinSpotCandlesticksGeneric();
//
//        // Deserialize the JSON into the response object as needed
//        JsonObject jsonObject = json.getAsJsonObject();
//
//        // Deserialize 'list' array (use your existing logic)
//        if (jsonObject.has("data")) {
//            JsonArray listArray = jsonObject.getAsJsonArray("data");
//            List<KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks> klines = new ArrayList<>();
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
//                KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks kline = new KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks();
//                kline.setTime(klineArray.get(0).getAsString());
//                kline.setOpen(klineArray.get(1).getAsString());
//                kline.setClose(klineArray.get(2).getAsString());
//                kline.setHigh(klineArray.get(3).getAsString());
//                kline.setLow(klineArray.get(4).getAsString());
//                kline.setAmount(klineArray.get(5).getAsString());
//                // Add the kline object to the list
//                klines.add(kline);
//
//            }
//            response.setData(klines);
//        }
//        return (response.getData());
//    }
//}
//
