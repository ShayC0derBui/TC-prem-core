package Spring.service.external.bybit.futures.perpetual.rest;

import Spring.client.bybit.BybitHttpClient;
import Spring.model.enums.bybit.futures.perpetual.OrderType;
import Spring.model.pojo.bybit.*;
import Spring.model.pojo.bybit.futures.perpetual.*;
import Spring.model.rest.adapters.bybit.*;
import Spring.model.rest.interfaces.*;
import Spring.utils.constants.bybit.BybitConstants;
import Spring.utils.deserializer.bybit.BybitDeserializer;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BybitInverseRest {
    private final BybitHttpClient httpClient;

    public BybitInverseRest(BybitHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static boolean isMultipleOf(double d1, double d2) {
        return BigDecimal.valueOf(d1).remainder(BigDecimal.valueOf(d2))
                .compareTo(BigDecimal.ZERO) == 0;
    }

    public List<Contracts> getInverseBybitPerpetualFuturesSymbols(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        queryParams.put("category", "inverse");
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

    public List<FundingRate> getInverseBybitFundingHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.MARKET_FUNDING_HISTORY;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitGenericResponse<BybitPerpetualFuturesFundingRateHistory> response = BybitDeserializer.deserializeResponse(json, BybitPerpetualFuturesFundingRateHistory.class);
        BybitGenericResult<BybitPerpetualFuturesFundingRateHistory> result = response.getResult();
        return result.getList().stream().map(BybitFundingRateAdapter::new).collect(Collectors.toList());
    }
    public JsonNode getInverseBybitOpenOrders(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.OPEN_ORDERS;
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
//        List<BybitOpenOrders> response = BybitDeserializer.deserializeResponse(json, BybitOpenOrders.class).getResult().getList();
//        System.out.println(response);
//        return response;
        return new ObjectMapper().readTree(json);
    }
    public JsonNode getInverseBybitOrderHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.ORDER_HISTORY;
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
//        List<BybitOrderHistory> response = BybitDeserializer.deserializeResponse(json, BybitOrderHistory.class).getResult().getList();
//        System.out.println(response);
//        return response;
        return new ObjectMapper().readTree(json);
    }
    public JsonNode getInverseBybitTradeHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.TRADE_HISTORY;
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
//        List<BybitTradeHistory> response = BybitDeserializer.deserializeResponse(json, BybitTradeHistory.class).getResult().getList();
//        System.out.println(response);
//        return response;
        return new ObjectMapper().readTree(json);
    }
    public List<RecentTrades> getInverseBybitPublicTradingHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.MARKET_RECENT_TRADE;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitGenericResponse<BybitRecentTrades> response = BybitDeserializer.deserializeResponse(json, BybitRecentTrades.class);
        BybitGenericResult<BybitRecentTrades> result = response.getResult();
        return result.getList().stream().map(BybitRecentTradesAdapter::new).collect(Collectors.toList());
    }

    public List<CandleStick> getInverseBybitFuturesKline(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.KLINE;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        List<BybitKline> response = BybitDeserializer.deserializeBybitKline(json).getResult().getList();
        return response.stream().map(kline -> new BybitCandleStickAdapter(kline, queryParams.get("interval").toString())).collect(Collectors.toList());
    }

    public List<OpenInterest> getInverseBybitOpenInterest(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.MARKET_OPEN_INTEREST;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitGenericResponse<BybitPerpetualFuturesOpenInterest> response = BybitDeserializer.deserializeResponse(json, BybitPerpetualFuturesOpenInterest.class);
        BybitGenericResult<BybitPerpetualFuturesOpenInterest> result = response.getResult();
        System.out.println("Category of Perpetual Futures = " + result.getCategory());
        System.out.println(result.getList());
        return result.getList().stream().map(openInterest -> new BybitOpenInterestAdapter(openInterest, result.getSymbol())).collect(Collectors.toList());
    }

    public OrderBook getInverseBybitOrderBook(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.ORDER_BOOK;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitOrderBookGenericResponse response = BybitDeserializer.deserializeBybitOrderBook(json);
        BybitOrderBookGenericResult result = response.getResult();
        System.out.println(result);
        return new BybitOrderBookAdapter(result);
    }

    public BybitGenericResponse<BybitPerpetualFuturesInstrumentInfo> getInverseBybitPerpetualFuturesInstrumentsInfo(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.MARKET_INSTRUMENTS_INFO;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        return BybitDeserializer.deserializeResponse(json, BybitPerpetualFuturesInstrumentInfo.class);
    }

    public BybitGenericResponse<BybitPerpetualFuturesPositionInfo> getInverseBybitPerpetualFuturesPositionInfo(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.POSITION_LIST;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        return BybitDeserializer.deserializeResponse(json, BybitPerpetualFuturesPositionInfo.class);
    }

    public BybitCreateOrder postInverseBybitCreateOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.ORDER_CREATE;
        String qty = (String) queryParams.get("qty");
        String price = (String) queryParams.get("price");
        OrderType orderType = OrderType.valueOf((String) queryParams.get("orderType"));

        String minOrderQty = getInverseBybitPerpetualFuturesInstrumentsInfo(queryParams).getResult().getList().get(0).getLotSizeFilter().getMinOrderQty();
        if (!isMultipleOf(Double.parseDouble(qty), Double.parseDouble(minOrderQty))) {
            throw new IOException("qty is not multiple of minOrderQty from InstrumentsInfo"); // In USD for inverse
        }

        if (orderType == OrderType.Limit) {
            BybitGenericResponse<BybitPerpetualFuturesPositionInfo> response = getInverseBybitPerpetualFuturesPositionInfo(queryParams);
            if (response != null) {
                String liqPrice = response.getResult().getList().get(0).getLiqPrice();
                if (Double.parseDouble(price) < Double.parseDouble(liqPrice)) {
                    throw new IOException("price is not greater than liqPrice from PositionInfo");
                }
            }
        }
        String rawResponse = httpClient.post(BybitConstants.ORDER_CREATE, queryParams);
        String json = extractResult(rawResponse);
        BybitCreateOrder result = BybitDeserializer.deserializeBybitCreateOrder(json);
        System.out.println(result);
        return result;
    }

    public ByBitCancelOrder postInverseBybitCancelOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.ORDER_CANCEL;
        String rawResponse = httpClient.post(BybitConstants.ORDER_CANCEL, queryParams);
        String json = extractResult(rawResponse);
        ByBitCancelOrder result = BybitDeserializer.deserializeBybitCancelOrder(json);
        System.out.println(result);
        return result;
    }

    public BybitAmendOrder postInverseBybitAmendOrder(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.ORDER_AMEND;
        String rawResponse = httpClient.post(BybitConstants.ORDER_AMEND, queryParams);
        String json = extractResult(rawResponse);
        BybitAmendOrder result = BybitDeserializer.deserializeBybitAmendOrder(json);
        System.out.println(result);
        return result;
    }

    public BybitGenericResponse<ByBitCancelOrder> postInverseBybitCancelAll(Map<String, Object> queryParams) throws NoSuchAlgorithmException, InvalidKeyException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.ORDER_CANCEL_ALL;
        String rawResponse = httpClient.post(BybitConstants.ORDER_CANCEL_ALL, queryParams);
        BybitGenericResponse<ByBitCancelOrder> response = BybitDeserializer.deserializeResponse(rawResponse, ByBitCancelOrder.class);

        BybitGenericResult<ByBitCancelOrder> result = response.getResult();
        for (ByBitCancelOrder orders : result.getList()) {
            System.out.println(orders);
        }

        return response;
    }

    public List<BybitPosition> getInverseBybitPosition(Map<String, Object> queryParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException, URISyntaxException {
        queryParams.put("category", "inverse");
        String endpoint = BybitConstants.POSITION_INFO;
        String rawResponse = httpClient.getSignedWithQueryParams(endpoint, queryParams);
        BybitGenericResponse<BybitPosition> response = BybitDeserializer.deserializeResponse(rawResponse, BybitPosition.class);
        BybitGenericResult<BybitPosition> result = response.getResult();
        return result.getList();
    }

    public String extractResult(String rawResponse) {
        JsonObject responseJson = JsonParser.parseString(rawResponse).getAsJsonObject();
        JsonObject resultJson = responseJson.getAsJsonObject("result");
        return resultJson.toString();
    }
}
