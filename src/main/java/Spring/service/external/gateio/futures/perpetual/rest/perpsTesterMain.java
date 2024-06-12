//package Spring.service.external.gateio.futures.perpetual.rest;
//
//import Spring.database.repository.FundingRateRepository;
//import Spring.utils.constants.gateio.GateioConstants;
//import Spring.client.gateio.GateioHttpClient;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class perpsTesterMain {
//    public static void main(String[] args) throws IOException {
//        FundingRateRepository fundingRateRepository = null;
//        GateioFuturesPerpetualRest client = new GateioFuturesPerpetualRest(new GateioHttpClient(GateioConstants.GATEIO_BASE_URL));
//
//        try {
////            client.getPerpetualFuturesContracts(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////            }});
////            client.getSinglePerpetualFuturesContract(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////                put("contract", "BTC_USDT");
////            }});
////            client.getPerpetualFuturesOrderbook(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////                put("contract", "BTC_USDT");
////            }});
////            client.getPerpetualFuturesTrades(new HashMap<String, Object>(){{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT");
////            }});
//            client.getPerpetualFuturesFundingRate(new HashMap<String, Object>(){{
//                put("settleCurrency", "usdt");
//                put("contract", "BTC_USDT");
//            }});
////            client.getPerpetualFuturesOpenInterest(new HashMap<String, Object>(){{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT");
////            }});
////            client.getPerpetualFuturesCandlesticks(new HashMap<String, Object>(){{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT");
////            }});
////            client.getPerpetualFutureTickers(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////                put("contract", "BTC_USDT");
////            }});
////            System.out.println("Creating order");
////            client.postSinglePerpetualFuturesOrder(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////                put("contract", "ETH_USDT");
////                put("size", 1);
////                put("iceberg", 0);
////                put("price", "1000");
////                put("tif", "gtc");
////            }});
////            client.postSinglePerpetualFuturesOrder(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////                put("contract", "ETH_USDT");
////                put("size", 1);
////                put("iceberg", 0);
////                put("price", "1000");
////                put("tif", "gtc");
////            }});
////            Thread.sleep(3000);
////            System.out.println("Fetching all orders");
////            client.getAllPerpetualFuturesOrders(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////                put("contract", "ETH_USDT");
////                put("status", "open");
////            }});
////            Thread.sleep(3000);
////            System.out.println("Deleting all orders");
////            System.out.println("Fetching all orders");
////            client.getAllPerpetualFuturesOrders(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////                put("contract", "ETH_USDT");
////                put("status", "open");
////            }});
////            Scanner scanner = new Scanner(System.in);
////            client.getSinglePerpetualFuturesOrder(new HashMap<String, Object>(){{
////                put("settle", "usdt");
////                put("orderId", 1234562231);
////            }});
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
