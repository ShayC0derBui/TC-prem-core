//package Spring.service.external.binance.futures.coinm.rest;
//
//import Spring.utils.constants.binance.BinanceConstants;
//import Spring.client.binance.BinanceHttpClient;
//
//import java.io.IOException;
//import java.util.HashMap;
//
//public class CoinmFuturesTester {
//    public static void main(String[] args) throws IOException {
//        BinanceFuturesCoinmRest client = new BinanceFuturesCoinmRest(new BinanceHttpClient(BinanceConstants.TESTNET_FUTURE_URL));
//        try {
////            client.getBinanceCoinmFuturesContracts();
////            client.getBinanceCoinmFuturesOrderBook(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////                put("limit", "5");
////            }});
////            client.getBinanceCoinmFuturesTrades(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////                put("limit", "5");
////            }});
////            client.getBinanceCoinmFuturesCandlesticks(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////                put("interval", "1m");
////            }});
////            client.getBinanceCoinmFuturesPremiumIndex();
////            client.getBinanceCoinmFuturesOpenInterest(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////            }});
////            client.getBinanceCoinmFuturesFundingRateHistory(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////            }});
////            client.getBinanceCoinmFutures24HrTickerPriceChange();
//
//            //Will only work with COINM_BASE_URL
////            client.getBinanceCoinmFuturesTopLongShortAccountRatio(new HashMap<String, Object>(){{
////                put("pair", "BTCUSD");
////                put("interval", "5m");
////            }});
////            client.getBinanceCoinmFuturesTopLongShortPositionRatio(new HashMap<String, Object>(){{
////                put("pair", "BTCUSD");
////                put("interval", "1d");
////            }});
////
////            client.postBinanceCoinmCreateOrder(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////                put("side", "BUY");
////                put("type", "LIMIT");
////                put("timeInForce", "GTC");
////                put("quantity", 1.0);
////                put("price", 27000.0);
////            }});
//////            client.deleteBinanceCoinmCancelOrder(new HashMap<String, Object>(){{
//////                put("symbol", "BTCUSD_PERP");
//////                put("orderId", "543317769");
//////            }});
//////            client.deleteBinancecancelAllCoinm(new HashMap<String, Object>(){{
//////                put("symbol", "BTCUSD_PERP");
//////            }});
//////            client.putBinanceCoinmAmendOrder(new HashMap<String, Object>(){{
//////                put("symbol", "BTCUSD_PERP");
//////                put("orderId", "543000624");
//////                put("side", "BUY");
//////                put("quantity", 1.2);
//////                put("price", 3600.0);
//////            }});
////        } catch (Exception e) {
////            System.out.println(e.getMessage());
////        }
////    }
////}
////            client.deleteBinanceCoinmCancelOrder(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////                put("orderId", "543317769");
////            }});
////            client.deleteBinancecancelAllCoinm(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////            }});
////            client.putBinanceCoinmAmendOrder(new HashMap<String, Object>(){{
////                put("symbol", "BTCUSD_PERP");
////                put("orderId", "543000624");
////                put("side", "BUY");
////                put("quantity", 1.2);
////                put("price", 3600.0);
////            }});
//            client.getBinanceCoinmFuturesPosition(new HashMap<String,Object>());
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
