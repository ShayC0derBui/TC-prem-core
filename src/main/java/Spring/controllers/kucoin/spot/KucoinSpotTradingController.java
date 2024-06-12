//package Spring.controllers.kucoin.spot;
//
//import Spring.client.kucoin.KucoinHttpClient;
//import Spring.model.rest.interfaces.CandleStick;
//import Spring.model.rest.interfaces.Contracts;
//import Spring.model.rest.interfaces.OrderBook;
//import Spring.model.rest.interfaces.RecentTrades;
//import Spring.service.external.kucoin.spot.restapi.KucoinSpotRestClient;
//import Spring.service.user.KeyService;
//import Spring.utils.constants.kucoin.spot.KucoinConstants;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("api/kucoin/spot")
//public class KucoinSpotTradingController {
//    private KeyService keyService;
//    private KucoinSpotRestClient restClient;
//
//    public KucoinSpotTradingController() {
////        KucoinHttpClient kucoinHttpClient = new KucoinHttpClient("https://api.kucoin.com");
////        this.restClient = new KucoinSpotRestClient(kucoinHttpClient);
//        this.restClient = new KucoinSpotRestClient(new KucoinHttpClient(KucoinConstants.KUCOIN_BASE_URL,keyService));
//    }
//
//    @GetMapping("/get-contracts")
//    public List<Contracts> getAllTicker() {
//        try {
//            return restClient.getAllTickers();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @DeleteMapping("/deleteSingleSpotOrderByOrderId")
//    public String deleteSingleSpotOrderByOrderId(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.deleteSingleSpotOrderByOrderId(request.get("orderId"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @DeleteMapping("/deleteSingeSpotOrderByClientOid")
//    public String deleteSingeSpotOrderByClientOid(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.deleteSingeSpotOrderByClientOid(request.get("clientOid"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getSingleSpotStop_OrderByOrderId")
//    public String getSingleSpotStop_OrderByOrderId(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getSingleSpotStop_OrderByOrderId(request.get("orderId"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getSingleSpotStop_OrderByClientOid")
//    public String getSingleSpotStop_OrderByClientOid(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getSingleSpotStop_OrderByClientOid(request.get("clientOid"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getLast24hAccountLedger")
//    public String getLast24hAccountLedger(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getLast24hAccountLedger(request.get("currency"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getLast24hDeposits")
//    public String getLast24hDeposits(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getLast24hDeposits(request.get("currency"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getLast24hFills")
//    public String getLast24hFills() {
//        try {
//            return restClient.getLast24hFills();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getAllAccounts")
//    public String getAllAccounts() {
//        try {
//            return restClient.getAllAccounts();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getAllSubAccounts")
//    public String getAllSubAccounts() {
//        try {
//            return restClient.getAllSubAccounts();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getAllSubAccountsAggregatedBalance")
//    public String getAllSubAccountsAggregatedBalance() {
//        try {
//            return restClient.getAllSubAccountsAggregatedBalance();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getAccountSummaryInfo")
//    public String getAccountSummaryInfo() {
//        try {
//            return restClient.getAccountSummaryInfo();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @GetMapping("/getTransferrableBalance")
//    public String getTransferrableBalance(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getTransferrableBalance(request.get("currency"), request.get("type"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getSingleAccount")
//    public String getSingleAccount(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getSingleAccount(request.get("accountId"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getSubAccountSpotApiList")
//    public String getSubAccountSpotApiList(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getSubAccountSpotApiList(request.get("subName"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getAllDepositAddress")
//    public String getAllDepositAddress(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getAllDepositAddress(request.get("currency"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getSingleOrderByOrderId")
//    public String getSingleOrderByOrderId(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getSingleOrderByOrderId(request.get("orderId"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getSingleOrderByClientOid")
//    public String getSingleOrderByClientOid(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getSingleOrderByClientOid(request.get("clientOid"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//    @PostMapping("/postSingleSpotOrder")
//    public String postSingleSpotOrder(@RequestBody Map<String, String> request) {
//        try {
//            return restClient.postSingleSpotOrder(request.get("symbol"), request.get("side"), request.get("type"), request.get("price"), request.get("size"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @PostMapping("/postSingleSpotStop_Order")
//    public String postSingleSpotStop_Order(@RequestBody Map<String, String> request) {
//        try {
//            return restClient.postSingleSpotStop_Order(request.get("symbol"), request.get("side"), request.get("type"), request.get("price"), request.get("size"), request.get("stopPrice"), request.get("stop"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getAllOrdersByFilters")
//    public String getAllOrdersByFilters(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getAllOrdersByFilters(request.get("status"), request.get("symbol"), request.get("side"), request.get("type"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @DeleteMapping("/deleteSingleSpotStop_Order")
//    public String deleteSingleSpotStop_Order(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.deleteSingleSpotStop_Order(request.get("orderId"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getSymbolsList")
//    public String getSymbolsList() {
//        try {
//            return restClient.getSymbolsList();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/getTicker")
//    public String getTicker(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.getTicker(request.get("symbol"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/get24hStats")
//    public String get24hStats(@RequestParam Map<String, String> request) {
//        try {
//            return restClient.get24hStats(request.get("symbol"));
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/get-orderbook")
//    public OrderBook getOrderBook(@RequestParam Map<String, Object> request) {
//        try {
//            return restClient.getOrderBook(request);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/get-candlestick")
//    public List<CandleStick> getKucoinCandlesticks(@RequestParam Map<String, Object> request){
//        try {
//            return restClient.getKucoinSpotCandlestick(request);
//        } catch (IOException | URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @GetMapping("/get-trades")
//    public List<RecentTrades> getKucoinRecentTrades(@RequestParam Map<String, Object> request){
//        try {
//            return restClient.getKucoinRecentSpotTrades(request);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//}