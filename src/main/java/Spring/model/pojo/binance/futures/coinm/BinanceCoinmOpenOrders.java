package Spring.model.pojo.binance.futures.coinm;

import lombok.Data;

/*pojo:
* {
    "avgPrice": "0.0",
    "clientOrderId": "abc",
    "cumBase": "0",
    "executedQty": "0",
    "orderId": 1917641,
    "origQty": "0.40",
    "origType": "TRAILING_STOP_MARKET",
    "price": "0",
    "reduceOnly": false,
    "side": "BUY",
    "positionSide": "SHORT",
    "status": "NEW",
    "stopPrice": "9300",                // please ignore when order type is TRAILING_STOP_MARKET
    "closePosition": false,             // if Close-All
    "symbol": "BTCUSD_200925",
    "time": 1579276756075,              // order time
    "timeInForce": "GTC",
    "type": "TRAILING_STOP_MARKET",
    "activatePrice": "9020",            // activation price, only return with TRAILING_STOP_MARKET order
    "priceRate": "0.3",                 // callback rate, only return with TRAILING_STOP_MARKET order
    "updateTime": 1579276756075,        // update time
    "workingType": "CONTRACT_PRICE",
    "priceProtect": false               // if conditional order trigger is protected
  } */
@Data
public class BinanceCoinmOpenOrders {
    private String avgPrice;
    private String clientOrderId;
    private String cumBase;
    private String executedQty;
    private Long orderId;
    private String origQty;
    private String origType;
    private String price;
    private boolean reduceOnly;
    private String side;
    private String positionSide;
    private String status;
    private String stopPrice;
    private boolean closePosition;
    private String symbol;
    private Long time;
    private String timeInForce;
    private String type;
    private String activatePrice;
    private String priceRate;
    private Long updateTime;
    private String workingType;
    private boolean priceProtect;

}
