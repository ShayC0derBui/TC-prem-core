package Spring.service.external.binance.futures.usdm.rest;

import Spring.client.binance.BinanceHttpClient;
import Spring.client.binance.CustomBinanceUMFutuesClientImpl;
import Spring.model.pojo.binance.*;
import Spring.model.pojo.binance.futures.usdm.*;
import Spring.model.rest.adapters.binance.*;
import Spring.model.rest.interfaces.*;
import Spring.service.user.KeyService;
import Spring.utils.deserializer.binance.BinanceDeserializer;
import com.binance.connector.futures.client.exceptions.BinanceClientException;
import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static Spring.utils.constants.binance.BinanceConstants.*;

public class BinanceFuturesUsdmRest {
    private final BinanceHttpClient binanceHttpClient;
    @Autowired
    private final ObjectMapper objectMapper;
    private final KeyService keyservice;

    @Autowired
    public BinanceFuturesUsdmRest(BinanceHttpClient binanceHttpClient, ObjectMapper objectMapper, KeyService keyService) {
        this.objectMapper = objectMapper;
        this.binanceHttpClient = binanceHttpClient;
        this.keyservice = keyService;
    }

    public List<Contracts> getBinanceUsdmFuturesContracts() throws IOException {
        String endpoint = USDM_FUTURES_TICKER_PRICE;
        String priceResponse = binanceHttpClient.get(USDM_FUTURES_TICKER_PRICE);
        String binance24hrResponse = binanceHttpClient.get(USDM_FUTURES_24HR_TICKER);
        List<BinanceSymbol> priceList = BinanceDeserializer.deserializeBinanceListResponse(priceResponse, BinanceSymbol.class);
        List<BinanceFutures24HrTickerPriceChange> binanceUsdm24hrDataList = BinanceDeserializer.deserializeBinanceListResponse(binance24hrResponse, BinanceFutures24HrTickerPriceChange.class);

        // Create a map from symbol to BinanceSymbol for efficient lookup
        Map<String, BinanceSymbol> symbolToBinanceSymbol = priceList.stream()
                .collect(Collectors.toMap(BinanceSymbol::getSymbol, Function.identity()));

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

    public List<BinanceUsdmFuturesPremiumIndex> getBinanceUsdmFuturesPremiumIndex() throws IOException {
        String response = binanceHttpClient.get(USDM_FUTURES_PREMIUM_INDEX);
        List<BinanceUsdmFuturesPremiumIndex> binanceUsdmFuturesPremiumIndexList = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceUsdmFuturesPremiumIndex.class);
        System.out.println(binanceUsdmFuturesPremiumIndexList);

        return binanceUsdmFuturesPremiumIndexList;
    }

    public OrderBook getBinanceUsdmFuturesOrderBook(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String response = binanceHttpClient.getWithQueryParams(USDM_ORDER_BOOK_ENDPOINT, queryParams);
        BinanceOrderBook binanceOrderBook = BinanceDeserializer.deserializeBinanceResponse(response, BinanceOrderBook.class);
        BinanceOrderBook adaptedData = new Gson().fromJson(new Gson().toJson(binanceOrderBook), new TypeToken<BinanceOrderBook>() {
        }.getType());
        System.out.println(binanceOrderBook);
        return new BinanceOrderBookAdapter(adaptedData, queryParams.get("symbol").toString());
    }

    public List<RecentTrades> getBinanceUsdmFuturesTrades(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = USDM_TRADES_ENDPOINT;
        String response = binanceHttpClient.getWithQueryParams(endpoint, queryParams);
        List<BinanceRecentTrades> binanceRecentTrades = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceRecentTrades.class);
        List<BinanceRecentTrades> adaptedData = new Gson().fromJson(new Gson().toJson(binanceRecentTrades), new TypeToken<List<BinanceRecentTrades>>() {
        }.getType());
        System.out.println(binanceRecentTrades.toString());
        return adaptedData.stream().map(trade -> new BinanceRecentTradesAdapter(trade, endpoint, queryParams.get("symbol").toString())).collect(Collectors.toList());
    }

    public List<CandleStick> getBinanceUsdmFuturesCandlesticks(Map<String, Object> queryParams) throws IOException, URISyntaxException {

        String endpoint = USDM_FUTURES_KLINE;
        String response = binanceHttpClient.getWithQueryParams(endpoint, queryParams);
        List<BinanceCandlesticks> candlesticks = BinanceDeserializer.deserializeCandlesticks(response);
        System.out.println(candlesticks);

        return candlesticks.stream().map(trades -> new BinanceCandleStickAdapter(trades, endpoint, queryParams.get("symbol").toString())).collect(Collectors.toList());
    }

    public List<FundingRate> getBinanceUsdmFuturesFundingRateHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = binanceHttpClient.getWithQueryParams(USDM_FUTURES_FUNDING_RATE, queryParams);
        List<BinanceFuturesFundingRateHistory> binanceFuturesFundingRateHistoryList = BinanceDeserializer.deserializeBinanceListResponse(json, BinanceFuturesFundingRateHistory.class);
        return binanceFuturesFundingRateHistoryList.stream().map(BinanceFundingRateAdapter::new).collect(Collectors.toList());
    }

    public List<BinanceFutures24HrTickerPriceChange> getBinanceUsdmFutures24HrTickerPriceChange(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = binanceHttpClient.getWithQueryParams(USDM_FUTURES_24HR_TICKER, queryParams);
        List<BinanceFutures24HrTickerPriceChange> binanceUsdmFutures24HrTickerPriceChangeList = BinanceDeserializer.deserializeBinanceListResponse(json, BinanceFutures24HrTickerPriceChange.class);
        System.out.println(binanceUsdmFutures24HrTickerPriceChangeList);

        return binanceUsdmFutures24HrTickerPriceChangeList;
    }

    public OpenInterest getBinanceUsdmFuturesOpenInterest(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = binanceHttpClient.getWithQueryParams(USDM_FUTURES_OPEN_INTEREST, queryParams);
        BinanceFuturesOpenInterest binanceFuturesOpenInterest = BinanceDeserializer.deserializeBinanceResponse(json, BinanceFuturesOpenInterest.class);
        System.out.println(binanceFuturesOpenInterest);
        return new BinanceOpenInterestAdapter(binanceFuturesOpenInterest);
    }

    public List<BinanceUsdmFuturesTopLongShortRatio> getBinanceUsdmFuturesTopLongShortAccountRatio(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = binanceHttpClient.getWithQueryParams(USDM_FUTURES_TOP_LONG_SHORT_ACCOUNT_RATIO, queryParams);
        List<BinanceUsdmFuturesTopLongShortRatio> binanceUsdmFuturesTopLongShortRatioList = BinanceDeserializer.deserializeBinanceListResponse(json, BinanceUsdmFuturesTopLongShortRatio.class);
        System.out.println("LongShortAccountRatio" + binanceUsdmFuturesTopLongShortRatioList);

        return binanceUsdmFuturesTopLongShortRatioList;
    }

    public List<BinanceUsdmFuturesTopLongShortRatio> getBinanceUsdmFuturesTopLongShortPositionRatio(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = binanceHttpClient.getWithQueryParams(USDM_FUTURES_TOP_LONG_SHORT_POSITION_RATIO, queryParams);
        List<BinanceUsdmFuturesTopLongShortRatio> binanceUsdmFuturesTopLongShortRatioList = BinanceDeserializer.deserializeBinanceListResponse(json, BinanceUsdmFuturesTopLongShortRatio.class);
        System.out.println("LongShortPositionRatio" + binanceUsdmFuturesTopLongShortRatioList);

        return binanceUsdmFuturesTopLongShortRatioList;
    }

    public JsonNode postBinanceCreateOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String,Object> params=new LinkedHashMap<String,Object>(queryParams);
        params.put("recvWindow", "100000");
        UMFuturesClientImpl binanceUmClient=new UMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"),keyservice.getKeys().get("BINANCE_API_SECRET"));
        String res;
        try{
            res=binanceUmClient.account().newOrder(params);
        }catch(Exception e){
            res=e.getMessage();
        }
        return objectMapper.readTree(res);
    }

    public JsonNode deleteBinanceCancelOrder(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String,Object> params=new LinkedHashMap<String,Object>(queryParams);
        UMFuturesClientImpl binanceUmClient=new UMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"),keyservice.getKeys().get("BINANCE_API_SECRET"));
        String res;
        try{
            res=binanceUmClient.account().cancelOrder(params);
        }catch(Exception e){
            res=e.getMessage();
        }
        return objectMapper.readTree(res);
    }

    public JsonNode cancelAll(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String,Object> params=new LinkedHashMap<String,Object>(queryParams);
        UMFuturesClientImpl binanceUmClient=new UMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"),keyservice.getKeys().get("BINANCE_API_SECRET"));
        String res;
        try{
            res=binanceUmClient.account().cancelAllOpenOrders(params);
        }catch(Exception e){
            res=e.getMessage();
        }
        return objectMapper.readTree(res);
    }


    public JsonNode putBinanceAmendOrder(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        long timestamp = System.currentTimeMillis();
        queryParams.put("timestamp", String.valueOf(timestamp));
        String response = binanceHttpClient.post(USDM_FUTURES_AMEND_ORDER, queryParams);
        System.out.println(response);
        return objectMapper.readTree(response);
    }

    //getBinanceUsdmFuturesHistoricalTrades
    public List<RecentTrades> getBinanceUsdmFuturesHistoricalTrades(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = USDM_HISTORICAL_TRADES_ENDPOINT;
        String response = binanceHttpClient.getWithQueryParams(endpoint, queryParams);
        List<BinanceRecentTrades> binanceRecentTrades = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceRecentTrades.class);
        List<BinanceRecentTrades> adaptedData = new Gson().fromJson(new Gson().toJson(binanceRecentTrades), new TypeToken<List<BinanceRecentTrades>>() {
        }.getType());
        System.out.println(binanceRecentTrades.toString());
        return adaptedData.stream().map(trade -> new BinanceRecentTradesAdapter(trade, endpoint, queryParams.get("symbol").toString())).collect(Collectors.toList());
    }

    public List<BinanceUsdmPosition> getBinanceUsdmFuturesPositionRisk(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        long timestamp = System.currentTimeMillis();
        queryParams.put("timestamp", String.valueOf(timestamp));
        String response = binanceHttpClient.getSignedWithQueryParams(USDM_FUTURES_POSITION_RISK, queryParams);
        List<BinanceUsdmPosition> positionList = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceUsdmPosition.class);
        positionList.removeIf(position -> position.getPositionAmt().equals("0"));
        System.out.println(positionList);
        return positionList;
    }

    //changeInitialLeverage
    public JsonNode changeInitialLeverage(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String,Object> params=new LinkedHashMap<String,Object>(queryParams);
        UMFuturesClientImpl binanceUmClient=new UMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"),keyservice.getKeys().get("BINANCE_API_SECRET"));
        String res;
        try{
            res=binanceUmClient.account().changeInitialLeverage(params);
        }catch(Exception e){
            res=e.getMessage();
        }
        System.out.println(res);
        return objectMapper.readTree(res);
    }

    //getAllOpenOrders
    public JsonNode getAllOpenOrders(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String,Object> params=new LinkedHashMap<String,Object>(queryParams);
        UMFuturesClientImpl binanceUmClient=new UMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"),keyservice.getKeys().get("BINANCE_API_SECRET"));
        String res;
        try{
            res=binanceUmClient.account().currentAllOpenOrders(params);
        }catch(Exception e){
            res=e.getMessage();
        }
        return objectMapper.readTree(res);
    }

    //getAccountBalance
    public List<BinanceUsdmAccountBalance> getAccountBalance(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        long timestamp = System.currentTimeMillis();
        long recvWindow = 20000;
        queryParams.put("recvWindow", String.valueOf(recvWindow));
        queryParams.put("timestamp", String.valueOf(timestamp));
        String response = binanceHttpClient.getSignedWithQueryParams(USDM_FUTURES_ACCOUNT_BALANCE, queryParams);
        System.out.println("response: " + response);
        List<BinanceUsdmAccountBalance> accountBalance = BinanceDeserializer.deserializeBinanceListResponse(response, BinanceUsdmAccountBalance.class);
        System.out.println(accountBalance);
        return accountBalance;
    }

    public JsonNode postBatchOrders(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>(queryParams);
//        System.out.println("queryParams here:"+queryParams);
        // Convert the batchOrders to JSON string
        if (params.containsKey("batchOrders")) {
            Object batchOrders = params.get("batchOrders");
            if (batchOrders instanceof List) {
                String batchOrdersJson = objectMapper.writeValueAsString(batchOrders);
                params.put("batchOrders", batchOrdersJson);
            }
        }

        UMFuturesClientImpl binanceUmClient = new UMFuturesClientImpl(
                keyservice.getKeys().get("BINANCE_API_KEY"),
                keyservice.getKeys().get("BINANCE_API_SECRET")
        );
        String res;
        try {
            res = binanceUmClient.account().placeMultipleOrders(params);
        } catch (Exception e) {
            res = e.getMessage();
        }
        System.out.println(res);
        return objectMapper.readTree(res);
    }


    public JsonNode cancelBatchOrders(Map<String,Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        LinkedHashMap<String,Object> params=new LinkedHashMap<String,Object>(queryParams);
        if(params.containsKey("orderIdList")){
            Object orderIdList=params.get("orderIdList");
            if(orderIdList instanceof List){
                String batchOrdersJson=objectMapper.writeValueAsString(orderIdList);
                params.put("orderIdList",batchOrdersJson);
            }
        }
        UMFuturesClientImpl binanceUmClient=new UMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"),keyservice.getKeys().get("BINANCE_API_SECRET"));
        String res;
        try{
            res=binanceUmClient.account().cancelMultipleOrders(params);
        }catch(Exception e){
            res=e.getMessage();
        }
        System.out.println(res);
        return objectMapper.readTree(res);
    }

    // get all orders
    public JsonNode getAllOrders(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
//        long timestamp = System.currentTimeMillis();
//        long recvWindow = 20000;
//        queryParams.put("recvWindow", String.valueOf(recvWindow));
//        queryParams.put("timestamp", String.valueOf(timestamp));
//        String response = binanceHttpClient.getSignedWithQueryParams(USDM_FUTURES_ALL_ORDERS, queryParams);
//        System.out.println(response);
        LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>(queryParams);
        UMFuturesClientImpl binanceUmClient = new UMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try {
            response = binanceUmClient.account().allOrders(params);
        } catch (BinanceClientException e) {
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }

    public JsonNode amendOrder(Map<String, Object> request) throws JsonProcessingException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>(request);

//        UMFuturesClientImpl binanceUmClient = new UMFuturesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        CustomBinanceUMFutuesClientImpl binanceUmClient = new CustomBinanceUMFutuesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try {
            response = binanceUmClient.account().modifyOrder(params);
        } catch (BinanceClientException e) {
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }

    public JsonNode batchAmendOrders(Map<String, Object> request) throws JsonProcessingException {
        LinkedHashMap<String, Object> params = new LinkedHashMap<>(request);
        if(params.containsKey("batchOrders")){
            Object batchOrders=params.get("batchOrders");
            if(batchOrders instanceof List){
                String batchOrdersJson=objectMapper.writeValueAsString(batchOrders);
                params.put("batchOrders",batchOrdersJson);
            }
        }
        CustomBinanceUMFutuesClientImpl binanceUmClient = new CustomBinanceUMFutuesClientImpl(keyservice.getKeys().get("BINANCE_API_KEY"), keyservice.getKeys().get("BINANCE_API_SECRET"));
        String response;
        try {
            response = binanceUmClient.account().modifyMultipleOrders(params);
        } catch (BinanceClientException e) {
            response = e.getMessage();
        }
        System.out.println(response);
        return objectMapper.readTree(response);
    }
}
