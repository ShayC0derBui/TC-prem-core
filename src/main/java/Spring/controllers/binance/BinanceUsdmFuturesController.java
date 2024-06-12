package Spring.controllers.binance;

import Spring.client.binance.BinanceHttpClient;
import Spring.model.pojo.binance.BinanceFutures24HrTickerPriceChange;
import Spring.model.pojo.binance.futures.usdm.*;
import Spring.model.rest.interfaces.*;
import Spring.service.external.binance.futures.usdm.rest.BinanceFuturesUsdmRest;
import Spring.service.user.KeyService;
import Spring.utils.constants.binance.BinanceConstants;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;


@Component
@RestController
@RequestMapping("api/binance/futures/usdm")
public class BinanceUsdmFuturesController {
    //   @Autowired
//    public BybitLinearFuturesController(KeyService keyService,ObjectMapper objectMapper) {
//        this.restClient = new BybitLinearRest(new BybitHttpClient(BybitConstants.BASE_URL, keyService),objectMapper);
//    }
    private final BinanceFuturesUsdmRest restClient;
    Gson gson = new Gson();

    @Autowired
    public BinanceUsdmFuturesController(KeyService keyService, ObjectMapper obj) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.restClient = new BinanceFuturesUsdmRest(new BinanceHttpClient(BinanceConstants.USDM_BASE_URL, keyService), obj, keyService);
    }

    @GetMapping("get-contracts")
    public List<Contracts> getFuturesContracts() {
        try {
            return restClient.getBinanceUsdmFuturesContracts();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getPremiumIndex")
    public List<BinanceUsdmFuturesPremiumIndex> getPremiumIndex() {
        try {
            List<BinanceUsdmFuturesPremiumIndex> premiumIndexList = restClient.getBinanceUsdmFuturesPremiumIndex();
            return premiumIndexList;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-orderbook")
    public OrderBook getOrderBook(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceUsdmFuturesOrderBook(request);
        } catch (IllegalArgumentException | IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-trades")
    public List<RecentTrades> getTrades(@RequestParam Map<String, Object> request) {
        try {
            List<RecentTrades> tradesList = restClient.getBinanceUsdmFuturesTrades(request);
            return tradesList;
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-historical-trades")
    public List<RecentTrades> getHistoricalTrades(@RequestParam Map<String, Object> request) {
        try {
            List<RecentTrades> tradesList = restClient.getBinanceUsdmFuturesHistoricalTrades(request);
            return tradesList;
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-candlesticks")
    public List<CandleStick> getCandlesticks(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceUsdmFuturesCandlesticks(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-funding-rate")
    public List<FundingRate> getFundingRateHistory(Map<String, Object> queryParams) {
        try {
            return restClient.getBinanceUsdmFuturesFundingRateHistory(queryParams);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("get24HrTickerPriceChange")
    public List<BinanceFutures24HrTickerPriceChange> get24HrTickerPriceChange(Map<String, Object> queryParams) {
        try {
            List<BinanceFutures24HrTickerPriceChange> tickerPriceChangeList = restClient.getBinanceUsdmFutures24HrTickerPriceChange(queryParams);
            return tickerPriceChangeList;
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("get-open-interest")
    public OpenInterest getOpenInterest(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceUsdmFuturesOpenInterest(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getTopLongShortAccountRatio")
    public List<BinanceUsdmFuturesTopLongShortRatio> getTopLongShortAccountRatio(@RequestParam Map<String, Object> request) {
        try {
            List<BinanceUsdmFuturesTopLongShortRatio> topLongShortRatio = restClient.getBinanceUsdmFuturesTopLongShortAccountRatio(request);
            return topLongShortRatio;
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getTopLongShortPositionRatio")
    public List<BinanceUsdmFuturesTopLongShortRatio> getTopLongShortPositionRatio(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceUsdmFuturesTopLongShortPositionRatio(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("create-order")
    public JsonNode postBinanceCreateOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.postBinanceCreateOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PostMapping("cancel-order")
    public JsonNode cancelOrder(@RequestBody Map<String, Object> request) throws IOException, URISyntaxException {
        try {
            return restClient.deleteBinanceCancelOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/cancel-all-orders")
    public JsonNode deleteBinanceCancelAllOrders(@RequestBody(required = false) Map<String, Object> request) {
        try {
            return restClient.cancelAll(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("position-info")
    public List<BinanceUsdmPosition> getPosition(@RequestParam Map<String, Object> request) throws IOException, URISyntaxException {
        try {
            return restClient.getBinanceUsdmFuturesPositionRisk(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PostMapping("change-initial-leverage")
    public JsonNode changeInitialLeverage(@RequestBody Map<String, Object> queryParams) {
        try {
            return (restClient.changeInitialLeverage(queryParams));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-all-open-orders")
    public JsonNode getAllOpenOrders(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getAllOpenOrders(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("get-account-balance")
    public List<BinanceUsdmAccountBalance> getAccountBalance(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getAccountBalance(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("batch-orders")
    public JsonNode postBatchOrders(@RequestBody Map<String, Object> queryParams) {
        try {
            return restClient.postBatchOrders(queryParams);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PostMapping("cancel-batch-orders")
    public JsonNode cancelBatchOrders(@RequestBody Map<String,Object>queryParams) {
        try {
            return restClient.cancelBatchOrders(queryParams);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("all-orders")
    public JsonNode getAllOrders(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getAllOrders(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("amend-order")
    public JsonNode amendOrder(@RequestBody Map<String, Object> request) throws IOException, URISyntaxException {
        try {
            return restClient.amendOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("batch-amend-orders")
    public JsonNode batchAmendOrders(@RequestBody Map<String, Object> request) throws IOException, URISyntaxException {
        try {
            return restClient.batchAmendOrders(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
