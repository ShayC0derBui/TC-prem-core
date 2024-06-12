package Spring.service.external.gateio.spot.rest;

import Spring.model.pojo.gateio.spot.*;
import Spring.client.gateio.GateioHttpClient;
import Spring.model.rest.adapters.gateio.spot.GateioCandleStickSpotAdapter;
import Spring.model.rest.adapters.gateio.spot.GateioContractSpotAdapter;
import Spring.model.rest.adapters.gateio.spot.GateioOrderBookSpotAdapter;
import Spring.model.rest.adapters.gateio.spot.GateioRecentTradesSpotAdapter;
import Spring.model.rest.interfaces.CandleStick;
import Spring.model.rest.interfaces.Contracts;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.interfaces.RecentTrades;
import Spring.utils.deserializer.gateio.GateioDeserializer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static Spring.utils.constants.gateio.GateioConstants.*;

public class GateioSpotRestClient {
    private final GateioHttpClient httpClient;

    public GateioSpotRestClient(GateioHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<Contracts> getSpotTickers() throws IOException {

        String json = httpClient.get(GATEIO_SPOT_TICKERS_ENDPOINT);
        List<GateioSpotTicker> tickersList = GateioDeserializer.deserializeSpotTickers(json);
        for (GateioSpotTicker ticker : tickersList) {
            System.out.println(ticker.toString());
        }
        return tickersList.stream().map(GateioContractSpotAdapter::new).collect(Collectors.toList());
    }

    public List<GateioSpotCurrencyPair> getSpotCurrencyPairs() throws IOException {
        String json = httpClient.get(GATEIO_SPOT_CURRENCY_PAIRS_ENDPOINT);
        List<GateioSpotCurrencyPair> currencyPairsList = GateioDeserializer.deserializeSpotCurrencyPairs(json);
        for (GateioSpotCurrencyPair currencyPair : currencyPairsList) {
            System.out.println(currencyPair.toString());
        }
        return currencyPairsList;
    }

    public List<GateioSpotTrade> getSpotTrades(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = httpClient.getWithQueryParams(GATEIO_SPOT_TRADES_ENDPOINT, queryParams);
        List<GateioSpotTrade> tradesList = GateioDeserializer.deserializeSpotTrades(json);
        for (GateioSpotTrade trades : tradesList) {
            System.out.println(trades.toString());
        }
        return tradesList;
    }

    public List<CandleStick> getSpotCandlesticks(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = httpClient.getWithQueryParams(GATEIO_SPOT_CANDLESTICKS_ENDPOINT, queryParams);
        List<GateioSpotCandlesticks> result = GateioDeserializer.deserialiseSpotCandles(json);
        for (GateioSpotCandlesticks candlesticks : result) {
            System.out.println(candlesticks.toString());
        }

        return result.stream().map(GateioCandleStickSpotAdapter::new).collect(Collectors.toList());
    }

    public OrderBook getSpotOrderbook(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = httpClient.getWithQueryParams(GATEIO_SPOT_ORDERBOOK_ENDPOINT, queryParams);
        GateioSpotOrderbook gateioSpotOrderbook = GateioDeserializer.deserializeSpotOrderBook(json);
        System.out.println(gateioSpotOrderbook.toString());
        return new GateioOrderBookSpotAdapter(gateioSpotOrderbook, (String) queryParams.get("currency_pair"));
    }

    public List<GateioSpotAccount> getSpotAccounts() throws IOException {
        String json = httpClient.getSigned(GATEIO_SPOT_ACCOUNTS_ENDPOINT);
        List<GateioSpotAccount> accountList = GateioDeserializer.deserializeSpotAccounts(json);
        for (GateioSpotAccount account : accountList) {
            System.out.println(account.toString());
        }
        return accountList;
    }

    public List<GateioSpotOrderList> getSpotOpenOrders() throws IOException {
        String json = httpClient.getSigned(GATEIO_SPOT_OPEN_ORDERS_ENDPOINT);
        //todo: temporary
        System.out.println(json);
        List<GateioSpotOrderList> openOrdersList = GateioDeserializer.deserializeSpotOpenOrders(json);
        for (GateioSpotOrderList order : openOrdersList) {
            System.out.println(order.toString());
        }
        return openOrdersList;
    }

    public GateioSpotOrder postSpotCreateOrder(Map<String, Object> queryParams) throws IOException {
        String json = httpClient.post(GATEIO_SPOT_CREATE_ORDER_ENDPOINT, queryParams);

        //todo: temporary
        System.out.println(json);

        GateioSpotOrder order = GateioDeserializer.deserializeSpotOrder(json);
        System.out.println(order);
        return order;
    }

    public List<RecentTrades> getSpotTradingHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String json = httpClient.getWithQueryParams(GATEIO_SPOT_TRADING_HISTORY_ENDPOINT, queryParams);
        List<GateioSpotTrade> tradingHistoryList = GateioDeserializer.deserializeSpotTradingHistory(json);
        System.out.println(tradingHistoryList.toString());
        return tradingHistoryList.stream().map(GateioRecentTradesSpotAdapter::new).collect(Collectors.toList());
    }

    public GateioSpotOrder amendSingleSpotOrder(Map<String, Object> queryParams) throws URISyntaxException, IOException {

        String endpoint = GATEIO_SPOT_AMEND_ORDER_ENDPOINT + "/" + queryParams.get("orderId");
        queryParams.remove("orderId");
        String currencyPair = (String) queryParams.get("currencyPair");
        queryParams.remove("currencyPair");
        String json = httpClient.patch(endpoint, "currency_pair", currencyPair, queryParams);
        System.out.println(json);
        GateioSpotOrder amendedOrder = GateioDeserializer.deserializeSpotOrder(json);
        System.out.println(amendedOrder.toString());

        return amendedOrder;

    }



}

