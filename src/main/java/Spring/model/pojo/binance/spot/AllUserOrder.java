package Spring.model.pojo.binance.spot;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AllUserOrder {
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
    private String origQuoteOrderQty;
    private long workingTime;
    private String selfTradePreventionMode;
}
