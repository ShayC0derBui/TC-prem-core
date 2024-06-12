package Spring.controllers.gateio.futures.perpetual.rest;

import Spring.client.gateio.GateioHttpClient;
import Spring.model.pojo.gateio.futures.GateioFuturesContract;
import Spring.model.pojo.gateio.futures.GateioFuturesOrder;
import Spring.model.pojo.gateio.futures.GateioFuturesTickers;
import Spring.model.rest.adapters.gateio.futures.GateioCandleStickFuturesAdapter;
import Spring.model.rest.interfaces.*;
import Spring.service.external.gateio.futures.perpetual.rest.GateioFuturesPerpetualRest;

import Spring.service.user.KeyService;
import Spring.utils.constants.gateio.GateioConstants;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("api/gateio/futures/perpetual")
public class GateioFuturesPerpetualController {
    private KeyService keyService;
     private final GateioFuturesPerpetualRest gateioHttpClient = new GateioFuturesPerpetualRest(new GateioHttpClient(GateioConstants.GATEIO_BASE_URL, keyService));


    // We are displaying this in the frontend
    @GetMapping("get-contracts")
    public List<GateioFuturesTickers> getPerpetualFuturesTickers(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }

        try{
            return gateioHttpClient.getPerpetualFutureTickers(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @GetMapping("get-orderbook")
    public OrderBook getPerpetualFuturesOrderbook(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try{
            return gateioHttpClient.getPerpetualFuturesOrderbook(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-trades")
    public List<RecentTrades> getPerpetualFuturesTrades(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try {
            return gateioHttpClient.getPerpetualFuturesTrades(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-candlesticks")
    public List<GateioCandleStickFuturesAdapter> getPerpetualFuturesCandlesticks(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }

        try {
            return gateioHttpClient.getPerpetualFuturesCandlesticks(request);
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
            return gateioHttpClient.getPerpetualFuturesFundingRate(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("get-open-interest")
    public List<OpenInterest> getPerpetualFuturesOpenInterest(@RequestParam Map<String, Object> request) {
        try {
            return gateioHttpClient.getPerpetualFuturesOpenInterest(request);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("postSingleOrder")
    public GateioFuturesOrder postSinglePerpetualFuturesOrder(@RequestBody Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }

        try {
            return gateioHttpClient.postSinglePerpetualFuturesOrder(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("getAllOrders")
    public List<GateioFuturesOrder> getAllPerpetualFuturesOrders(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        if (!(request.get("status").equals("open") || request.get("status").equals("finished"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Status");
        }

        try {
            return gateioHttpClient.getAllPerpetualFuturesOrders(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping("getSingleOrder")
    public GateioFuturesOrder getSinglePerpetualFuturesOrder(@RequestParam Map<String, Object> request){
        if(!checkValidSettleCurrency((String) request.get("settleCurrency"))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Settle Currency");
        }
        try{
            return gateioHttpClient.getSinglePerpetualFuturesOrder(request);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    private boolean checkValidSettleCurrency(String currency){
        return currency.equals("usd") || currency.equals("usdt") || currency.equals("btc");
    }
}