package Spring.model.pojo.kucoin.futures;

import lombok.Data;

@Data
public class KucoinFuturesSingleOrderDetails {
    private String id;
    private String symbol;
    private String type;
    private String side;
    private String price;
    private int size;
    private String value;
    private String dealValue;
    private int dealSize;
    private String stp;
    private String stop;
    private String stopPriceType;
    private boolean stopTriggered;
    private String stopPrice;
    private String timeInForce;
    private boolean postOnly;
    private boolean hidden;
    private boolean iceberg;
    private String leverage;
    private boolean forceHold;
    private boolean closeOrder;
    private String visibleSize;
    private String clientOid;
    private String remark;
    private String tags;
    private boolean isActive;
    private boolean cancelExist;
    private long createdAt;
    private long updatedAt;
    private long endAt;
    private long orderTime;
    private String settleCurrency;
    private String status;
    private String filledValue;
    private int filledSize;
    private boolean reduceOnly;
}
