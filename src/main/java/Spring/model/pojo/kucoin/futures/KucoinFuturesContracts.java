package Spring.model.pojo.kucoin.futures;

import lombok.Data;

@Data
public class KucoinFuturesContracts {
    private String symbol;
    private String rootSymbol;
    private String type;
    private String baseCurrency;
    private String quoteCurrency;
    private String settleCurrency;
    private int maxOrderQty;
    private double maxPrice;
    private int lotSize;
    private double tickSize;
    private double indexPriceTickSize;
    private double multiplier;
    private double initialMargin;
    private double maintainMargin;
    private int maxRiskLimit;
    private int minRiskLimit;
    private int riskStep;
    private double makerFeeRate;
    private double takerFeeRate;
    private double takerFixFee;
    private double makerFixFee;
    private boolean isDeleverage;
    private boolean isQuanto;
    private boolean isInverse;
    private String markMethod;
    private String fairMethod;
    private String fundingBaseSymbol;
    private String fundingQuoteSymbol;
    private String fundingRateSymbol;
    private String indexSymbol;
    private String settlementSymbol;
    private String status;
    private double fundingFeeRate;
    private double predictedFundingFeeRate;
    private long fundingRateGranularity;
    private String openInterest;
    private double turnoverOf24h;
    private double volumeOf24h;
    private double markPrice;
    private double indexPrice;
    private double lastTradePrice;
    private long nextFundingRateTime;
    private int maxLeverage;
    private String premiumsSymbol1M;
    private String premiumsSymbol8H;
    private String fundingBaseSymbol1M;
    private String fundingQuoteSymbol1M;
    private double lowPrice;
    private double highPrice;
    private double priceChgPct;
    private double priceChg;
}
