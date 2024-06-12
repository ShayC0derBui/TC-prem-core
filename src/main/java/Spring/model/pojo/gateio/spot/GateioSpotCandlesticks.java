package Spring.model.pojo.gateio.spot;

import lombok.Data;

@Data
public class GateioSpotCandlesticks {
    private String timestamp;
    private String quoteCurrencyTradingVolume;
    private String closePrice;
    private String highestPrice;
    private String lowestPrice;
    private String openPrice;
    private String baseCurrencyTradingAmount;
}