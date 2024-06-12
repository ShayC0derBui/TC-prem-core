//package Spring.utils.deserializer.kucoin.futures;
//
//import Spring.model.pojo.kucoin.futures.*;
//import com.google.gson.*;
//import com.google.gson.reflect.TypeToken;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Deserializer {
//
//    private static final Gson gson = new Gson();
//
//    public static DataListResponse<KucoinFuturesContracts> deserializeKucoinFuturesContracts(String json) {
//        return gson.fromJson(json, DataListResponse.class);
//    }
//
//
//    public static KucoinFuturesOrderbook deserializeKucoinFuturesOrderbook(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(dataObject, KucoinFuturesOrderbook.class);
//    }
//
//    public static KucoinFuturesTicker deserializeKucoinFuturesTickers(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(dataObject, KucoinFuturesTicker.class);
//    }
//
//    public static List<KucoinFuturesTradeHistory> deserializeKucoinFuturesTradeHistory(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonArray dataArray = jsonObject.getAsJsonArray("data");
//
//        List<KucoinFuturesTradeHistory> tradeHistoryList = new ArrayList<>();
//
//        for (JsonElement element : dataArray) {
//            KucoinFuturesTradeHistory tradeHistory = gson.fromJson(element, KucoinFuturesTradeHistory.class);
//            tradeHistoryList.add(tradeHistory);
//        }
//        return tradeHistoryList;
//    }
//
//    public static KucoinFuturesPremiumIndex deserializeKucoinFuturesIndex(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(dataObject, KucoinFuturesPremiumIndex.class);
//    }
//
//    public static KucoinFuturesCurrentMarkPrice deserializeKucoinFuturesCurrentMarkPrice(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(dataObject, KucoinFuturesCurrentMarkPrice.class);
//    }
//
//
//    public static KucoinFuturesPosition deserializeKucoinFuturesPosition(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(dataObject, KucoinFuturesPosition.class);
//    }
//
//    public static KucoinFuturesFills deserializeKucoinFuturesFills(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(dataObject, KucoinFuturesFills.class);
//    }
//
//    public static List<KucoinKline> deserializeKucoinFuturesKline(String json) {
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(KucoinFuturesKlineEntry.class, new KucoinKlineDeserializer());
//        Gson customGson = gsonBuilder.create();
//        Type type = new TypeToken<KucoinFuturesKlineEntry>() {
//        }.getType();
//        List<KucoinKline> response = customGson.fromJson(json, type);
//
//        return response;
//    }
//
//    public static List<KucoinFuturesOpenInterestGenericResponse.KucoinFuturesOpenInterest> deserializeKucoinFuturesOpenInterest(String json) {
//        JsonArray jsonArray = JsonParser.parseString(json).getAsJsonObject().getAsJsonObject("data").getAsJsonArray("dataList");
//        return gson.fromJson(jsonArray, new TypeToken<List<KucoinFuturesOpenInterestGenericResponse.KucoinFuturesOpenInterest>>() {}.getType());
//    }
//
//    public static KucoinFuturesFundingRate deserializeKucoinFuturesFundingRate(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject jsonDataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(jsonDataObject, KucoinFuturesFundingRate.class);
//    }
//
//    public static KucoinFuturesCreateOrder deserializeCreateOrder(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(dataObject, KucoinFuturesCreateOrder.class);
//    }
//
//    public static KucoinFuturesFundingHistory deserializeKucoinFuturesFundingHistory(String json) {
//        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
//        JsonObject dataObject = jsonObject.getAsJsonObject("data");
//        return gson.fromJson(dataObject, KucoinFuturesFundingHistory.class);
//    }
//}
