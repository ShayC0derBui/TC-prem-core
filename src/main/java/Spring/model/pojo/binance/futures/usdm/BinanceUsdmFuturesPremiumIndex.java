package Spring.model.pojo.binance.futures.usdm;

import lombok.Data;

@Data
public class BinanceUsdmFuturesPremiumIndex {
    private String symbol;
    private String markPrice;
    private String indexPrice;
    private String lastFundingRate;
    private long nextFundingTime;
    private String interestRate;
    private long time;

}
