//package Spring.service.external.kucoin.spot.restapi;
//
//import Spring.client.kucoin.KucoinHttpClient;
//import Spring.model.pojo.kucoin.spot.*;
//import Spring.model.rest.adapters.kucoin.spot.KucoinCandleStickSpotAdapter;
//import Spring.model.rest.adapters.kucoin.spot.KucoinContractSpotAdapter;
//import Spring.model.rest.adapters.kucoin.spot.KucoinOrderBookSpotAdapter;
//import Spring.model.rest.adapters.kucoin.spot.KucoinRecentTradesSpotAdapter;
//import Spring.model.rest.interfaces.CandleStick;
//import Spring.model.rest.interfaces.Contracts;
//import Spring.model.rest.interfaces.OrderBook;
//import Spring.model.rest.interfaces.RecentTrades;
//import Spring.utils.deserializer.kucoin.spot.KucoinDeserialiser;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//import static Spring.utils.constants.kucoin.spot.KucoinConstants.*;
//
//public class KucoinSpotRestClient {
//    private final KucoinHttpClient kucoinHttpClient;
//
//    public KucoinSpotRestClient(KucoinHttpClient kucoinHttpClient) {
//        this.kucoinHttpClient = kucoinHttpClient;
//    }
//
//    public String getSymbolsList() throws Exception {
//        return kucoinHttpClient.getSigned("/api/v2/symbols");
//    }
//
//    public String getTicker(String symbol) throws Exception {
//        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("symbol", symbol);
//        return kucoinHttpClient.getSignedWithQueryParams("/api/v1/market/orderbook/level1", queryParams);
//    }
//
//    public List<Contracts> getAllTickers() throws Exception {
//        String json =  kucoinHttpClient.getSigned("/api/v1/market/allTickers");
//        List<KucoinSpotTickers> result = KucoinDeserialiser.deserializeSpotTickers(json).getTicker();
//        return result.stream().map(KucoinContractSpotAdapter::new).collect(Collectors.toList());
//    }
//
//    public String get24hStats(String symbol) throws Exception {
//        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("symbol", symbol);
//        return kucoinHttpClient.getSignedWithQueryParams("/api/v1/market/stats", queryParams);
//    }
//
//    public OrderBook getOrderBook(Map<String, Object> queryParams) throws Exception {
//        String url = KUCOIN_SPOT_ORDERBOOK;
//        String res = kucoinHttpClient.getSignedWithQueryParams(url, queryParams);
//        KucoinSpotOrderbook orderbook = KucoinDeserialiser.deserializeSpotOrderBook(res);
//        return new KucoinOrderBookSpotAdapter(orderbook, queryParams.get("symbol").toString());
//    }
//
//    public String getAllOrdersByFilters(String status, String symbol, String side, String type) throws Exception {
//        Map<String, Object> queryParams = new HashMap<>();
//        if (status.equalsIgnoreCase("active") || status.equalsIgnoreCase("done")) {
//            queryParams.put("status", status);
//        }
//        if (side.equalsIgnoreCase("buy") || side.equalsIgnoreCase("sell")) {
//            queryParams.put("side", side);
//        }
//        if (type.equalsIgnoreCase("limit") || type.equalsIgnoreCase("market") || type.equalsIgnoreCase("limit_stop") || type.equalsIgnoreCase("market_stop")) {
//            queryParams.put("type", type);
//        }
//        if (!symbol.equals("")) {
//            queryParams.put("symbol", symbol);
//        }
//
//        return kucoinHttpClient.getSignedWithQueryParams("/api/v1/orders", queryParams);
//    }
//
//    /**
//     * This fetches only 50 of the values in the first page. Page size can be modified upto 500
//     */
//    public String getLast24hAccountLedger(String currency) throws Exception {
//
//        String endpoint = "/api/v1/accounts/ledgers";
//        Map<String, Object> queryParams = new HashMap<>();
//        if (!currency.equals("")) {
//            queryParams.put("currency", currency);
//            return kucoinHttpClient.getSignedWithQueryParams(endpoint, queryParams);
//        } else {
//            return kucoinHttpClient.getSigned(endpoint);
//        }
//    }
//
//    /**
//     * This fetches only 50 of the values in the first page. Page size can be modified upto 500
//     */
//    public String getLast24hDeposits(String currency) throws Exception {
//
//        String endpoint = "/api/v1/accounts/ledgers";
//        Map<String, Object> queryParams = new HashMap<>();
//        if (!currency.equals("")) {
//            queryParams.put("currency", currency);
//            return kucoinHttpClient.getSignedWithQueryParams(endpoint, queryParams);
//        } else {
//            return kucoinHttpClient.getSigned(endpoint);
//        }
//    }
//
//    public String getLast24hFills() throws Exception {
//        return kucoinHttpClient.getSigned("/api/v1/limit/fills");
//    }
//
//
//    public String getAllAccounts() throws Exception {
//        return kucoinHttpClient.getSigned("/api/v1/accounts");
//    }
//
//    public String getAllAccounts(String currency, String type) throws Exception {
//
//        String endpoint = "/api/v1/accounts";
//
//        if (currency.equals("") && type.equals("")) {
//            return kucoinHttpClient.getSigned(endpoint);
//        } else {
//            Map<String, Object> queryParams = new HashMap<>();
//            if (!currency.equals("")) {
//                queryParams.put("currency", currency);
//            }
//            if (!type.equals("")) {
//                queryParams.put("type", type.toLowerCase());
//            }
//            return kucoinHttpClient.getSignedWithQueryParams(endpoint, queryParams);
//        }
//    }
//
//    public String getTransferrableBalance(String currency, String type) throws Exception {
//        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("currency", currency);
//        queryParams.put("type", type.toUpperCase());
//        return kucoinHttpClient.getSignedWithQueryParams("/api/v1/accounts/transferable", queryParams);
//    }
//
//    public String getAllSubAccounts() throws Exception {
//        return kucoinHttpClient.getSigned("/api/v1/sub/user");
//    }
//
//    public String getAllSubAccountsAggregatedBalance() throws Exception {
//        return kucoinHttpClient.getSigned("/api/v1/sub-accounts");
//    }
//
//    public String getSingleAccount(String accountId) throws Exception {
//        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("accountId", accountId);
//        return kucoinHttpClient.getSignedWithQueryParams("/api/v1/accounts", queryParams);
//    }
//
//    public String getAccountSummaryInfo() throws Exception {
//        return kucoinHttpClient.getSigned("/api/v2/user-info");
//    }
//
//    public String getSubAccountSpotApiList(String subName) throws Exception {
//        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("subName", subName);
//        return kucoinHttpClient.getSignedWithQueryParams("/api/v1/sub/api-key", queryParams);
//    }
//
//    public String getAllDepositAddress(String currency) throws Exception {
//        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("currency", currency);
//        return kucoinHttpClient.getSignedWithQueryParams("/api/v2/deposit-addresses", queryParams);
//    }
//
//    public String getSingleOrderByOrderId(String orderId) throws Exception {
//        return kucoinHttpClient.getSigned("/api/v1/orders/" + orderId);
//    }
//
//    public String getSingleOrderByClientOid(String clientOid) throws Exception {
//        return kucoinHttpClient.getSigned("/api/v1/order/client-order/" + clientOid);
//    }
//
//    public String getSingleSpotStop_OrderByOrderId(String orderId) throws Exception {
//        return kucoinHttpClient.getSigned("/api/v1/stop-order/" + orderId);
//    }
//
//    public String getSingleSpotStop_OrderByClientOid(String clientOid) throws Exception {
//        Map<String, Object> queryParams = new HashMap<>();
//        queryParams.put("clientOid", clientOid);
//        return kucoinHttpClient.getSignedWithQueryParams("/api/v1/stop-order/queryOrderByClientOid", queryParams);
//    }
//
//    public String postSingleSpotOrder(String symbol, String side, String type, String price, String size) throws Exception {
//
//        Map<String, Object> bodyParams = new HashMap<>();
//        bodyParams.put("symbol", symbol);
//        bodyParams.put("side", side.toLowerCase());
//        bodyParams.put("type", type);
//        bodyParams.put("clientOid", UUID.randomUUID().toString());
//        bodyParams.put("size", size);
//
//        if (type.equalsIgnoreCase("limit") || type.equals("")) {
//            bodyParams.put("price", price);
//
//            //more shit can be added as the trader needs
//        } else if (type.equalsIgnoreCase("market")) {
//            //more shit can be added here too
//        } else {
//            return "";
//        }
//
//
//        return kucoinHttpClient.post("/api/v1/orders/", bodyParams);
//    }
//
//
//    public String postSingleSpotStop_Order(String symbol, String side, String type, String price, String size, String stopPrice, String stop) throws Exception {
//        Map<String, Object> bodyParams = new HashMap<>();
//        bodyParams.put("symbol", symbol);
//        bodyParams.put("side", side.toLowerCase());
//        bodyParams.put("type", type);
//        bodyParams.put("clientOid", UUID.randomUUID().toString());
//        bodyParams.put("stop", stop.toLowerCase());
//        bodyParams.put("stopPrice", stopPrice);
//        bodyParams.put("size", size);
//
//        if (type.equalsIgnoreCase("limit") || type.equals("")) {
//            bodyParams.put("price", price);
//
//            //more shit can be added as the trader needs
//        } else if (type.equalsIgnoreCase("market")) {
//            //more shit can be added here too
//        } else {
//            return "";
//        }
//
//        return kucoinHttpClient.post("/api/v1/stop-order/", bodyParams);
//    }
//
//    public String deleteSingleSpotOrderByOrderId(String orderId) throws Exception {
//        return kucoinHttpClient.kucoinSignedDeleteRequest("/api/v1/orders/" + orderId);
//    }
//
//    public String deleteSingeSpotOrderByClientOid(String clientOid) throws Exception {
//        return kucoinHttpClient.kucoinSignedDeleteRequest("/api/v1/order/client-order/" + clientOid);
//    }
//
//    public String deleteSingleSpotStop_Order(String orderId) throws Exception {
//        return kucoinHttpClient.kucoinSignedDeleteRequest("/api/v1/stop-order/" + orderId);
//    }
//
//    public List<CandleStick> getKucoinSpotCandlestick(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        String uri = KUCOIN_SPOT_CANDLESTICKS;
//        String res = kucoinHttpClient.getWithQueryParams(uri, queryParams);
//        List<KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks> candlesticks = KucoinDeserialiser.deserializeSpotCandlesticks(res);
//        return candlesticks.stream().map(KucoinCandleStickSpotAdapter::new).collect(Collectors.toList());
//    }
//
//    public List<RecentTrades> getKucoinRecentSpotTrades(Map<String, Object> queryParams) throws Exception {
//        String endpoint = KUCOIN_SPOT_RECENT_TRADES;
//        String res = kucoinHttpClient.getWithQueryParams(endpoint, queryParams);
//        List<KucoinRecentTradesSpot> recentTrades = KucoinDeserialiser.deserializeRecentTrades(res);
//        return recentTrades.stream().map(KucoinRecentTradesSpotAdapter::new).collect(Collectors.toList());
//    }
//}
