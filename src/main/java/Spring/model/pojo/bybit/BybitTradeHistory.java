package Spring.model.pojo.bybit;

import lombok.Data;

@Data
public class BybitTradeHistory {
    private String symbol;
    private String orderType;
    private String underlyingPrice;
    private String orderLinkId;
    private String side;
    private String indexPrice;
    private String orderId;
    private String stopOrderType;
    private String leavesQty;
    private String execTime;
    private String feeCurrency;
    private boolean isMaker;
    private String execFee;
    private String feeRate;
    private String execId;
    private String tradeIv;
    private String blockTradeId;
    private String markPrice;
    private String execPrice;
    private String markIv;
    private String orderQty;
    private String orderPrice;
    private String execValue;
    private String execType;
    private String execQty;
    private String closedSize;
    private long seq;
}
