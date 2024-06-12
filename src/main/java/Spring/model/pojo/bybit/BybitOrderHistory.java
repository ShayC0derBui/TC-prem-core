package Spring.model.pojo.bybit;

import lombok.Data;

@Data
public class BybitOrderHistory {
    private String orderId;
    private String orderLinkId;
    private String blockTradeId;
    private String symbol;
    private String price;
    private String qty;
    private String side;
    private String isLeverage;
    private int positionIdx;
    private String orderStatus;
    private String cancelType;
    private String rejectReason;
    private String avgPrice;
    private String leavesQty;
    private String leavesValue;
    private String cumExecQty;
    private String cumExecValue;
    private String cumExecFee;
    private String timeInForce;
    private String orderType;
    private String stopOrderType;
    private String orderIv;
    private String triggerPrice;
    private String takeProfit;
    private String stopLoss;
    private String tpTriggerBy;
    private String slTriggerBy;
    private int triggerDirection;
    private String triggerBy;
    private String lastPriceOnCreated;
    private boolean reduceOnly;
    private boolean closeOnTrigger;
    private String smpType;
    private int smpGroup;
    private String smpOrderId;
    private String tpslMode;
    private String tpLimitPrice;
    private String slLimitPrice;
    private String placeType;
    private String createdTime;
    private String updatedTime;
}