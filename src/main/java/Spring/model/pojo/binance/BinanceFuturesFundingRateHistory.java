package Spring.model.pojo.binance;

import lombok.Data;

@Data
public class BinanceFuturesFundingRateHistory {
    private String symbol;
    private String fundingRate;
    private long fundingTime;
}

