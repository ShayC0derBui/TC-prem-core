package Spring.model.pojo.binance.futures.usdm;

import lombok.Data;

@Data
public class BinanceUsdmPosition {
    private String symbol;
    private String positionSide;
    private String positionAmt;
    private String entryPrice;
    private String markPrice;
    private String leverage;
    private String notional;
    private String liquidationPrice;
    private String unRealizedProfit;
    private String marginType;
    private Boolean isAutoAddMargin;
    private Long updateTime;
}
