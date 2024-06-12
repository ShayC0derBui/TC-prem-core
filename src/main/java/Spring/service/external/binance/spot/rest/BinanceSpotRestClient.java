package Spring.service.external.binance.spot.rest;

import Spring.client.binance.BinanceHttpClient;
import Spring.model.pojo.binance.*;
import Spring.model.pojo.binance.spot.AggregateTrades;
import Spring.model.pojo.binance.spot.AllUserOrder;
import Spring.model.pojo.binance.spot.OpenOrders;
import Spring.model.rest.adapters.binance.*;
import Spring.model.rest.interfaces.CandleStick;
import Spring.model.rest.interfaces.Contracts;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.interfaces.RecentTrades;
import Spring.utils.constants.binance.BinanceConstants;
import Spring.utils.deserializer.binance.BinanceDeserializer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static Spring.utils.constants.binance.BinanceConstants.SPOT_24HR_TICKER;
import static Spring.utils.constants.binance.BinanceConstants.SPOT_TICKER;

public class BinanceSpotRestClient {
    private final BinanceHttpClient httpClient;

    public BinanceSpotRestClient(BinanceHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<Contracts> getBybitSpotTickers() throws IOException {
        String endpoint = SPOT_TICKER;
        String priceResponse = httpClient.get(SPOT_TICKER);
        String binance24hrResponse = httpClient.get(SPOT_24HR_TICKER);
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


    public List<CandleStick> getSpotCandlesticks(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = BinanceConstants.SPOT_CANDLESTICK;
        String json = httpClient.getWithQueryParams(endpoint, params);
        List<BinanceCandlesticks> response = BinanceDeserializer.deserializeCandlesticks(json);
        return response.stream().map(kline -> new BinanceCandleStickAdapter(kline, endpoint, params.get("symbol").toString())).collect(Collectors.toList());
    }

    public List<RecentTrades> getSpotRecentTrades(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = BinanceConstants.SPOT_RECENT_TRADES;
        String json = httpClient.getWithQueryParams(endpoint, params);
        List<BinanceRecentTrades> response = BinanceDeserializer.deserializeBinanceListResponse(json, BinanceRecentTrades.class);
        return response.stream().map(trade -> new BinanceRecentTradesAdapter(trade, endpoint, params.get("symbol").toString())).collect(Collectors.toList());
    }

    public OrderBook getSpotOrderBook(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = BinanceConstants.SPOT_ORDER_BOOK;
        String json = httpClient.getWithQueryParams(endpoint, params);
        BinanceOrderBook response = BinanceDeserializer.deserializeBinanceResponse(json, BinanceOrderBook.class);
        return new BinanceOrderBookAdapter(response, params.get("symbol").toString());
    }

    public List<AggregateTrades> getSpotAggregateTrades(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = BinanceConstants.SPOT_AGG_TRADES;
        String json = httpClient.getWithQueryParams(endpoint, params);
        List<AggregateTrades> response = BinanceDeserializer.deserializeBinanceListResponse(json, AggregateTrades.class);
        System.out.println(response);
        return response;
    }

    public List<AllUserOrder> getAllSpotOrders(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = BinanceConstants.SPOT_ALL_ORDERS;
        String json = httpClient.getWithQueryParams(endpoint, params);
        List<AllUserOrder> response = BinanceDeserializer.deserializeBinanceListResponse(json, AllUserOrder.class);
        System.out.println(response);
        return response;
    }

    public List<OpenOrders> getOpenOrders(Map<String, Object> params) throws IOException, URISyntaxException {
        String endpoint = BinanceConstants.SPOT_OPEN_ORDERS;
        String json = httpClient.getWithQueryParams(endpoint, params);
        List<OpenOrders> response = BinanceDeserializer.deserializeBinanceListResponse(json, OpenOrders.class);
        System.out.println(response);
        return response;
    }


}
