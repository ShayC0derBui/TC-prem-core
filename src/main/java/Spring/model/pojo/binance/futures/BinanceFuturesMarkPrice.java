package Spring.model.pojo.binance.futures;

import lombok.Data;

@Data
public class BinanceFuturesMarkPrice {
    private String symbol;
    private String markPrice;
    private String indexPrice;
}
