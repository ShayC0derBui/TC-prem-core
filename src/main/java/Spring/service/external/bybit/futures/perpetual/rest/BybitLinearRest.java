package Spring.service.external.bybit.futures.perpetual.rest;

import Spring.client.bybit.BybitHttpClient;
import Spring.client.util.Retry;
import Spring.model.pojo.bybit.BybitKline;
import Spring.model.pojo.bybit.BybitRecentTrades;
import Spring.model.pojo.bybit.BybitSymbol;
import Spring.model.pojo.bybit.futures.perpetual.*;
import Spring.model.rest.adapters.bybit.*;
import Spring.model.rest.interfaces.*;
import Spring.utils.constants.bybit.BybitConstants;
import Spring.utils.deserializer.bybit.BybitDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BybitLinearRest {
    private final BybitHttpClient httpClient;
    @Autowired
    private final ObjectMapper objectMapper;
    private final Retry retry;

    @Autowired
    public BybitLinearRest(BybitHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.retry = new Retry();
    }

    public static boolean isMultipleOf(double d1, double d2) {
        return BigDecimal.valueOf(d1).remainder(BigDecimal.valueOf(d2))
                .compareTo(BigDecimal.ZERO) == 0;
    }

    public List<Contracts> getLinearBybitPerpetualFuturesSymbols(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.MARKET_TICKERS;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        BybitGenericResponse<BybitSymbol> response = BybitDeserializer.deserializeResponse(json, BybitSymbol.class);

        BybitGenericResult<BybitSymbol> result = response.getResult();
        System.out.println("Category of Perpetual Futures = " + result.getCategory());

        for (BybitSymbol symbol : result.getList()) {
            System.out.println(symbol);
        }
        return result.getList().stream().map(BybitContractAdapter::new).collect(Collectors.toList());
    }

    public List<FundingRate> getLinearBybitFundingHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.MARKET_FUNDING_HISTORY;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitGenericResponse<BybitPerpetualFuturesFundingRateHistory> response = BybitDeserializer.deserializeResponse(json, BybitPerpetualFuturesFundingRateHistory.class);
        BybitGenericResult<BybitPerpetualFuturesFundingRateHistory> result = response.getResult();
        return result.getList().stream().map(BybitFundingRateAdapter::new).collect(Collectors.toList());
    }

    public List<RecentTrades> getLinearBybitPublicTradingHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.MARKET_RECENT_TRADE;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitGenericResponse<BybitRecentTrades> response = BybitDeserializer.deserializeResponse(json, BybitRecentTrades.class);
        BybitGenericResult<BybitRecentTrades> result = response.getResult();
        return result.getList().stream().map(BybitRecentTradesAdapter::new).collect(Collectors.toList());
    }

    public List<CandleStick> getLinearBybitFuturesKline(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.KLINE;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        List<BybitKline> response = BybitDeserializer.deserializeBybitKline(json).getResult().getList();
        Map<String, Object> finalQueryParams = queryParams;
        return response.stream().map(kline -> new BybitCandleStickAdapter(kline, finalQueryParams.get("interval").toString())).collect(Collectors.toList());
    }

    public List<OpenInterest> getLinearBybitOpenInterest(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.MARKET_OPEN_INTEREST;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitGenericResponse<BybitPerpetualFuturesOpenInterest> response = BybitDeserializer.deserializeResponse(json, BybitPerpetualFuturesOpenInterest.class);
        BybitGenericResult<BybitPerpetualFuturesOpenInterest> result = response.getResult();
        System.out.println("Category of Perpetual Futures = " + result.getCategory());
        System.out.println(result.getList());
        return result.getList().stream().map(openInterest -> new BybitOpenInterestAdapter(openInterest, result.getSymbol())).collect(Collectors.toList());
    }

    public OrderBook getLinearBybitOrderBook(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.ORDER_BOOK;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitOrderBookGenericResponse response = BybitDeserializer.deserializeBybitOrderBook(json);
        BybitOrderBookGenericResult result = response.getResult();
        System.out.println(result);
        return new BybitOrderBookAdapter(result);
    }

    public JsonNode getLinearBybitOpenOrders(Map<String, Object> queryParams) throws Exception {
        String endpoint = BybitConstants.OPEN_ORDERS;
        String json = retry.executeWithRetry(() ->
                {
                    try {
                        return httpClient.getSignedWithQueryParams(endpoint, queryParams);
                    } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        return objectMapper.readTree(json);
    }

    public JsonNode getLinearBybitTradeHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.TRADE_HISTORY;
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
//        List<BybitTradeHistory> response = BybitDeserializer.deserializeResponse(json, BybitTradeHistory.class).getResult().getList();
//        System.out.println(response);
//        return response;
        return objectMapper.readTree(json);
    }

    public JsonNode getLinearBybitOrderHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.ORDER_HISTORY;
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
        //List<BybitOrderHistory> response = BybitDeserializer.deserializeResponse(json, BybitOrderHistory.class).getResult().getList();
//        System.out.println(response);
//        return response;
        return objectMapper.readTree(json);
    }

    public JsonNode getLinearBybitPerpetualFuturesInstrumentsInfo(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.MARKET_INSTRUMENTS_INFO;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        return objectMapper.readTree(json);
    }

    public BybitGenericResponse<BybitPerpetualFuturesPositionInfo> getLinearBybitPerpetualFuturesPositionInfo(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.POSITION_LIST;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        return BybitDeserializer.deserializeResponse(json, BybitPerpetualFuturesPositionInfo.class);
    }

    public JsonNode postLinearBybitCreateOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
//        queryParams.put("category", "linear");
//        String endpoint = BybitConstants.ORDER_CREATE;
//        String qty = (String) queryParams.get("qty");
//        String price = (String) queryParams.get("price");
//        OrderType orderType = OrderType.valueOf((String) queryParams.get("orderType"));
//
////        String minOrderQty = getLinearBybitPerpetualFuturesInstrumentsInfo(queryParams).getResult().getList().get(0).getLotSizeFilter().getMinOrderQty();
////        String minOrderQty = getLinearBybitPerpetualFuturesInstrumentsInfo(queryParams).get("result").get(0).get("lotSizeFilter").get("minOrderQty").asText();
//        String minOrderQty="";
//        JsonNode json = getLinearBybitPerpetualFuturesInstrumentsInfo(queryParams);
//        System.out.println(json);
//
//        JsonNode resultNode = json.get("result");
//        if (resultNode != null && resultNode.has("list") && resultNode.get("list").isArray() && resultNode.get("list").size() > 0) {
//            JsonNode firstItem = resultNode.get("list").get(0);
//            if (firstItem != null && firstItem.has("lotSizeFilter")) {
//                JsonNode lotSizeFilterNode = firstItem.get("lotSizeFilter");
//                if (lotSizeFilterNode != null && lotSizeFilterNode.has("minOrderQty")) {
//                    minOrderQty = lotSizeFilterNode.get("minOrderQty").asText();
//                    System.out.println("minOrderQty = " + minOrderQty);
//                } else {
//                    System.out.println("minOrderQty node is missing.");
//                }
//            } else {
//                System.out.println("First item or lotSizeFilter node is missing.");
//            }
//        } else {
//            System.out.println("Result node is null or empty array.");
//        }
//
//        System.out.println("minOrderQty = " + minOrderQty);
//        if (!isMultipleOf(Double.parseDouble(qty), Double.parseDouble(minOrderQty))) {
//            throw new IOException("qty is not multiple of minOrderQty from InstrumentsInfo"); // In USD for inverse
//        }
//
//        if (orderType == OrderType.Limit) {
//            BybitGenericResponse<BybitPerpetualFuturesPositionInfo> response = getLinearBybitPerpetualFuturesPositionInfo(queryParams);
//            if (response != null) {
//                String liqPrice = response.getResult().getList().get(0).getLiqPrice();
//                if (Double.parseDouble(price) < Double.parseDouble(liqPrice)) {
//                    throw new IOException("price is not greater than liqPrice from PositionInfo");
//                }
//            }
//        }
        String rawResponse = httpClient.post(BybitConstants.ORDER_CREATE, queryParams);
        System.out.println(rawResponse);
        return objectMapper.readTree(rawResponse);
//        String json = extractResult(rawResponse);
//        System.out.println(json);
//        return BybitDeserializer.deserializeBybitCreateOrder(json);
    }

    public JsonNode postLinearBybitCancelOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.ORDER_CANCEL;
        String rawResponse = httpClient.post(BybitConstants.ORDER_CANCEL, queryParams);
        System.out.println(rawResponse);
        return objectMapper.readTree(rawResponse);
    }

    public JsonNode postLinearBybitAmendOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.ORDER_AMEND;
        String rawResponse = httpClient.post(BybitConstants.ORDER_AMEND, queryParams);
        System.out.println(rawResponse);
        return objectMapper.readTree(rawResponse);
    }

    public JsonNode postLinearBybitCancelAll(Map<String, Object> queryParams) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.ORDER_CANCEL_ALL;
        String rawResponse = httpClient.post(BybitConstants.ORDER_CANCEL_ALL, queryParams);
        BybitGenericResponse<ByBitCancelOrder> response = BybitDeserializer.deserializeResponse(rawResponse, ByBitCancelOrder.class);

        BybitGenericResult<ByBitCancelOrder> result = response.getResult();
        if (result.getList() != null) {
            for (ByBitCancelOrder orders : result.getList()) {
                System.out.println(orders);
            }
        }

        return objectMapper.readTree(rawResponse);
    }

    public JsonNode getLinearBybitPosition(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
//        queryParams.put("category", "linear");
        String endpoint = BybitConstants.POSITION_INFO;
        String rawResponse = httpClient.getSignedWithQueryParams(endpoint, queryParams);
        return objectMapper.readTree(rawResponse);
    }

    public String extractResult(String rawResponse) {
        JsonObject responseJson = JsonParser.parseString(rawResponse).getAsJsonObject();
        JsonObject resultJson = responseJson.getAsJsonObject("result");
        return resultJson.toString();
    }

    public JsonNode getBybitWalletBalance(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
        String endpoint = BybitConstants.WALLET_BALANCE;
        String rawResponse = httpClient.getSignedWithQueryParams(endpoint, queryParams);
        return objectMapper.readTree(rawResponse);
    }

    public JsonNode getSubApiKeys(Map<String, Object> request) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.SUB_API_KEYS;
        String rawResponse = httpClient.getSignedWithQueryParams(endpoint, request);
        return objectMapper.readTree(rawResponse);
    }

    public JsonNode getApiKeyInfo(Map<String, Object> request) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.API_KEY_INFO;
        String rawResponse = httpClient.getSignedWithQueryParams(endpoint, request);
        return objectMapper.readTree(rawResponse);
    }

    public JsonNode getRiskLimit(Map<String, Object> request) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.RISK_LIMIT;
        String rawResponse = httpClient.getSignedWithQueryParams(endpoint, request);
        return objectMapper.readTree(rawResponse);
    }

    public JsonNode setLeverage(Map<String, Object> request) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.LEVERAGE;
        String rawResponse = httpClient.post(endpoint, request);
        return objectMapper.readTree(rawResponse);
    }

    public JsonNode setRiskLimit(Map<String, Object> request) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        String endpoint = BybitConstants.SET_RISK_LIMIT;
        String rawResponse = httpClient.post(endpoint, request);
        return objectMapper.readTree(rawResponse);
    }

    public JsonNode createBatchOrder(Map<String, Object> request) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        String endpoint = BybitConstants.BATCH_CREATE_ORDER;
        String rawResponse = httpClient.post(endpoint, request);
        return objectMapper.readTree(rawResponse);
    }


    public JsonNode cancelBatchOrder(Map<String, Object> request) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        String endpoint = BybitConstants.BATCH_CANCEL_ORDER;
        String rawResponse = httpClient.post(endpoint, request);
        return objectMapper.readTree(rawResponse);
    }


    public JsonNode ammendBatchOrder(Map<String, Object> request) throws NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException {
        String endpoint = BybitConstants.BATCH_AMMEND_ORDER;
        String rawResponse = httpClient.post(endpoint, request);
        return objectMapper.readTree(rawResponse);
    }
}
