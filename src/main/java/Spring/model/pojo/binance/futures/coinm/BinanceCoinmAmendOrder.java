package Spring.model.pojo.binance.futures.coinm;
import lombok.Data;

@Data
public class BinanceCoinmAmendOrder {
    private String clientOrderId;
    private String cumQty;
    private String cumBase;
    private String pair;
    private String executedQty;
    private Long orderId;
    private String avgPrice;
    private String origQty;
    private String price;
    private Boolean reduceOnly;
    private String side;
    private String positionSide;
    private String status;
    private String stopPrice;
    private Boolean closePosition;
    private String symbol;
    private String timeInForce;
    private String type;
    private String origType;
    private Long updateTime;
    private String workingType;
    private Boolean priceProtect;
}
