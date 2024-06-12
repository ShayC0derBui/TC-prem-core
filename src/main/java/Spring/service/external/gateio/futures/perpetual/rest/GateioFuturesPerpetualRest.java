package Spring.service.external.gateio.futures.perpetual.rest;

import Spring.client.gateio.GateioHttpClient;
import Spring.model.pojo.gateio.futures.*;
import Spring.model.pojo.gateio.futures.perpetual.*;
import Spring.model.rest.adapters.gateio.futures.*;
import Spring.model.rest.interfaces.FundingRate;
import Spring.model.rest.interfaces.OpenInterest;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.interfaces.RecentTrades;
import Spring.utils.deserializer.gateio.GateioDeserializer;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class GateioFuturesPerpetualRest {

    public final GateioHttpClient httpClient;
    public GateioFuturesPerpetualRest(GateioHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<GateioFuturesContract> getPerpetualFuturesContracts(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/contracts";
        queryParams.remove("settleCurrency");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        List<GateioFuturesContract> contractList = GateioDeserializer.deserializePerpetualFuturesContracts(json);
        System.out.println(contractList);
        return contractList;
    }

    public List<FundingRate> getPerpetualFuturesFundingRate(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/contracts";
        queryParams.remove("settleCurrency");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        List<GateioFuturesFundingRate> contractList = GateioDeserializer.deserializePerpetualFuturesFundingRate(json);
       // System.out.println("RAW:"+contractList);
        List<FundingRate> fundingRateList= contractList.stream().map(GateioFundingRateFuturesAdapter::new).collect(Collectors.toList());
        //System.out.println("ADAPTED:"+fundingRateList);
        return fundingRateList;

    }

    public List<OpenInterest> getPerpetualFuturesOpenInterest(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/contract_stats";
        queryParams.remove("settleCurrency");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesOpenInterest> gateioFuturesOpenInterest = GateioDeserializer.deserializePerpetualFuturesOpenInterest(json);
        String contract = (String) queryParams.get("contract");
        return gateioFuturesOpenInterest.stream().map(p -> new GateioOpenInterestFuturesAdapter(p, contract)).collect(Collectors.toList());
    }

    public GateioFuturesContract getSinglePerpetualFuturesContract(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/contracts/" + queryParams.get("contract");
        queryParams.remove("settleCurrency");
        queryParams.remove("contract");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        GateioFuturesContract gateioPerpetualFuturesContract = GateioDeserializer.deserializeSinglePerpetualFuturesContract(json);
        System.out.println(gateioPerpetualFuturesContract);

        return gateioPerpetualFuturesContract;
    }

    public OrderBook getPerpetualFuturesOrderbook(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/order_book";
        queryParams.remove("settleCurrency");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);

        System.out.println(json);
        GateioFuturesOrderbook perpetualFuturesSpotOrderbook = GateioDeserializer.deserializePerpetualFuturesOrderbook(json);
        System.out.println(perpetualFuturesSpotOrderbook);

        // Create an OrderBook instance
        OrderBook orderBook = new GateioOrderBookFuturesAdapter(perpetualFuturesSpotOrderbook, (String) queryParams.get("contract"));
        System.out.println("Data in Rest: "+orderBook);
        return orderBook;
    }



    public List<RecentTrades> getPerpetualFuturesTrades(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/trades";
        queryParams.remove("settleCurrency");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesTrade> gateioPerpetualFuturesTradeList = GateioDeserializer.deserializePerpetualFuturesTrades(json);
        System.out.println(gateioPerpetualFuturesTradeList);
        return gateioPerpetualFuturesTradeList.stream().map(GateioRecentTradesFuturesAdapter::new).collect(Collectors.toList());
    }

    public List<GateioCandleStickFuturesAdapter> getPerpetualFuturesCandlesticks(Map<String, Object> queryParams) throws IOException, URISyntaxException {

        if (!queryParams.containsKey("interval")) {
            queryParams.put("interval", "1m");
        }

        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/candlesticks";
        queryParams.remove("settleCurrency");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesCandlesticks> candlesticks = GateioDeserializer.deserializePerpetualFuturesCandlesticks(json);
        System.out.println(candlesticks);
        return candlesticks.stream().map(GateioCandleStickFuturesAdapter::new).collect(Collectors.toList());
    }

    public List<GateioFuturesTickers> getPerpetualFutureTickers(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/tickers";
        queryParams.remove("settleCurrency");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesTickers> tickers = GateioDeserializer.deserializePerpetualFuturesTickers(json);
        System.out.println(tickers);

        return tickers;
    }

    public GateioFuturesOrder postSinglePerpetualFuturesOrder(Map<String, Object> queryParams) throws IOException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/orders";
        queryParams.remove("settleCurrency");
        String json = httpClient.postCustomBody(endpoint, queryParams);
        System.out.println(json);
        GateioFuturesOrder order = GateioDeserializer.deserializePerpetualFuturesOrder(json);
        System.out.println(order);

        return order;
    }

    public List<GateioFuturesOrder> getAllPerpetualFuturesOrders(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/orders";
        queryParams.remove("settleCurrency");
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesOrder> gateioPerpetualFuturesOrderList = GateioDeserializer.deserializePerpetualFuturesOrderList(json);
        for (GateioFuturesOrder p : gateioPerpetualFuturesOrderList) {
            System.out.println(p);
        }
        return gateioPerpetualFuturesOrderList;
    }

    public GateioFuturesOrder getSinglePerpetualFuturesOrder(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/orders/" + queryParams.get("orderId");
        queryParams.remove("settleCurrency");
        queryParams.remove("orderId");
        String json = httpClient.getSignedWithQueryParams(endpoint, queryParams);
        GateioFuturesOrder order = GateioDeserializer.deserializePerpetualFuturesOrder(json);
        System.out.println(order);

        return order;

    }
}
