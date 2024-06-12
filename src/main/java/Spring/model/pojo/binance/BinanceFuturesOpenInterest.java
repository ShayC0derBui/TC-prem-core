package Spring.model.pojo.binance;

import lombok.Data;

@Data
public class BinanceFuturesOpenInterest {
    private String symbol;
    private String openInterest;
    private long time;
}
