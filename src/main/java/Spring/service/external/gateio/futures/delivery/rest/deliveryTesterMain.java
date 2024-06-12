//package Spring.service.external.gateio.futures.delivery.rest;
//
//import Spring.utils.constants.gateio.GateioConstants;
//import Spring.client.gateio.GateioHttpClient;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Scanner;
//
//public class deliveryTesterMain {
//    public static void main(String[] args) throws IOException {
//
//        GateioFuturesDeliveryRest client = new GateioFuturesDeliveryRest(new GateioHttpClient(GateioConstants.GATEIO_BASE_URL));
//
//        try {
//            client.getDeliveryFuturesContracts(new HashMap<String, Object>() {{
//                put("settleCurrency", "usdt");
//            }});
////            client.getSingleDeliveryFuturesContract(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20231229");
////            }});
////            client.getDeliveryFuturesOrderbook(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20231229");
////            }});
////            client.getDeliveryFuturesTrades(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20231229");
////            }});
////            client.getDeliveryFuturesCandlesticks(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20231229");
////            }});
////            client.getDeliveryFuturesTickers(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20231229");
////            }});
////            System.out.println("Creating order");
////            client.postSingleDeliveryFuturesOrder(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20230519");
////                put("size", 1);
////                put("iceberg", 0);
////                put("price", "1000");
////                put("tif", "gtc");
////            }});
//            client.postSingleDeliveryFuturesOrder(new HashMap<String, Object>() {{
//                put("settleCurrency", "usdt");
//                put("contract", "BTC_USDT_20231229");
//                put("size", 1);
//                put("iceberg", 0);
//                put("price", "30000000");
//                put("tif", "gtc");
//            }});
////            Thread.sleep(3000);
////            System.out.println("Fetching all orders");
////            client.getAllDeliveryFuturesOrders(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20230519");
////                put("status", "open");
////            }});
////            Thread.sleep(3000);
////            System.out.println("Deleting all orders");
////            client.cancelAllDeliveryFuturesMatchedOrders(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20231229");
////            }});
////            Thread.sleep(3000);
////            System.out.println("Fetching all orders");
////            client.getAllDeliveryFuturesOrders(new HashMap<String, Object>() {{
////                put("settleCurrency", "usdt");
////                put("contract", "BTC_USDT_20230519");
////                put("status", "open");
////            }});
////            Scanner scanner = new Scanner(System.in);
////            System.out.println("Please input the orderId below:");
////            String orderId = scanner.nextLine();
////            client.getSinglePerpetualFuturesOrder("usdt", orderId);
////            client.cancelSinglePerpetualFuturesOrder("usdt", "291200637191");
////            client.amendSinglePerpetualFuturesOrder("usdt", "291209236662", 2, "1010");
//
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//}
