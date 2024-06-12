package Spring.model.pojo.binance.futures.coinm;

import lombok.Data;

@Data
public class BinanceCoinmFuturesPremiumIndex {
    private String symbol;
    private String pair;
    private String markPrice;
    private String indexPrice;
    private String lastFundingRate;
    private long nextFundingTime;
    private String interestRate;
    private long time;

}
