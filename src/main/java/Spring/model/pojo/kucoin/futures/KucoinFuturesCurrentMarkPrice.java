package Spring.model.pojo.kucoin.futures;

import lombok.Data;

@Data
public class KucoinFuturesCurrentMarkPrice {
    private String symbol;
    private long granularity;
    private long timePoint;
    private double value;
    private double indexPrice;
}
