package Spring.controllers.binance;

import Spring.client.binance.BinanceHttpClient;
import Spring.model.pojo.binance.BinanceFutures24HrTickerPriceChange;
import Spring.model.pojo.binance.futures.coinm.*;
import Spring.model.pojo.binance.futures.usdm.BatchCancelOrderRequest;
import Spring.model.pojo.binance.futures.usdm.BatchOrderRequest;
import Spring.model.pojo.binance.futures.usdm.BinanceUsdmChangeInitialLeverage;
import Spring.model.rest.interfaces.*;
import Spring.service.external.binance.futures.coinm.rest.BinanceFuturesCoinmRest;
import Spring.service.user.KeyService;
import Spring.utils.constants.binance.BinanceConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/binance/futures/coinm")
public class BinanceCoinmFuturesController {

    private final BinanceFuturesCoinmRest restClient;
    Gson gson = new Gson();

    @Autowired
    public BinanceCoinmFuturesController(KeyService keyService, ObjectMapper obj) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.restClient = new BinanceFuturesCoinmRest(new BinanceHttpClient(BinanceConstants.COINM_BASE_URL, keyService), obj, keyService);
    }

    @GetMapping("get-contracts")
    public List<Contracts> getFuturesCoinmContracts(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesContracts();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-orderbook")
    public OrderBook getFuturesCoinmOrderBook(@RequestParam Map<String, Object> request) {
        try {
            return (OrderBook) restClient.getBinanceCoinmFuturesOrderBook(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-trades")
    public List<RecentTrades> getFuturesCoinmTrades(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesTrades(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-candlesticks")
    public List<CandleStick> getFuturesCoinmCandlesticks(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesCandlesticks(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getPremiumIndex")
    public List<BinanceCoinmFuturesPremiumIndex> getFuturesCoinmPremiumIndex() {
        try {
            return restClient.getBinanceCoinmFuturesPremiumIndex();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-funding-rate")
    public JsonNode getFuturesCoinmFundingRateHistory(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesFundingRateHistory(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-historical-trades")
    public List<RecentTrades> getFuturesCoinmHistoricalTrades(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesHistoricalTrades(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get24HrTickerPriceChange")
    public List<BinanceFutures24HrTickerPriceChange> getFuturesCoinm24HrTickerPriceChange() {
        try {
            return restClient.getBinanceCoinmFutures24HrTickerPriceChange();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-open-interest")
    public JsonNode getFuturesCoinmOpenInterest(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesOpenInterest(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getTopLongShortAccountRatio")
    public List<BinanceCoinmFuturesTopLongShortRatio> getTopLongShortAccountRatio(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesTopLongShortAccountRatio(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getTopLongShortPositionRatio")
    public List<BinanceCoinmFuturesTopLongShortRatio> getTopLongShortPositionRatio(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesTopLongShortPositionRatio(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("position-info")
    public JsonNode getFuturesCoinmPosition(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getBinanceCoinmFuturesPosition(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    /*@GetMapping("position-info")
    public List<BinanceUsdmPosition> getPosition(@RequestParam Map<String, Object> request) throws IOException, URISyntaxException {
        try {
            return restClient.getBinanceUsdmFuturesPositionRisk(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }*/

    @PostMapping("change-initial-leverage")
    public JsonNode postBinanceChangeInitialLeverage(@RequestBody Map<String, Object> queryParams) {
        try {
            return restClient.postBinanceCoinmChangeInitialLeverage(queryParams);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // get all orders
    @GetMapping("all-orders")
    public JsonNode getAllOrders(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getAllOrders(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // single order
    @PostMapping("create-order")
    public JsonNode createOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.createOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // batch create order
    @PostMapping("batch-create-order")
    public JsonNode batchCreateOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.batchCreateOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // amend order
    @PostMapping("amend-order")
    public JsonNode amendOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.amendOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // batch amend order
    @PostMapping("batch-amend-orders")
    public JsonNode batchAmendOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.batchAmendOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // cancel order
    @PostMapping("cancel-order")
    public JsonNode cancelOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.cancelOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // cancel batch order
    @PostMapping("cancel-batch-order")
    public JsonNode cancelBatchOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.cancelBatchOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // get query current open order
    @GetMapping("open-order")
    public JsonNode queryCurrentOpenOrder(@RequestParam Map<String, Object> request) {
        try {
            return restClient.queryCurrentOpenOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    // get all open orders
    @GetMapping("all-open-order")
    public JsonNode queryAllOpenOrder(@RequestParam Map<String, Object> request) {
        try {
            return restClient.queryAllOpenOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    // cancel all orders
    @PostMapping("cancel-all-orders")
    public JsonNode cancelAllOrders(@RequestBody Map<String, Object> request) {
        try {
            return restClient.cancelAllOrders(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
