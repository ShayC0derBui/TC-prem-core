package Spring.model.pojo.binance.spot;
import lombok.Data;

@Data
public class OpenOrders {
    private String symbol;
    private long orderId;
    private long orderListId;
    private String clientOrderId;
    private String price;
    private String origQty;
    private String executedQty;
    private String cummulativeQuoteQty;
    private String status;
    private String timeInForce;
    private String type;
    private String side;
    private String stopPrice;
    private String icebergQty;
    private long time;
    private long updateTime;
    private boolean isWorking;
    private long workingTime;
    private String origQuoteOrderQty;
    private String selfTradePreventionMode;
}
