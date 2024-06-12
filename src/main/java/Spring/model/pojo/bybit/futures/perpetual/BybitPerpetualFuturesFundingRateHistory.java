package Spring.model.pojo.bybit.futures.perpetual;

import lombok.Data;

@Data
public class BybitPerpetualFuturesFundingRateHistory {
    private String symbol;
    private String fundingRate;
    private String fundingRateTimestamp;
}
