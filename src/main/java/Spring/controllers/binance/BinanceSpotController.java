package Spring.controllers.binance;

import Spring.client.binance.BinanceHttpClient;
import Spring.model.pojo.binance.spot.AggregateTrades;
import Spring.model.pojo.binance.spot.AllUserOrder;
import Spring.model.pojo.binance.spot.OpenOrders;
import Spring.model.rest.interfaces.CandleStick;
import Spring.model.rest.interfaces.Contracts;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.interfaces.RecentTrades;
import Spring.service.external.binance.spot.rest.BinanceSpotRestClient;
import Spring.service.user.KeyService;
import Spring.utils.constants.binance.BinanceConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/binance/spot")
public class BinanceSpotController {
    private final BinanceSpotRestClient restClient;
    @Autowired
    public BinanceSpotController(KeyService keyService) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeySpecException {
        BinanceHttpClient httpClient = new BinanceHttpClient(BinanceConstants.BASE_URL,keyService);
        this.restClient = new BinanceSpotRestClient(httpClient);
    }

    @GetMapping("/get-contracts")
    public List<Contracts> getBybitSpotTickers(@RequestParam Map<String, Object> params) {
        try {
            return restClient.getBybitSpotTickers();
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-candlesticks")
    public List<CandleStick> getBybitCandlesticks(@RequestParam Map<String, Object> params) {
        try {
            return restClient.getSpotCandlesticks(params);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-trades")
    public List<RecentTrades> getBybitRecentTrades(@RequestParam Map<String, Object> params) {
        try {
            return restClient.getSpotRecentTrades(params);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-orderbook")
    public OrderBook getBybitOrderBook(@RequestParam Map<String, Object> params) {
        try {
            return restClient.getSpotOrderBook(params);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    @GetMapping("/get-aggregate-trades")
    public List<AggregateTrades> getSpotAggregateTrades(@RequestParam Map<String, Object> params) {
        try {
            return restClient.getSpotAggregateTrades(params);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-all-spot-orders")
    public List<AllUserOrder> getAllSpotOrders(@RequestParam Map<String, Object> params) {
        try {
            return restClient.getAllSpotOrders(params);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/get-open-orders")
    public List<OpenOrders> getOpenOrders(@RequestParam Map<String, Object> params) {
        try {
            return restClient.getOpenOrders(params);
        } catch (IOException | URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
