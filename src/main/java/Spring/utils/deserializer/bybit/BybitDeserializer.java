package Spring.utils.deserializer.bybit;

import Spring.model.pojo.bybit.futures.perpetual.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class BybitDeserializer {
    private static final Gson gson = new GsonBuilder().create();

    public static <T> BybitGenericResponse<T> deserializeResponse(String json, Class<T> clazz) {
        Type responseType = TypeToken.getParameterized(BybitGenericResponse.class, clazz).getType();
        return gson.fromJson(json, responseType);
    }

    public static BybitKlineGenericResponse deserializeBybitKline(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BybitKlineGenericResponse.class, new BybitKlineDeserializer());
        Gson customGson = gsonBuilder.create();
        Type type = new TypeToken<BybitKlineGenericResponse>() {
        }.getType();
        return customGson.fromJson(json, type);
    }

    public static BybitOrderBookGenericResponse deserializeBybitOrderBook(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(BybitOrderBookGenericResponse.class, new BybitOrderBookDeserializer());
        Gson customGson = gsonBuilder.create();
        Type type = new TypeToken<BybitOrderBookGenericResponse>() {
        }.getType();
        return customGson.fromJson(json, type);
    }

    public static BybitCreateOrder deserializeBybitCreateOrder(String json) {
        return gson.fromJson(json, new TypeToken<BybitCreateOrder>() {
        }.getType());
    }

    public static ByBitCancelOrder deserializeBybitCancelOrder(String json) {
        return gson.fromJson(json, new TypeToken<ByBitCancelOrder>() {
        }.getType());
    }

    public static BybitAmendOrder deserializeBybitAmendOrder(String json) {
        return gson.fromJson(json, new TypeToken<BybitAmendOrder>() {
        }.getType());
    }
}

