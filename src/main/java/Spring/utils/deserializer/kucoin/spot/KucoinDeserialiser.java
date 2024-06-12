//package Spring.utils.deserializer.kucoin.spot;
//
//import Spring.model.pojo.kucoin.futures.KucoinFuturesKlineEntry;
//import Spring.model.pojo.kucoin.spot.*;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.List;
//
//public class KucoinDeserialiser {
//    private static final Gson gson = new Gson();
//
//    public static KucoinSpotResponse deserialise(String json) {
//        return gson.fromJson(json, new TypeToken<KucoinSpotResponse>(){}.getType());
//    }
//    public static KucoinSpotCurrencyPairsResponse deserializeSpotCurrencyPairs(String json) {
//        return gson.fromJson(json, new TypeToken<KucoinSpotCurrencyPairsResponse>(){}.getType());
//    }
//    public static KucoinSpotTickersResponse deserializeSpotTickers(String json) {
//        return gson.fromJson(json, new TypeToken<KucoinSpotTickersResponse>(){}.getType());
//    }
//
//    public static KucoinSpotOrderbook deserializeSpotOrderBook(String json) {
//        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
//        JsonObject data = jsonObject.get("data").getAsJsonObject();
//        return gson.fromJson(data, new TypeToken<KucoinSpotOrderbook>(){}.getType());
//    }
//
//    public static List<KucoinRecentTradesSpot> deserializeRecentTrades(String json) {
//        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
//        return gson.fromJson(jsonObject.get("data"), new TypeToken<List<KucoinRecentTradesSpot>>(){}.getType());
//    }
//
//    public static List<KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks> deserializeSpotCandlesticks(String json) {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(KucoinSpotCandlesticksGeneric.class, new KucoinCandlesticksSpotDeserializer());
//        Gson customGson = gsonBuilder.create();
//        Type type = new TypeToken<KucoinSpotCandlesticksGeneric>() {
//        }.getType();
//        List<KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks> response = customGson.fromJson(json, type);
//        return response;
//    }
//}
