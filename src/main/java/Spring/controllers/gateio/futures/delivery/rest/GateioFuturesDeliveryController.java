package Spring.controllers.gateio.futures.delivery.rest;
import Spring.client.gateio.GateioHttpClient;
import Spring.model.pojo.gateio.futures.GateioFuturesContract;
import Spring.model.pojo.gateio.futures.GateioFuturesOrder;
import Spring.model.rest.interfaces.*;
import Spring.service.external.gateio.futures.delivery.rest.GateioFuturesDeliveryRest;
import Spring.service.user.KeyService;
import Spring.utils.constants.gateio.GateioConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("api/gateio/futures/delivery")
public class GateioFuturesDeliveryController {
    private KeyService keyService;

    private  final GateioFuturesDeliveryRest gateioHttpClient;
    public GateioFuturesDeliveryController() {
        this.gateioHttpClient = new GateioFuturesDeliveryRest(new GateioHttpClient(GateioConstants.GATEIO_BASE_URL, keyService));
    }

    private boolean checkValidSettleCurrency(String currency){
        return currency.equals("usdt") || currency.equals("btc");
    }

    @GetMapping("get-contracts")
    public List<Contracts> getDeliveryFuturesContracts(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try{
            return gateioHttpClient.getDeliveryFuturesTickers(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getSingleContract")
    public GateioFuturesContract getSingleDeliveryFuturesContract(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try{
            return gateioHttpClient.getSingleDeliveryFuturesContract(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-orderbook")
    public OrderBook getDeliveryFuturesOrderbook(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }

        try{
            return gateioHttpClient.getDeliveryFuturesOrderbook(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("get-trades")
    public List<RecentTrades> getDeliveryFuturesTrades(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }

        try {
            return gateioHttpClient.getDeliveryFuturesTrades(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-candlesticks")
    public List<CandleStick> getDeliveryFuturesCandlesticks(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try {
            return gateioHttpClient.getDeliveryFuturesCandlesticks(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-funding-rate")
    public List<FundingRate> getPerpetualFuturesFundingRate(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try{
            return gateioHttpClient.getDeliveryFuturesFundingRate(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-open-interest")
    public List<OpenInterest> getDeliveryFuturesOpenInterest(@RequestParam Map<String, Object> request) {
        try {
            return gateioHttpClient.getDeliveryFuturesOpenInterest(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("postSingleOrder")
    public GateioFuturesOrder postSingleDeliveryFuturesOrder(@RequestBody Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }

        try {
            return gateioHttpClient.postSingleDeliveryFuturesOrder(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getAllOrders")
    public List<GateioFuturesOrder> getAllDeliveryFuturesOrders(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        if (!(request.get("status").equals("open") || request.get("status").equals("finished"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Status");
        }

        try {
            return gateioHttpClient.getAllDeliveryFuturesOrders(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("getSingleOrder")
    public GateioFuturesOrder getSingleDeliveryFuturesOrder(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try{
            return gateioHttpClient.getSingleDeliveryFuturesOrder(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("amendSingleOrder")
    public GateioFuturesOrder amendSingleDeliveryFuturesOrder(@RequestBody Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try {
            return gateioHttpClient.amendSingleDeliveryFuturesOrder(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
