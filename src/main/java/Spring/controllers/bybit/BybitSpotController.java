package Spring.controllers.bybit;

import Spring.client.bybit.BybitHttpClient;
import Spring.model.pojo.bybit.*;
import Spring.model.pojo.bybit.futures.perpetual.ByBitCancelOrder;
import Spring.model.pojo.bybit.futures.perpetual.BybitCreateOrder;
import Spring.model.pojo.bybit.futures.perpetual.BybitGenericResponse;
import Spring.model.pojo.bybit.futures.perpetual.BybitOrderBookGenericResponse;
import Spring.model.rest.interfaces.CandleStick;
import Spring.model.rest.interfaces.Contracts;
import Spring.model.rest.interfaces.OrderBook;
import Spring.service.external.bybit.spot.BybitSpotRest;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/bybit/spot")
public class BybitSpotController {
//    private final BybitSpotRest restClient = new BybitSpotRest(new BybitHttpClient(BybitConstants.TESTNET_BASE_URL, keyService));
    private final BybitSpotRest restClient;
    @Autowired
    public BybitSpotController(KeyService keyService) {
        this.restClient = new BybitSpotRest(new BybitHttpClient(BybitConstants.BASE_URL, keyService));
    }
    @GetMapping("/get-contracts")
    public List<Contracts> getBybitSpotTickers(@RequestParam Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.getBybitSpotTickers(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-orderbook")
    public OrderBook getBybitSpotOrderBook(@RequestParam Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.getBybitSpotOrderBook(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-candlesticks")
    public List<CandleStick> getBybitSpotKline(@RequestParam Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.getBybitSpotKline(request);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-open-orders")
    public JsonNode getBybitSpotOpenOrders(@RequestBody Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.getBybitSpotOpenOrders(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-order-history")
    public JsonNode getBybitSpotOrderHistory(@RequestBody Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.getBybitSpotOrderHistory(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-trade-history")
    public JsonNode getBybitSpotTradeHistory(@RequestBody Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.getBybitSpotTradeHistory(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-trades")
    public List<BybitRecentTrades> getBybitSpotRecentTrades(@RequestBody Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.getBybitSpotRecentTrades(request);
        } catch (IOException | URISyntaxException  e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/create-order")
    public BybitCreateOrder postBybitSpotCreateOrder(@RequestBody Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.postBybitSpotCreateOrder(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/cancel-order")
    public ByBitCancelOrder postBybitSpotCancelOrder(@RequestBody Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.postBybitSpotCancelOrder(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/cancel-all-orders")
    public List<ByBitCancelOrder> postBybitSpotCancelAllOrders(@RequestBody(required = false) Map<String, Object> request) {
        try {
            request.put("category","spot");
            return restClient.postBybitSpotCancelAll(request);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }


}
