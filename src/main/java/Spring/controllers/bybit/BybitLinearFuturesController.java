package Spring.controllers.bybit;

import Spring.client.bybit.BybitHttpClient;
import Spring.model.pojo.bybit.futures.perpetual.*;
import Spring.model.rest.interfaces.*;
import Spring.service.external.bybit.futures.perpetual.rest.BybitLinearRest;
import Spring.service.user.KeyService;
import Spring.utils.constants.bybit.BybitConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
@RequestMapping("api/bybit/futures/linear")
public class BybitLinearFuturesController {
    private final BybitLinearRest restClient;

//    private final BybitLinearRest restClient = new BybitLinearRest(new BybitHttpClient(BybitConstants.TESTNET_BASE_URL, keyService));
    @Autowired
    public BybitLinearFuturesController(KeyService keyService,ObjectMapper objectMapper) {
        this.restClient = new BybitLinearRest(new BybitHttpClient(BybitConstants.BASE_URL, keyService),objectMapper);
    }
    @GetMapping("/get-contracts")
    public List<Contracts> getLinearBybitPerpetualFutureSymbols(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getLinearBybitPerpetualFuturesSymbols(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-funding-rate")
    public List<FundingRate> getLinearBybitPerpetualFuturesFundingHistory(@RequestParam Map<String, Object> request) {
        try {
            
            return restClient.getLinearBybitFundingHistory(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-trades")
    public List<RecentTrades> getLinearBybitPerpetualFuturesPublicTradingHistory(@RequestParam Map<String, Object> request) {
        try {
            
            return restClient.getLinearBybitPublicTradingHistory(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-open-interest")
    public List<OpenInterest> getLinearBybitPerpetualFuturesOpenInterest(@RequestParam Map<String, Object> request) {
        try {
            
            return restClient.getLinearBybitOpenInterest(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-orderbook")
    public OrderBook getLinearBybitPerpetualFuturesOpenInterestHistory(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getLinearBybitOrderBook(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-open-orders")
    public JsonNode getLinearBybitPerpetualFuturesOpenOrders(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getLinearBybitOpenOrders(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping("/get-order-history")
    public JsonNode getLinearBybitPerpetualFuturesOrderHistory(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getLinearBybitOrderHistory(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/get-trade-history")
    public JsonNode getLinearBybitPerpetualFuturesTradeHistory(@RequestParam Map<String, Object> request) {
        try {

            return restClient.getLinearBybitTradeHistory(request);
        } catch (IOException | URISyntaxException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-candlesticks")
    public List<CandleStick> getLinearBybitPerpetualFuturesKline(@RequestParam Map<String, Object> request) {
        try {
            
            return restClient.getLinearBybitFuturesKline(request);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-instrument-info")
    public JsonNode getLinearBybitPerpetualFuturesInstrumentsInfo(@RequestParam Map<String, Object> request) {
        try {
            
            return restClient.getLinearBybitPerpetualFuturesInstrumentsInfo(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-position-info")
    public BybitGenericResponse<BybitPerpetualFuturesPositionInfo> getLinearBybitPerpetualFuturesPositionInfo(@RequestParam Map<String, Object> request) {
        try {
            
            return restClient.getLinearBybitPerpetualFuturesPositionInfo(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/create-order")
    public JsonNode postLinearBybitCreateOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.postLinearBybitCreateOrder(request);
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @PostMapping("/cancel-order")
    public JsonNode postLinearBybitCancelOrder(
            @RequestBody Map<String, Object> request) {
        try {
            return restClient.postLinearBybitCancelOrder(request);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/amend-order")
    public JsonNode postLinearBybitAmendOrder(
            @RequestBody Map<String, Object> request) {
        try {
            return restClient.postLinearBybitAmendOrder(request);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/cancel-all-orders")
    public JsonNode postLinearBybitCancelAll(@RequestBody(required = false) Map<String, Object> request) {
        try {
            return restClient.postLinearBybitCancelAll(request);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/position-info")
    public JsonNode getLinearBybitPositionInfo(@RequestParam Map<String, Object> request) {
        try {
            
            return restClient.getLinearBybitPosition(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-wallet-balance")
    public JsonNode getBybitWalletBalance(@RequestParam Map<String, Object> request) {
        try {
            System.out.println("Request: " + request);
            return restClient.getBybitWalletBalance(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-sub-apikeys")
    public JsonNode getSubApiKeys(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getSubApiKeys(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-api-key-info")
    public JsonNode getApiKeyInfo(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getApiKeyInfo(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/get-risk-limit")
    public JsonNode getRiskLimit(@RequestParam Map<String, Object> request) {
        try {
            return restClient.getRiskLimit(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/set-leverage")
    public JsonNode getLeverage(@RequestBody Map<String, Object> request) {
        try {
            return restClient.setLeverage(request);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/set-risk-limit")
    public JsonNode setRiskLimit(@RequestBody Map<String, Object> request) {
        try {
            return restClient.setRiskLimit(request);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/batch-create-order")
    public JsonNode createBatchOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.createBatchOrder(request);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/batch-cancel-order")
    public JsonNode cancelBatchOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.cancelBatchOrder(request);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/batch-ammed-order")
    public JsonNode ammedBatchOrder(@RequestBody Map<String, Object> request) {
        try {
            return restClient.ammendBatchOrder(request);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
