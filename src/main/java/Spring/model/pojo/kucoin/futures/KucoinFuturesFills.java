package Spring.model.pojo.kucoin.futures;

import lombok.Data;

@Data
public class KucoinFuturesFills {
    private String symbol;
    private String tradeId;
    private String orderId;
    private String side;
    private String liquidity;
    private boolean forceTaker;
    private String price;
    private int size;
    private String value;
    private String feeRate;
    private String fixFee;
    private String feeCurrency;
    private String stop;
    private String fee;
    private String orderType;
    private String tradeType;
    private long createdAt;
    private String settleCurrency;
    private String openFeePay;
    private String closeFeePay;
    private long tradeTime;
}

