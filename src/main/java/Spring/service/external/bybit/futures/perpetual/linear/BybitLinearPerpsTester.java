//package Spring.service.external.bybit.futures.perpetual.linear;
//
//import Spring.service.external.bybit.futures.perpetual.rest.BybitLinearRest;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//public class BybitLinearPerpsTester {
//    private final BybitLinearRest client ;
//
//    public BybitLinearPerpsTester(BybitLinearRest client) {
//        this.client = client;
//    }
//
//    public static void main(String[] args) throws IOException {
//        try {
//              client.getBybitPerpetualFuturesSymbols(Category.linear);
////            client.getBybitFundingHistory(Category.linear, Symbol._BTCUSDT);
////            client.getBybitPublicTradingHistory(Category.linear, Symbol._ALPACAUSDT);
////            client.getBybitOpenInterest(Category.linear, Symbol._BTCUSDT, Interval._1d);
////            client.getBybitPerpetualFuturesInstrumentsInfo(Category.linear, null);
////            client.getBybitPerpetualFuturesPositionInfo(Category.linear, null);
////            client.postBybitCreateOrder(Category.linear,Symbol._BTCUSDT, OrderSide.Buy, OrderType.Limit, "1", "10000");
////            client.postBybitCancelOrder(Category.linear,Symbol._BTCUSDT, "8e6c1aa7-4000-43f0-83f4-4a2a94a8e9d7");
////            client.postBybitAmendOrder(Category.linear,Symbol._BTCUSDT, "7d8c60d0-87f0-4deb-a94d-b5d69baf4198", "1", "10001");
////            client.postBybitCancelAll(Category.linear,Symbol._BTCUSDT);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
