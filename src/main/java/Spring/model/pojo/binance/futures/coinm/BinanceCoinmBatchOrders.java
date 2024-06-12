package Spring.model.pojo.binance.futures.coinm;

import lombok.Data;

/*pojo:
*  {
        "clientOrderId": "testOrder",
        "cumQty": "0",
        "cumBase": "0",
        "executedQty": "0",
        "orderId": 22542179,
        "avgPrice": "0.0",
        "origQty": "10",
        "price": "0",
        "reduceOnly": false,
        "side": "BUY",
        "positionSide": "SHORT",
        "status": "NEW",
        "stopPrice": "9300",             // please ignore when order type is TRAILING_STOP_MARKET
        "symbol": "BTCUSD_200925",
        "pair": "BTCUSD",
        "timeInForce": "GTC",
        "type": "TRAILING_STOP_MARKET",
        "origType": "TRAILING_STOP_MARKET",
        "activatePrice": "9020",         // activation price, only return with TRAILING_STOP_MARKET order
        "priceRate": "0.3",              // callback rate, only return with TRAILING_STOP_MARKET order
        "updateTime": 1566818724722,
        "workingType": "CONTRACT_PRICE",
        "priceProtect": false            // if conditional order trigger is protected
    }
*  */
@Data
public class BinanceCoinmBatchOrders {
    private String clientOrderId;
    private String cumQty;
    private String cumBase;
    private String executedQty;
    private Long orderId;
    private String avgPrice;
    private String origQty;
    private String price;
    private boolean reduceOnly;
    private String side;
    private String positionSide;
    private String status;
    private String stopPrice;
    private String symbol;
    private String pair;
    private String timeInForce;
    private String type;
    private String origType;
    private String activatePrice;
    private String priceRate;
    private Long updateTime;
    private String workingType;
    private boolean priceProtect;
}
