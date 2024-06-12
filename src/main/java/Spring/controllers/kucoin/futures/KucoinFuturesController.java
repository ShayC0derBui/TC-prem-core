//package Spring.controllers.kucoin.futures;
//
//import Spring.client.kucoin.KucoinHttpClient;
//import Spring.model.pojo.kucoin.futures.*;
//import Spring.model.rest.interfaces.*;
//import Spring.service.external.kucoin.futures.KucoinFuturesRest;
//import Spring.service.user.KeyService;
//import Spring.utils.constants.kucoin.spot.KucoinConstants;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//import org.springframework.http.HttpStatus;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//@RestController
//@RequestMapping("api/kucoin/futures/both")
//public class KucoinFuturesController {
//    private KeyService keyService;
//
//    private KucoinFuturesRest kucoinFuturesRest;
//    public KucoinFuturesController(){
//        this.kucoinFuturesRest=new KucoinFuturesRest(new KucoinHttpClient(KucoinConstants.KUCOIN_BASE_URL,keyService));
//    }
//
//    @GetMapping("getContracts")
//    public List<Contracts> getKucoinFuturesContracts() throws ResponseStatusException {
//        try {
//            List<Contracts> kucoinFuturesSymbolsList = kucoinFuturesRest.getKucoinFuturesSymbolsList();
//            return kucoinFuturesSymbolsList;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("get-orderbook")
//    public OrderBook getKucoinFuturesOrderbook(@RequestParam Map<String, Object> request) throws ResponseStatusException {
//        try {
//            return kucoinFuturesRest.getKucoinFuturesOrderbook(request);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("getTicker")
//    public KucoinFuturesTicker getKucoinFuturesTicker(@RequestParam Map<String, Object> request) throws ResponseStatusException {
//        try {
//            return kucoinFuturesRest.getKucoinFuturesTicker(request);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("get-trade-history")
//    public List<RecentTrades> getKucoinFuturesTradeHistory(@RequestParam Map<String, Object> request) throws ResponseStatusException {
//        try {
//            return kucoinFuturesRest.getKucoinFuturesTradeHistory(request);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("get-premium-index")
//    public KucoinFuturesPremiumIndex getKucoinFuturesIndex(@RequestParam Map<String, Object> request) throws ResponseStatusException {
//        try {
//            return kucoinFuturesRest.getKucoinFuturesPremiumIndex(request);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("getMarkPrice")
//    public KucoinFuturesCurrentMarkPrice getKucoinFuturesMarkPrice(@RequestParam Map<String, Object> request) throws ResponseStatusException {
//        try {
//            KucoinFuturesCurrentMarkPrice kucoinFuturesMarkPrice = kucoinFuturesRest.getKucoinFuturesMarkPrice(request);
//            return kucoinFuturesMarkPrice;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("getPosition")
//    public KucoinFuturesPosition getKucoinFuturesPosition(@RequestParam Map<String, Object> request) throws ResponseStatusException {
//        try {
//            KucoinFuturesPosition kucoinFuturesPosition = (KucoinFuturesPosition) kucoinFuturesRest.getKucoinFuturesPosition(request);
//            return kucoinFuturesPosition;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("getFills")
//    public KucoinFuturesFills getKucoinFuturesFills(@RequestParam Map<String, Object> request) throws ResponseStatusException {
//        try {
//
//            // Call the restClient method with the extracted parameters
//            KucoinFuturesFills kucoinFuturesFills = kucoinFuturesRest.getKucoinFuturesFills(request);
//            return kucoinFuturesFills;
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("get-candlesticks")
//    public List<CandleStick> getKucoinFuturesCandlesticks(@RequestParam Map<String, Object> request) throws ResponseStatusException {
//        try {
//            return kucoinFuturesRest.getKucoinFuturesKline(request);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("get-trades")
//    public List<OpenInterest> getKucoinFuturesRecentTrades(@RequestParam Map<String, Object> request) throws IOException {
//        try{
//            return kucoinFuturesRest.getKucoinFuturesOpenInterest(request);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//    @GetMapping("get-funding-rate")
//    public FundingRate getKucoinFuturesFundingRate(@RequestParam Map<String, Object> request) throws IOException {
//        try{
//            return kucoinFuturesRest.getKucoinFuturesFundingRate(request);
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
//        }
//    }
//
//}