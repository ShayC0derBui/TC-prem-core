package Spring.service.external.gateio.futures.delivery.rest;

import Spring.client.gateio.GateioHttpClient;
import Spring.model.pojo.gateio.futures.*;
import Spring.model.rest.adapters.gateio.futures.*;
import Spring.model.rest.interfaces.*;
import Spring.utils.deserializer.gateio.GateioDeserializer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class GateioFuturesDeliveryRest {

    public final GateioHttpClient gateioHttpClient;
    public GateioFuturesDeliveryRest(GateioHttpClient gateioHttpClient) {
        this.gateioHttpClient = gateioHttpClient;
    }


    public List<GateioFuturesContract> getDeliveryFuturesContracts(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/contracts";
        String json = gateioHttpClient.getWithQueryParams(endpoint, queryParams);
        List<GateioFuturesContract> contractList = GateioDeserializer.deserializeDeliveryFuturesContracts(json);
        System.out.println(contractList);

        return contractList;
    }

    public GateioFuturesContract getSingleDeliveryFuturesContract(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/contracts/" + queryParams.get("contract");
        queryParams.remove("settleCurrency");
        queryParams.remove("contract");
        String json = gateioHttpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        GateioFuturesContract perpetualFuturesContract = GateioDeserializer.deserializeSingleDeliveryFuturesContract(json);
        System.out.println(perpetualFuturesContract);

        return perpetualFuturesContract;
    }

    public OrderBook getDeliveryFuturesOrderbook(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/order_book";
        queryParams.remove("settleCurrency");
        String json = gateioHttpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        GateioFuturesOrderbook perpetualFuturesSpotOrderbook = GateioDeserializer.deserializeDeliveryFuturesOrderbook(json);
        return new GateioOrderBookFuturesAdapter(perpetualFuturesSpotOrderbook, (String) queryParams.get("contract"));
    }

    public List<RecentTrades> getDeliveryFuturesTrades(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/trades";
        queryParams.remove("settleCurrency");
        String json = gateioHttpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesTrade> perpetualFuturesTradeList = GateioDeserializer.deserializeDeliveryFuturesTrades(json);
        return perpetualFuturesTradeList.stream().map(GateioRecentTradesFuturesAdapter::new).collect(Collectors.toList());
    }

    public List<CandleStick> getDeliveryFuturesCandlesticks(Map<String, Object> queryParams) throws IOException, URISyntaxException {

        if (!queryParams.containsKey("interval")) {
            queryParams.put("interval", "1m");
        }

        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/candlesticks";
        queryParams.remove("settleCurrency");
        String json = gateioHttpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesCandlesticks> candlesticks = GateioDeserializer.deserializeDeliveryFuturesCandlesticks(json);
        return candlesticks.stream().map(GateioCandleStickFuturesAdapter::new).collect(Collectors.toList());
    }

    public List<FundingRate> getDeliveryFuturesFundingRate(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/contracts";
        queryParams.remove("settleCurrency");
        /* String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/contracts";
        queryParams.remove("settleCurrency");
        String json = httpClient.getWithQueryParams(endpoint, queryParams);
        List<GateioFuturesFundingRate> contractList = GateioDeserializer.deserializePerpetualFuturesFundingRate(json);
        System.out.println("RAW:"+contractList);
        List<FundingRate> fundingRateList= contractList.stream().map(GateioFundingRateFuturesAdapter::new).collect(Collectors.toList());
        System.out.println("ADAPTED:"+fundingRateList);
        List<FundingRateEntity> entities = fundingRateList.stream()
                .map(fundingRate -> new FundingRateEntity(fundingRate.getSymbol(), fundingRate.getTimePoint(), fundingRate.getFundingRate(), fundingRate.getNextFundingInterval()))
                .collect(Collectors.toList());
        System.out.println(entities);

        System.out.println("Saving data");

        fundingRateRepository.saveAll(entities);
        return fundingRateList; */
        String json = gateioHttpClient.getWithQueryParams(endpoint, queryParams);
        List<GateioFuturesFundingRate> contractList = GateioDeserializer.deserializePerpetualFuturesFundingRate(json);
        System.out.println(contractList);
        List<FundingRate> fundingRateList= contractList.stream().map(GateioFundingRateFuturesAdapter::new).collect(Collectors.toList());
        return contractList.stream().map(GateioFundingRateFuturesAdapter::new).collect(Collectors.toList());
    }

    public List<OpenInterest> getDeliveryFuturesOpenInterest(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/futures/" + queryParams.get("settleCurrency") + "/contract_stats";
        queryParams.remove("settleCurrency");
        String json = gateioHttpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesOpenInterest> gateioFuturesOpenInterest = GateioDeserializer.deserializePerpetualFuturesOpenInterest(json);
        String contract = (String) queryParams.get("contract");
        return gateioFuturesOpenInterest.stream().map(p -> new GateioOpenInterestFuturesAdapter(p, contract)).collect(Collectors.toList());
    }

    public List<Contracts> getDeliveryFuturesTickers(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/tickers";
        queryParams.remove("settleCurrency");
        String json = gateioHttpClient.getWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesTickers> tickers = GateioDeserializer.deserializeDeliveryFuturesTickers(json);
        System.out.println(tickers);
        return tickers.stream().map(GateioContractAdapter::new).collect(Collectors.toList());
    }

    public GateioFuturesOrder postSingleDeliveryFuturesOrder(Map<String, Object> queryParams) throws IOException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/orders";
        queryParams.remove("settleCurrency");
        String json = gateioHttpClient.postCustomBody(endpoint, queryParams);
        System.out.println(json);
        GateioFuturesOrder order = GateioDeserializer.deserializeDeliveryFuturesOrder(json);
        System.out.println(order);

        return order;
    }

    public List<GateioFuturesOrder> getAllDeliveryFuturesOrders(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/orders";
        queryParams.remove("settleCurrency");
        String json = gateioHttpClient.getSignedWithQueryParams(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesOrder> perpetualFuturesOrderList = GateioDeserializer.deserializeDeliveryFuturesOrderList(json);
        for (GateioFuturesOrder p : perpetualFuturesOrderList) {
            System.out.println(p);
        }
        return perpetualFuturesOrderList;
    }

    public GateioFuturesOrder getSingleDeliveryFuturesOrder(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/orders/" + queryParams.get("orderId");
        queryParams.remove("settleCurrency");
        queryParams.remove("orderId");
        String json = gateioHttpClient.getSignedWithQueryParams(endpoint, queryParams);
        GateioFuturesOrder order = GateioDeserializer.deserializeDeliveryFuturesOrder(json);
        System.out.println(order);

        return order;

    }

    public GateioFuturesOrder amendSingleDeliveryFuturesOrder(Map<String, Object> queryParams) throws URISyntaxException, IOException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/orders/" + queryParams.get("orderId");
        queryParams.remove("settleCurrency");
        queryParams.remove("orderId");
        String json = gateioHttpClient.put(endpoint, queryParams);
        System.out.println(json);
        GateioFuturesOrder order = GateioDeserializer.deserializeDeliveryFuturesOrder(json);
        System.out.println(order);

        return order;
    }

    public List<GateioFuturesCancelAllOrders> cancelAllDeliveryFuturesMatchedOrders(Map<String, Object> queryParams) throws IOException, URISyntaxException {
        String endpoint = "/delivery/" + queryParams.get("settleCurrency") + "/price_orders";
        queryParams.remove("settleCurrency");
        String json = gateioHttpClient.delete(endpoint, queryParams);
        System.out.println(json);
        List<GateioFuturesCancelAllOrders> cancelAllOrders = GateioDeserializer.deserializeDeliveryCancelAllOrders(json);
        System.out.println(cancelAllOrders);
        return cancelAllOrders;
    }
}