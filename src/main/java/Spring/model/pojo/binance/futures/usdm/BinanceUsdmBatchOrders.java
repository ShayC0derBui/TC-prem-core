package Spring.model.pojo.binance.futures.usdm;

import lombok.Data;
@Data
public class BinanceUsdmBatchOrders {
    private String clientOrderId;
    private String cumQty;
    private String cumQuote;
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
    private String timeInForce;
    private String type;
    private String origType;
    private String activatePrice;
    private String priceRate;
    private Long updateTime;
    private String workingType;
    private boolean priceProtect;
    private String priceMatch;
    private String selfTradePreventionMode;
    private Long goodTillDate;
}
