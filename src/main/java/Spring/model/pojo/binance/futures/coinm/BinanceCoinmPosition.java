package Spring.model.pojo.binance.futures.coinm;

import lombok.Data;

@Data
public class BinanceCoinmPosition {
    private String symbol;
    private String positionAmt;
    private String entryPrice;
    private String breakEvenPrice;
    private String markPrice;
    private String unRealizedProfit;
    private String liquidationPrice;
    private String leverage;
    private String maxQty;
    private String marginType;
    private String isolatedMargin;
    private Boolean isAutoAddMargin;
    private String positionSide;
    private Long updateTime;
}
