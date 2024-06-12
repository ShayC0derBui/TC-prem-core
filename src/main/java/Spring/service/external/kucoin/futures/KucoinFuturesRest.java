//package Spring.service.external.kucoin.futures;
//
//import Spring.model.pojo.kucoin.futures.*;
//import Spring.client.kucoin.KucoinHttpClient;
//import Spring.model.rest.adapters.kucoin.futures.*;
//import Spring.model.rest.interfaces.*;
//import Spring.utils.deserializer.kucoin.futures.Deserializer;
//import com.google.gson.JsonSyntaxException;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Callable;
//import java.util.stream.Collectors;
//
//import static Spring.utils.constants.kucoin.futures.KucoinFuturesConstants.*;
//public class KucoinFuturesRest {
//    private final KucoinHttpClient kucoinHttpClient;
//
//    public KucoinFuturesRest(KucoinHttpClient kucoinHttpClient) {
//        this.kucoinHttpClient = kucoinHttpClient;
//    }
//
//    // Helper method to handle exceptions uniformly
//    private <T> T executeRequest(Callable<T> request) {
//        try {
//            return request.call();
//        } catch (IOException e) {
//            System.out.println("IO Error: " + e.getMessage());
//            e.printStackTrace();
//        } catch (JsonSyntaxException e) {
//            System.out.println("JSON Parsing Error: " + e.getMessage());
//            e.printStackTrace();
//        } catch (Exception e) {
//            System.out.println("General Error: " + e.getMessage());
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public List<Contracts> getKucoinFuturesSymbolsList() {
//        return executeRequest(() -> {
//            String endpoint = KUCOIN_FUTURES_CONTRACTS;
//            String res = kucoinHttpClient.get(endpoint);
//            List<KucoinFuturesContracts> des = Deserializer.deserializeKucoinFuturesContracts(res).getData();
//            return des.stream().map(KucoinContractAdapter::new).collect(Collectors.toList());
//        });
//    }
//
//    public OrderBook getKucoinFuturesOrderbook(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        String endpoint = KUCOIN_FUTURES_ORDERBOOK; //TODO: make global endpoint
//        String res = kucoinHttpClient.getWithQueryParams(endpoint, queryParams);
//        System.out.println(res);
//        OrderBook orderBook=new KucoinOrderBookAdapter(Deserializer.deserializeKucoinFuturesOrderbook(res), queryParams.get("symbol").toString());
//        System.out.println("ADAPTED:"+orderBook);
//
//        return orderBook;
//    }
//
//    public KucoinFuturesTicker getKucoinFuturesTicker(Map<String, Object> queryParams) {
//        return executeRequest(() -> {
//            String endpoint = KUCOIN_FUTURES_TICKER;
//            String res = kucoinHttpClient.getWithQueryParams(endpoint, queryParams);
//            return Deserializer.deserializeKucoinFuturesTickers(res);
//        });
//    }
//
//    public List<RecentTrades> getKucoinFuturesTradeHistory(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        String endpoint = KUCOIN_FUTURES_TRADE_HISTORY;
//        String res = kucoinHttpClient.getWithQueryParams(endpoint, queryParams);
//        List<KucoinFuturesTradeHistory> pojo = Deserializer.deserializeKucoinFuturesTradeHistory(res);
//        System.out.println("Kucoin POJO:"+pojo);
//        return pojo.stream().map(KucoinRecentTradesAdapter::new).collect(Collectors.toList());
//    }
//
//    public KucoinFuturesPremiumIndex getKucoinFuturesPremiumIndex(Map<String, Object> queryParams) {
//        return executeRequest(() -> {
//            String endpoint = KUCOIN_FUTURES_PREMIUM_INDEX;
//            String res = kucoinHttpClient.getWithQueryParams(endpoint, queryParams);
//            return Deserializer.deserializeKucoinFuturesIndex(res);
//        });
//    }
//
//    public KucoinFuturesCurrentMarkPrice getKucoinFuturesMarkPrice(Map<String, Object> queryParams) {
//        return executeRequest(() -> {
//            String endpoint = KUCOIN_FUTURES_MARK_PRICE + "/" + queryParams.get("symbol").toString() + "/current";
//            String res = kucoinHttpClient.get(endpoint);
//            return Deserializer.deserializeKucoinFuturesCurrentMarkPrice(res);
//        });
//    }
//
//    public KucoinFuturesPosition getKucoinFuturesPosition(Map<String, Object> queryParams) {
//        return executeRequest(() -> {
//            String endpoint = KUCOIN_FUTURES_POSITION;
//            String res = kucoinHttpClient.getSignedWithQueryParams(endpoint, queryParams);
//            return Deserializer.deserializeKucoinFuturesPosition(res);
//        });
//    }
//
//    public KucoinFuturesFundingHistory getKucoinFuturesFundingHistory(String symbol) {
//        return executeRequest(() -> {
//            String endpoint = KUCOIN_FUTURES_FUNDING_HISTORY;
//            Map<String, Object> queryParams = new HashMap<>();
//            queryParams.put("symbol", symbol);
//            String res = kucoinHttpClient.getSignedWithQueryParams(endpoint, queryParams);
//            return Deserializer.deserializeKucoinFuturesFundingHistory(res);
//        });
//    }
//
//    public KucoinFuturesFills getKucoinFuturesFills(Map<String, Object> queryParams) {
////            String symbol = request.get("symbol"); // Replace with the actual parameter names
////            String side = request.get("side");
////            String type = request.get("type");
////            long currentPage = Long.parseLong(request.get("currentPage"));
////            long pageSize = Long.parseLong(request.get("pageSize"));
//        return executeRequest(() -> {
//            String endpoint = KUCOIN_FUTURES_FILLS;
//            String res = kucoinHttpClient.getSignedWithQueryParams(endpoint, queryParams);
//            return Deserializer.deserializeKucoinFuturesFills(res);
//        });
//    }
//
//    public List<CandleStick> getKucoinFuturesKline(Map<String, Object> queryParams) throws IOException, URISyntaxException {
////            String symbol = request.get("symbol"); // XBTUSDM
////            Integer granularity = Integer.parseInt(request.get("granularity")); // Example: 480
//        String endpoint = KUCOIN_FUTURES_KLINE;
//        String res = kucoinHttpClient.getWithQueryParams(endpoint, queryParams);
//        List<KucoinKline> klineEntry = Deserializer.deserializeKucoinFuturesKline(res);
//        return klineEntry.stream().map(KucoinCandleStickAdapter::new).collect(Collectors.toList());
//    }
//
//    public KucoinFuturesCreateOrder postKucoinFuturesCreateOrder(String clientOid, String side, String symbol, String leverage, String type, String price, String size) {
//        return executeRequest(() -> {
//            String endpoint = KUCOIN_FUTURES_ORDER;
//            Map<String, Object> params = new HashMap<>();
//            params.put("clientOid", clientOid);
//            params.put("side", side);
//            params.put("symbol", symbol);
//            params.put("type", type);
//            params.put("leverage", leverage);
//            if (type == "limit") {
//                params.put("price", price);
//                params.put("size", size);
//            } else if (type == "market") {
//                params.put("size", size);
//            }
//            String response = kucoinHttpClient.post(endpoint, params);
//            return Deserializer.deserializeCreateOrder(response);
//        });
//    }
//
//    public List<OpenInterest> getKucoinFuturesOpenInterest(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        String endpoint = KUCOIN_FUTURES_OPEN_INTEREST;
//        String res = kucoinHttpClient.getWithQueryParams(endpoint, queryParams);
//        List<KucoinFuturesOpenInterestGenericResponse.KucoinFuturesOpenInterest> openInterest = Deserializer.deserializeKucoinFuturesOpenInterest(res);
//        System.out.println(openInterest);
//
//        return openInterest.stream().map(KucoinOpenInterestkAdapter::new).collect(Collectors.toList());
//    }
//
//    public FundingRate getKucoinFuturesFundingRate(Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        String endpoint = KUCOIN_FUTURES_FUNDING_RATE + queryParams.get("symbol").toString() + "/current";
//        String res = kucoinHttpClient.get(endpoint);
//        KucoinFuturesFundingRate funding_Rate = Deserializer.deserializeKucoinFuturesFundingRate(res);
//        System.out.println(funding_Rate);
//        FundingRate adapted=new KucoinFundingRateAdapter(funding_Rate);
//        System.out.println(adapted);//Problem in saving into db
//        return adapted;
//    }
//}
