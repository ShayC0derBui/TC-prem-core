package Spring.utils.deserializer.gateio;

import Spring.model.pojo.gateio.futures.*;
import Spring.model.pojo.gateio.spot.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.List;

public class GateioDeserializer {
    private static final Gson gson = new Gson();

    public static List<GateioFuturesOrder> deserializePerpetualFuturesOrderList(String json){
        return gson.fromJson(json, new TypeToken<List<GateioFuturesOrder>>(){}.getType());
    }
    public static GateioFuturesOrder deserializePerpetualFuturesOrder(String json){
        return gson.fromJson(json, new TypeToken<GateioFuturesOrder>(){}.getType());
    }
    public static List<GateioFuturesTickers> deserializePerpetualFuturesTickers(String json){
        return gson.fromJson(json, new TypeToken<List<GateioFuturesTickers>>(){}.getType());
    }

    public static List<GateioFuturesCandlesticks> deserializePerpetualFuturesCandlesticks(String json){
        return gson.fromJson(json, new TypeToken<List<GateioFuturesCandlesticks>>(){}.getType());
    }

    public static GateioFuturesOrderbook deserializePerpetualFuturesOrderbook(String json) {
        return gson.fromJson(json, new TypeToken<GateioFuturesOrderbook>(){}.getType());
    }

    public static List<GateioFuturesTrade> deserializePerpetualFuturesTrades(String json){
        return gson.fromJson(json, new TypeToken<List<GateioFuturesTrade>>(){}.getType());
    }


    public static GateioFuturesContract deserializeSinglePerpetualFuturesContract(String json) {
        return gson.fromJson(json, new TypeToken<GateioFuturesContract>(){}.getType());
    }

    public static List<GateioFuturesOpenInterest> deserializePerpetualFuturesOpenInterest(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioFuturesOpenInterest>>(){}.getType());
    }

    public static List<GateioFuturesContract> deserializePerpetualFuturesContracts(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioFuturesContract>>(){}.getType());
    }

    public static List<GateioFuturesFundingRate> deserializePerpetualFuturesFundingRate(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioFuturesFundingRate>>(){}.getType());
    }

    public static List<GateioSpotTicker> deserializeSpotTickers(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioSpotTicker>>() {
        }.getType());
    }

    public static List<GateioSpotCurrencyPair> deserializeSpotCurrencyPairs(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioSpotCurrencyPair>>() {
        }.getType());
    }

    public static List<GateioSpotTrade> deserializeSpotTrades(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioSpotTrade>>() {
        }.getType());
    }

    public static List<GateioSpotCandlesticks> deserialiseSpotCandles(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(GateioSpotCandlesticks.class, new GateioSpotCandlestickDeserializer());
        Gson customGson = gsonBuilder.create();
        Type type = new TypeToken<List<GateioSpotCandlesticks>>() {
        }.getType();
        List<GateioSpotCandlesticks> dataList = customGson.fromJson(json, type);
        return dataList;
    }

    public static GateioSpotOrderbook deserializeSpotOrderBook(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(GateioSpotOrderbook.class, new GateioSpotOrderbookDeserializer());
        Gson customGson = gsonBuilder.create();
        Type type = new TypeToken<GateioSpotOrderbook>() {
        }.getType();
        GateioSpotOrderbook orderbook = customGson.fromJson(json, type);
        return orderbook;
    }

    public static List<GateioSpotAccount> deserializeSpotAccounts(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioSpotAccount>>() {
        }.getType());
    }

    public static List<GateioSpotOrderList> deserializeSpotOpenOrders(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioSpotOrderList>>() {
        }.getType());
    }

    public static List<GateioSpotOrder> deserializeSpotOrders(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioSpotOrder>>() {
        }.getType());
    }

    public static GateioSpotOrder deserializeSpotOrder(String json) {
        return gson.fromJson(json, new TypeToken<GateioSpotOrder>() {
        }.getType());
    }

    public static List<GateioSpotTrade> deserializeSpotTradingHistory(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioSpotTrade>>() {
        }.getType());
    }

    public static List<GateioFuturesOrder> deserializeDeliveryFuturesOrderList(String json){
        return gson.fromJson(json, new TypeToken<List<GateioFuturesOrder>>(){}.getType());
    }
    public static GateioFuturesOrder deserializeDeliveryFuturesOrder(String json){
        return gson.fromJson(json, new TypeToken<GateioFuturesOrder>(){}.getType());
    }
    public static List<GateioFuturesTickers> deserializeDeliveryFuturesTickers(String json){
        return gson.fromJson(json, new TypeToken<List<GateioFuturesTickers>>(){}.getType());
    }

    public static List<GateioFuturesCandlesticks> deserializeDeliveryFuturesCandlesticks(String json){
        return gson.fromJson(json, new TypeToken<List<GateioFuturesCandlesticks>>(){}.getType());
    }

    public static GateioFuturesOrderbook deserializeDeliveryFuturesOrderbook(String json) {
        return gson.fromJson(json, new TypeToken<GateioFuturesOrderbook>(){}.getType());
    }

    public static List<GateioFuturesTrade> deserializeDeliveryFuturesTrades(String json){
        return gson.fromJson(json, new TypeToken<List<GateioFuturesTrade>>(){}.getType());
    }

    public static GateioFuturesContract deserializeSingleDeliveryFuturesContract(String json) {
        return gson.fromJson(json, new TypeToken<GateioFuturesContract>(){}.getType());
    }
    public static List<GateioFuturesContract> deserializeDeliveryFuturesContracts(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioFuturesContract>>(){}.getType());
    }

    public static List<GateioFuturesCancelAllOrders> deserializeDeliveryCancelAllOrders(String json) {
        return gson.fromJson(json, new TypeToken<List<GateioFuturesCancelAllOrders>>(){}.getType());
    }
}