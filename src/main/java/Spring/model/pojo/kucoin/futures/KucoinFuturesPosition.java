package Spring.model.pojo.kucoin.futures;

import lombok.Data;

@Data
public class KucoinFuturesPosition {
    private String id;
    private String symbol;
    private boolean autoDeposit;
    private double maintMarginReq;
    private int riskLimit;
    private double realLeverage;
    private boolean crossMode;
    private double delevPercentage;
    private long openingTimestamp;
    private long currentTimestamp;
    private int currentQty;
    private double currentCost;
    private double currentComm;
    private double unrealisedCost;
    private double realisedGrossCost;
    private double realisedCost;
    private boolean isOpen;
    private double markPrice;
    private double markValue;
    private double posCost;
    private double posCross;
    private double posInit;
    private double posComm;
    private double posLoss;
    private double posMargin;
    private double posMaint;
    private double maintMargin;
    private double realisedGrossPnl;
    private double realisedPnl;
    private double unrealisedPnl;
    private double unrealisedPnlPcnt;
    private double unrealisedRoePcnt;
    private double avgEntryPrice;
    private double liquidationPrice;
    private double bankruptPrice;
    private String settleCurrency;
    private boolean isInverse;
    private long userId;
}
