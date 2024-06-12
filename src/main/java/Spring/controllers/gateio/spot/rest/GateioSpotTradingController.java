package Spring.controllers.gateio.spot.rest;


import Spring.model.pojo.gateio.spot.*;
import Spring.model.rest.interfaces.CandleStick;
import Spring.model.rest.interfaces.Contracts;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.interfaces.RecentTrades;
import Spring.service.user.KeyService;
import Spring.utils.constants.gateio.GateioConstants;
import Spring.client.gateio.GateioHttpClient;
import Spring.service.external.gateio.spot.rest.GateioSpotRestClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/gateio/spot")
public class GateioSpotTradingController {
    private KeyService keyService;

    GateioSpotRestClient gateioHttpClient = new GateioSpotRestClient(new GateioHttpClient(GateioConstants.GATEIO_BASE_URL, keyService));

    public GateioSpotTradingController() throws IOException {
    }

    /**
     * todo: for all the endpoints with currencyPair param, implement a currencyPairValidator
     * currencyPairValidator will check whether input currencyPair is in the list of accepted currency pairs
     * todo: custom http response codes specific to the types of bad requests
     */


    @GetMapping("/get-contracts")
    public List<Contracts> getSpotContracts() {
        try {
            return gateioHttpClient.getSpotTickers();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-trades")
    public List<GateioSpotTrade> getSpotTrades(@RequestParam Map<String, Object> request) {
        try {
            return gateioHttpClient.getSpotTrades(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-currency-pairs")
    public List<GateioSpotCurrencyPair> getSpotCurrencyPairs() {
        try {
            return gateioHttpClient.getSpotCurrencyPairs();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-candlesticks")
    public List<CandleStick> getSpotCandleSticks(@RequestParam Map<String, Object> request) {
        try {
            return gateioHttpClient.getSpotCandlesticks(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-orderbook")
    public OrderBook getSpotOrderbook(@RequestParam Map<String, Object> request) {
        try {
            return gateioHttpClient.getSpotOrderbook(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("/get-accounts")
    public List<GateioSpotAccount> getSpotAccounts() {
        try {
            return gateioHttpClient.getSpotAccounts();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-open-orders")
    public List<GateioSpotOrderList> getSpotOpenOrders() {
        try {
            return gateioHttpClient.getSpotOpenOrders();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/post-create-order")
    public GateioSpotOrder postSpotCreateOrder(@RequestBody Map<String, Object> request) {
        if (!request.get("side").toString().equalsIgnoreCase("BUY") && !request.get("side").toString().equalsIgnoreCase("SELL")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "\"side\" is invalid: (should be buy or sell)");
        }
        try {
            return gateioHttpClient.postSpotCreateOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("/get-trading-history")
    public List<RecentTrades> getSpotTradingHistory(@RequestParam Map<String, Object> request) {
        try {
            return gateioHttpClient.getSpotTradingHistory(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @PatchMapping("/amend-single-order")
    public GateioSpotOrder amendSingleSpotOrder(@RequestBody Map<String, Object> request) {
        if (request.containsKey("amount") && request.containsKey("price")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "\"amount\" or \"price\" both cannot be passed with the request");
        }
        if (!request.containsKey("amount") && !request.containsKey("price")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "\"amount\" or \"price\" must be passed with the request");
        }

        try {
            return gateioHttpClient.amendSingleSpotOrder(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
