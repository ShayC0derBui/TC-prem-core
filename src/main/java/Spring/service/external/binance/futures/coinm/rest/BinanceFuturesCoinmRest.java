package Spring.service.external.binance.futures.coinm.rest;

import Spring.client.binance.BinanceHttpClient;
import Spring.client.binance.CustomBinanceCMFuturesClientImpl;
import Spring.model.pojo.binance.*;
import Spring.model.pojo.binance.futures.coinm.*;
import Spring.model.pojo.binance.futures.usdm.BatchOrderRequest;
import Spring.service.user.KeyService;
import com.binance.connector.futures.client.exceptions.BinanceClientException;
import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import Spring.model.pojo.binance.futures.usdm.BinanceUsdmChangeInitialLeverage;
import Spring.model.pojo.binance.futures.usdm.BinanceUsdmOpenOrders;
import Spring.model.rest.adapters.binance.*;
import Spring.model.rest.interfaces.*;
import Spring.utils.deserializer.binance.BinanceDeserializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static Spring.utils.constants.binance.BinanceConstants.*;

public class BinanceFuturesCoinmRest {
    private final BinanceHttpClient httpClient;
    @Autowired
    private final ObjectMapper objectMapper;
    private final KeyService keyservice;

    @Autowired
    public BinanceFuturesCoinmRest(BinanceHttpClient httpClient, ObjectMapper objectMapper, KeyService keyService) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
        this.keyservice = keyService;
    }

    public List<Contracts> getBinanceCoinmFuturesContracts() throws IOException {
        String endpoint = COINM_FUTURES_TICKER_PRICE;
        String priceResponse = httpClient.get(COINM_FUTURES_TICKER_PRICE);
        String binance24hrResponse = httpClient.get(COINM_FUTURES_24HR_TICKER);
        List<BinanceSymbol> priceList = BinanceDeserializer.deserializeBinanceListResponse(priceResponse, BinanceSymbol.class);
        List<BinanceFutures24HrTickerPriceChange> binanceUsdm24hrDataList = BinanceDeserializer.deserializeBinanceListResponse(binance24hrResponse, BinanceFutures24HrTickerPriceChange.class);

        // Create a map from symbol to BinanceSymbol for efficient lookup
        Map<String, BinanceSymbol> symbolToBinanceSymbol = priceList.stream()
                .collect(Collectors.toMap(BinanceSymbol::getSymbol, java.util.function.Function.identity()));

        // Merge the two lists based on the common field 'symbol'
        List<Contracts> mergedList = binanceUsdm24hrDataList.stream()
                .map(binance24hrData -> {
                    BinanceSymbol binanceSymbol = symbolToBinanceSymbol.get(binance24hrData.getSymbol());
                    if (binanceSymbol != null) {
                        return new BinanceContractAdapter(binance24hrData, binanceSymbol, endpoint) {
                        };
                    }
                    return null; // Handle the case where there's no match
                })
                .filter(Objects::nonNull) // Filter out null entries
                .collect(Collectors.toList());
        return mergedList;
    }

    public JsonNode getBinanceCoinmFuturesOrderBook(Map<String, Object> params) throws IOException, URISyntaxException {
        String response = httpClient.getWithQueryParams(COINM_FUTURES_ORDER_BOOK, params);
//        BinanceOrderBook res = BinanceDeserializer.deserializeBinanceResponse(response, BinanceOrderBook.class);
//        return new BinanceOrderBookAdapter(res, params.get("symbol").toString());
        System.out.println(response);
        return objectMapper.readTree(response);
    }


    public List<RecentTrades> getBinanceCoinmFuturesTrades(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = COINM_FUTURES_TRADES;
        String response = httpClient.getWithQueryParams(endpoint, params);
        List<BinanceRecentTrades> binanceRecentTrades = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceRecentTrades.class);
        System.out.println(binanceRecentTrades);
        List<BinanceRecentTrades> adaptedData = new Gson().fromJson(new Gson().toJson(binanceRecentTrades), new TypeToken<List<BinanceRecentTrades>>() {
        }.getType());
        return adaptedData.stream().map(trade -> new BinanceRecentTradesAdapter(trade, endpoint, params.get("symbol").toString())).collect(Collectors.toList());
    }

    public List<CandleStick> getBinanceCoinmFuturesCandlesticks(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = COINM_FUTURES_KLINE;
        String response = httpClient.getWithQueryParams(endpoint, params);
        List<BinanceCandlesticks> candlesticks = BinanceDeserializer.deserializeCoinmFuturesCandlesticks(response);
        System.out.println(candlesticks);

        return candlesticks.stream().map(trades -> new BinanceCandleStickAdapter(trades, endpoint, params.get("symbol").toString())).collect(Collectors.toList());
    }

    public List<BinanceCoinmFuturesPremiumIndex> getBinanceCoinmFuturesPremiumIndex() throws IOException {
        String response = httpClient.get(COINM_FUTURES_PREMIUM_INDEX);
        List<BinanceCoinmFuturesPremiumIndex> binanceCoinmFuturesPremiumIndices = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceCoinmFuturesPremiumIndex.class);
        System.out.println(binanceCoinmFuturesPremiumIndices);

        return binanceCoinmFuturesPremiumIndices;
    }

    public JsonNode getBinanceCoinmFuturesFundingRateHistory(Map<String, Object> params) throws IOException, URISyntaxException {
        String response = httpClient.getWithQueryParams(COINM_FUTURES_FUNDING_RATE, params);
        System.out.println(response);
//        List<BinanceFuturesFundingRateHistory> binanceFuturesFundingRateHistoryList = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceFuturesFundingRateHistory.class);
//        ;
//        List<BinanceFuturesFundingRateHistory> adaptedData = new Gson().fromJson(new Gson().toJson(binanceFuturesFundingRateHistoryList), new TypeToken<List<BinanceFuturesFundingRateHistory>>() {
//        }.getType());
//        return adaptedData.stream()
//                .map(BinanceFundingRateAdapter::new)
//                .collect(Collectors.toList());
        return objectMapper.readTree(response);
    }

    //getBinanceCoinmFuturesHistoricalTrades
    public List<RecentTrades> getBinanceCoinmFuturesHistoricalTrades(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = COINM_FUTURES_TRADES;
        String response = httpClient.getWithQueryParams(endpoint, params);
        List<BinanceRecentTrades> binanceRecentTrades = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceRecentTrades.class);
        System.out.println(binanceRecentTrades);
        List<BinanceRecentTrades> adaptedData = new Gson().fromJson(new Gson().toJson(binanceRecentTrades), new TypeToken<List<BinanceRecentTrades>>() {
        }.getType());
        return adaptedData.stream().map(trade -> new BinanceRecentTradesAdapter(trade, endpoint, params.get("symbol").toString())).collect(Collectors.toList());
    }

    public List<BinanceFutures24HrTickerPriceChange> getBinanceCoinmFutures24HrTickerPriceChange() throws IOException {
        String response = httpClient.get(COINM_FUTURES_24HR_TICKER);
        List<BinanceFutures24HrTickerPriceChange> binanceCoinmFutures24HrTickerPriceChanges = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceFutures24HrTickerPriceChange.class);
        System.out.println(binanceCoinmFutures24HrTickerPriceChanges);

        return binanceCoinmFutures24HrTickerPriceChanges;
    }

    public JsonNode getBinanceCoinmFuturesOpenInterest(Map<String, Object> params) throws IOException, URISyntaxException {
        String response = httpClient.getWithQueryParams(COINM_FUTURES_OPEN_INTEREST, params);
//        BinanceFuturesOpenInterest binanceFuturesOpenInterest = BinanceDeserializer.deserializeBinanceResponse(response, BinanceFuturesOpenInterest.class);
        System.out.println(response);
        return objectMapper.readTree(response);
    }

    public List<BinanceCoinmFuturesTopLongShortRatio> getBinanceCoinmFuturesTopLongShortAccountRatio(Map<String, Object> params) throws IOException, URISyntaxException {
        String response = httpClient.getWithQueryParams(COINM_FUTURES_TOP_LONG_SHORT_ACCOUNT_RATIO, params);

        List<BinanceCoinmFuturesTopLongShortRatio> binanceCoinmFuturesTopLongShortRatioList = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceCoinmFuturesTopLongShortRatio.class);
        System.out.println("LongShortAccountRatio" + binanceCoinmFuturesTopLongShortRatioList);

        return binanceCoinmFuturesTopLongShortRatioList;
    }

    public List<BinanceCoinmFuturesTopLongShortRatio> getBinanceCoinmFuturesTopLongShortPositionRatio(Map<String, Object> params) throws IOException, URISyntaxException {
        String response = httpClient.getWithQueryParams(COINM_FUTURES_TOP_LONG_SHORT_POSITION_RATIO, params);

        List<BinanceCoinmFuturesTopLongShortRatio> binanceCoinmFuturesTopLongShortRatioList = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceCoinmFuturesTopLongShortRatio.class);
        System.out.println("LongShortPositionRatio" + binanceCoinmFuturesTopLongShortRatioList);

        return binanceCoinmFuturesTopLongShortRatioList;
    }

    //Todo: testing
//    public JsonNode postBinanceCoinmCreateOrder(Map<String, Object> params) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
//        long timestamp = System.currentTimeMillis();
//        bodyParams.put("symbol", params.get("symbol"));
//        bodyParams.put("side", params.get("side"));
//        bodyParams.put("type", params.get("type"));
//        bodyParams.put("recvWindow", "100000");

        // Add parameters based on order type
//        switch (params.get("type").toString().toUpperCase()) {
//            case "LIMIT" -> {
//                bodyParams.put("timeInForce", params.get("timeInForce"));
//                bodyParams.put("price", params.get("price"));
//                bodyParams.put("quantity", params.get("quantity"));
//            }
//            case "MARKET" -> bodyParams.put("quantity", params.get("quantity"));
//            case "STOP", "TAKE_PROFIT" -> {
//                bodyParams.put("price", params.get("price"));
//                bodyParams.put("stopPrice", params.get("stopPrice"));
//            }
//            case "STOP_MARKET", "TAKE_PROFIT_MARKET" -> bodyParams.put("stopPrice", params.get("stopPrice"));
//            case "TRAILING_STOP_MARKET" -> bodyParams.put("callbackRate", params.get("callbackRate"));
//        }

//        params.put("timestamp", String.valueOf(timestamp));
//        String response = httpClient.post(COINM_FUTURES_ORDER, params);
////        BinanceCoinmCreateOrder order = BinanceDeserializer.deserializeBinanceResponse(response, BinanceCoinmCreateOrder.class);
//        System.out.println(response);
//        return objectMapper.readTree(response);
//    }


//    public JsonNode deleteBinanceCoinmCancelOrder(Map<String, Object> params) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
//        long timestamp = System.currentTimeMillis();
//        params.put("timestamp", String.valueOf(timestamp));
//        String response = httpClient.delete(COINM_FUTURES_CANCEL_ORDER, params);
////        BinanceCoinmCancelOrder order = BinanceDeserializer.deserializeBinanceResponse(response, BinanceCoinmCancelOrder.class);
//        System.out.println(response);
//        return objectMapper.readTree(response);
//    }

    // todo: testing
//    public JsonNode deleteBinancecancelAllCoinm(Map<String, Object> params) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
//        long timestamp = System.currentTimeMillis();
//        params.put("timestamp", String.valueOf(timestamp));
//        String response =  httpClient.delete(COINM_FUTURES_CANCEL_ALL_ORDERS, params);
//        System.out.println(response);
//        return objectMapper.readTree(response);
//    }

    // todo: testing
//    public JsonNode putBinanceCoinmAmendOrder(Map<String, Object> params) throws IOException, URISyntaxException {
//        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
//        String response = httpClient.put(COINM_FUTURES_AMEND_ORDER, params);
////        BinanceCoinmAmendOrder order = BinanceDeserializer.deserializeBinanceResponse(response, BinanceCoinmAmendOrder.class);
//        System.out.println(response);
//        return objectMapper.readTree(response);
//    }

    public JsonNode getBinanceCoinmFuturesPosition(Map<String, Object> params) throws UnsupportedEncodingException, URISyntaxException, JsonProcessingException, NoSuchAlgorithmException, InvalidKeyException {
        long timestamp = System.currentTimeMillis();
        long recvWindow = 20000;
        params.put("timestamp", String.valueOf(timestamp));
        params.put("recvWindow", String.valueOf(recvWindow));
        String response = httpClient.getSignedWithQueryParams(COINM_FUTURES_POSITION_RISK, params);
//        List<BinanceCoinmPosition> positionList = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceCoinmPosition.class);
       // positionList.removeIf(position -> position.getPositionAmt().equals("0"));
//        System.out.println(positionList);
        System.out.println(response);
        return objectMapper.readTree(response);
    }

    //implement postBinanceCoinmChangeInitialLeverage
    public JsonNode postBinanceCoinmChangeInitialLeverage(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String res;
        try{
            res = binanceCmClient.account().changeInitialLeverage(params);
        }catch (BinanceClientException e){
            res = e.getMessage();
        }
        System.out.println(res);
        return objectMapper.readTree(res);
    }

    // get all orders
    public JsonNode getAllOrders(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try{
            response = binanceCmClient.account().allOrders(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }
    // single order
    public JsonNode createOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try{
            response = binanceCmClient.account().newOrder(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }
    // batch create order
    public JsonNode batchCreateOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceUmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        if (params.containsKey("batchOrders")) {
            Object batchOrders = params.get("batchOrders");
            if (batchOrders instanceof List) {
                String batchOrdersJson = objectMapper.writeValueAsString(batchOrders);
                params.put("batchOrders", batchOrdersJson);
            }
        }
        String response;
        try{
            response = binanceUmClient.account().placeMultipleOrders(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }
    // amend order
    public JsonNode amendOrder(Map<String, Object> queryParams) throws JsonProcessingException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try{
            response = binanceCmClient.account().modifyOrder(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }
    // amend batch order
    public JsonNode batchAmendOrder(Map<String, Object> queryParams) throws JsonProcessingException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
//        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        CustomBinanceCMFuturesClientImpl binanceCmClient = new CustomBinanceCMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        if(params.containsKey("batchOrders")){
            Object batchOrders=params.get("batchOrders");
            if(batchOrders instanceof List){
                String batchOrdersJson=objectMapper.writeValueAsString(batchOrders);
                params.put("batchOrders",batchOrdersJson);
            }
        }
        String response;
        try{
            response = binanceCmClient.account().modifyMultipleOrders(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }

    // cancel order
    public JsonNode cancelOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try{
            response = binanceCmClient.account().cancelOrder(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }
    // cancel batch order
    public JsonNode cancelBatchOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        if(params.containsKey("orderIdList")){
            Object orderIdList=params.get("orderIdList");
            if(orderIdList instanceof List){
                String batchOrdersJson=objectMapper.writeValueAsString(orderIdList);
                params.put("orderIdList",batchOrdersJson);
            }
        }
        String response;
        try{
            response = binanceCmClient.account().cancelMultipleOrders(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }
    // get query current open order
    public JsonNode queryCurrentOpenOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try{
            response = binanceCmClient.account().queryCurrentOpenOrder(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }

    // get all open orders
    public JsonNode queryAllOpenOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try{
            response = binanceCmClient.account().currentAllOpenOrders(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }

    public JsonNode cancelAllOrders(Map<String, Object> request) throws JsonProcessingException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(request);
        CMFuturesClientImpl binanceCmClient = new CMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try{
            response = binanceCmClient.account().cancelAllOpenOrders(params);
        }catch (BinanceClientException e){
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }
}