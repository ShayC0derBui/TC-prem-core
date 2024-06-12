package Spring.controllers.bybit;

import Spring.client.bybit.BybitHttpClient;
import Spring.model.pojo.bybit.BybitOpenOrders;
import Spring.model.pojo.bybit.BybitOrderHistory;
import Spring.model.pojo.bybit.BybitPosition;
import Spring.model.pojo.bybit.BybitTradeHistory;
import Spring.model.pojo.bybit.futures.perpetual.*;
import Spring.model.rest.interfaces.*;
import Spring.service.external.bybit.futures.perpetual.rest.BybitInverseRest;
import Spring.service.user.KeyService;
import Spring.utils.constants.bybit.BybitConstants;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/bybit/futures/inverse")
public class BybitInverseFuturesController {

    private final BybitInverseRest restClient;

    @Autowired
    public BybitInverseFuturesController(KeyService keyService) {
        this.restClient = new BybitInverseRest(new BybitHttpClient(BybitConstants.BASE_URL,keyService));
    }

    @GetMapping("/get-contracts")
    public List<Contracts> getInverseBybitPerpetualFutureSymbols(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitPerpetualFuturesSymbols(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-funding-rate")
    public List<FundingRate> getInverseBybitPerpetualFuturesFundingHistory(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitFundingHistory(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping("/get-open-orders")
    public JsonNode getInverseBybitPerpetualFuturesOpenOrders(@RequestParam Map<String, Object> request) {
        try {

            return restClient.getInverseBybitOpenOrders(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-order-history")
    public JsonNode getInverseBybitPerpetualFuturesOrderHistory(@RequestParam Map<String, Object> request) {
        try {

            return restClient.getInverseBybitOrderHistory(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-trade-history")
    public JsonNode getInverseBybitPerpetualFuturesTradeHistory(@RequestParam Map<String, Object> request) {
        try {

            return restClient.getInverseBybitTradeHistory(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-trades")
    public List<RecentTrades> getInverseBybitPerpetualFuturesPublicTradingHistory(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitPublicTradingHistory(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-open-interest")
    public List<OpenInterest> getInverseBybitPerpetualFuturesOpenInterest(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitOpenInterest(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-orderbook")
    public OrderBook getInverseBybitPerpetualFuturesOpenInterestHistory(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitOrderBook(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-candlesticks")
    public List<CandleStick> getInverseBybitPerpetualFuturesKline(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitFuturesKline(request);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-instrument-info")
    public BybitGenericResponse<BybitPerpetualFuturesInstrumentInfo> getInverseBybitPerpetualFuturesInstrumentsInfo(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitPerpetualFuturesInstrumentsInfo(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-position-info")
    public BybitGenericResponse<BybitPerpetualFuturesPositionInfo> getInverseBybitPerpetualFuturesPositionInfo(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitPerpetualFuturesPositionInfo(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/create-order")
    public BybitCreateOrder postInverseBybitCreateOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.postInverseBybitCreateOrder(request);
        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/cancel-order")
    public ByBitCancelOrder postInverseBybitCancelOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.postInverseBybitCancelOrder(request);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/amend-order")
    public BybitAmendOrder postInverseBybitAmendOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.postInverseBybitAmendOrder(request);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/cancel-all-orders")
    public BybitGenericResponse<ByBitCancelOrder> postInverseBybitCancelAll(@RequestBody(required = false) Map<String, Object> request) {
        try {
            return restClient.postInverseBybitCancelAll(request);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/position-info")
    public List<BybitPosition> getInverseBybitPositionInfo(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getInverseBybitPosition(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


}
