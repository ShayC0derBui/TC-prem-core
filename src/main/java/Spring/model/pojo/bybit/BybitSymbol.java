package Spring.model.pojo.bybit;

import lombok.Data;

@Data
public class BybitSymbol {
    private String symbol;
    private String lastPrice;
    private String indexPrice;
    private String markPrice;
    private String prevPrice24h;
    private String price24hPcnt;
    private String highPrice24h;
    private String lowPrice24h;
    private String prevPrice1h;
    private String openInterest;
    private String openInterestValue;
    private String turnover24h;
    private String volume24h;
    private String fundingRate;
    private String nextFundingTime;
    private String predictedDeliveryPrice;
    private String basisRate;
    private String deliveryFeeRate;
    private String deliveryTime;
    private String ask1Size;
    private String bid1Price;
    private String ask1Price;
    private String bid1Size;
    private String basis;
}