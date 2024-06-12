package Spring.service.external.bybit.spot;

import Spring.client.bybit.BybitHttpClient;
import Spring.model.pojo.bybit.*;
import Spring.model.pojo.bybit.futures.perpetual.*;
import Spring.model.rest.adapters.bybit.BybitCandleStickAdapter;
import Spring.model.rest.adapters.bybit.BybitContractAdapter;
import Spring.model.rest.adapters.bybit.BybitOrderBookAdapter;
import Spring.model.rest.interfaces.CandleStick;
import Spring.model.rest.interfaces.Contracts;
import Spring.model.rest.interfaces.OrderBook;
import Spring.utils.constants.bybit.BybitConstants;
import Spring.utils.deserializer.bybit.BybitDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BybitSpotRest {
    private final BybitHttpClient httpClient;

    public BybitSpotRest(BybitHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<Contracts> getBybitSpotTickers(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = BybitConstants.MARKET_TICKERS;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);

        BybitGenericResponse<BybitSymbol> response = BybitDeserializer.deserializeResponse(json, BybitSymbol.class);

        BybitGenericResult<BybitSymbol> result = response.getResult();
        System.out.println("Category of Perpetual Futures = " + result.getCategory());

        for (BybitSymbol symbol : result.getList()) {
            System.out.println(symbol);
        }

        return result.getList().stream().map(BybitContractAdapter::new).collect(Collectors.toList());
    }

    public List<BybitRecentTrades> getBybitSpotRecentTrades(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = BybitConstants.MARKET_RECENT_TRADE;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        List<BybitRecentTrades> response = BybitDeserializer.deserializeResponse(json, BybitRecentTrades.class).getResult().getList();
        System.out.println(response);
        return response;
    }

    public OrderBook getBybitSpotOrderBook(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = BybitConstants.ORDER_BOOK;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        BybitOrderBookGenericResponse response = BybitDeserializer.deserializeBybitOrderBook(json);
        BybitOrderBookGenericResult result = response.getResult();
        System.out.println(result);
        return new BybitOrderBookAdapter(result);
    }

    public List<CandleStick> getBybitSpotKline(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = BybitConstants.KLINE;
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        List<BybitKline> response = BybitDeserializer.deserializeBybitKline(json).getResult().getList();
        System.out.println(response);
        return response.stream().map(pojo -> new BybitCandleStickAdapter(pojo, queryParams.get("interval").toString())).collect(Collectors.toList());
    }

    public BybitCreateOrder postBybitSpotCreateOrder(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.ORDER_CREATE;
        String json = httpClient.post( endpoint, queryParams);
        BybitCreateOrder response = BybitDeserializer.deserializeBybitCreateOrder(json);
        System.out.println(response);
        return response;
    }

    public ByBitCancelOrder postBybitSpotCancelOrder(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.ORDER_CANCEL;
        String json = httpClient.post( endpoint, queryParams);
        ByBitCancelOrder response = BybitDeserializer.deserializeBybitCancelOrder(json);
        System.out.println(response);
        return response;
    }

    public List<ByBitCancelOrder> postBybitSpotCancelAll(Map<String, Object> queryParams) throws NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.ORDER_CANCEL_ALL;
        String json = httpClient.post(endpoint, queryParams);
        List<ByBitCancelOrder> response = BybitDeserializer.deserializeResponse(json, ByBitCancelOrder.class).getResult().getList();
        System.out.println(response);
        return response;
    }

    //todo: Its giving signature error error sign! origin_string[1696072003827VxWRRc3rYQDMypBf8U5000category=spot],
    // used correct signature generation method from the docs but its still giving error.
    public JsonNode getBybitSpotOpenOrders(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.OPEN_ORDERS;
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
//        List<BybitOpenOrders> response = BybitDeserializer.deserializeResponse(json, BybitOpenOrders.class).getResult().getList();
//        System.out.println(response);
//        return response;
        return new JsonMapper().readTree(json);
    }

    public JsonNode getBybitSpotOrderHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.ORDER_HISTORY;
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
//        List<BybitOrderHistory> response = BybitDeserializer.deserializeResponse(json, BybitOrderHistory.class).getResult().getList();
//        System.out.println(response);
//        return response;
        return new JsonMapper().readTree(json);
    }
    public JsonNode getBybitSpotTradeHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        String endpoint = BybitConstants.TRADE_HISTORY;
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
//        List<BybitTradeHistory> response = BybitDeserializer.deserializeResponse(json, BybitTradeHistory.class).getResult().getList();
//        System.out.println(response);
//        return response;
        return new JsonMapper().readTree(json);
    }

}
