package Spring.utils.deserializer.binance;

import Spring.model.pojo.binance.*;
import Spring.utils.deserializer.binance.candlestick.BinanceCoinmFuturesCandlesticksDeserializer;
import Spring.utils.deserializer.binance.candlestick.BinanceSpotCandlesticksDeserializer;
import Spring.utils.deserializer.binance.candlestick.BinanceUsdmFuturesCandlesticksDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class BinanceDeserializer {
    private static final Gson gson = new Gson();

    public static <T> List<T> deserializeBinanceListResponse(String json, Class<T> classOfT) {
        JsonElement root = JsonParser.parseString(json);

        if (root.isJsonArray()) {
            Type type = TypeToken.getParameterized(List.class, classOfT).getType();
            return gson.fromJson(json, type);
        } else if (root.isJsonObject()) {
            // Handle object case, maybe convert it to a singleton list or handle it based on your requirements
            T obj = gson.fromJson(json, classOfT);
            return Collections.singletonList(obj);
        } else {
            // Handle other cases or throw an exception
            throw new IllegalArgumentException("Invalid JSON structure");
        }
    }

    public static <T> T deserializeBinanceResponse(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }


    public static List<BinanceCandlesticks> deserializeCandlesticks(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BinanceCandlesticks.class, new BinanceSpotCandlesticksDeserializer());
        Gson customGson = gsonBuilder.create();
        Type type = new TypeToken<List<BinanceCandlesticks>>() {
        }.getType();
        List<BinanceCandlesticks> dataList = customGson.fromJson(json, type);

        return dataList;
    }

    public static List<BinanceCandlesticks> deserializeCoinmFuturesCandlesticks(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BinanceCandlesticks.class, new BinanceCoinmFuturesCandlesticksDeserializer());
        Gson customGson = gsonBuilder.create();
        Type type = new TypeToken<List<BinanceCandlesticks>>() {
        }.getType();
        List<BinanceCandlesticks> dataList = customGson.fromJson(json, type);

        return dataList;
    }

}