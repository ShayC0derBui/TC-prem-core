package Spring.model.pojo.bybit.futures.perpetual;

import lombok.Data;

@Data
public class BybitPerpetualFuturesPositionInfo {
    private int positionIdx;
    private int riskId;
    private String riskLimitValue;
    private String symbol;
    private String side;
    private String size;
    private String avgPrice;
    private String positionValue;
    private int tradeMode;
    private String positionStatus;
    private int autoAddMargin;
    private int adlRankIndicator;
    private String leverage;
    private String positionBalance;
    private String markPrice;
    private String liqPrice;
    private String bustPrice;
    private String positionMM;
    private String positionIM;
    private String tpslMode;
    private String takeProfit;
    private String stopLoss;
    private String trailingStop;
    private String unrealisedPnl;
    private String cumRealisedPnl;
    private String createdTime;
    private String updatedTime;
}

