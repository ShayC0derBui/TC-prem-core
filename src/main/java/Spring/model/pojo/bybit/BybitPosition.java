package Spring.model.pojo.bybit;

import lombok.Data;

@Data
public class BybitPosition {
    private String symbol;
    private String side;
    private String size;
    private String avgPrice;
    private String markPrice;
    private String leverage;
    private String positionStatus;
    private String positionValue;

    // Additional information
    private String liqPrice;
    private String unrealisedPnl;
    private String tradeMode;
    private String autoAddMargin;
    private String createdTime;
    private String updatedTime;
    private String seqlongCross;
}
