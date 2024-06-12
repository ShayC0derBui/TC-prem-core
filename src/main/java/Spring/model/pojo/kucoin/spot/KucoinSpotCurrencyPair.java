package Spring.model.pojo.kucoin.spot;

import lombok.Data;

@Data
public class KucoinSpotCurrencyPair {
    private String symbol;
    private String baseCurrency;
    private String quoteCurrency;
    private String baseMinSize;
    private String quoteMinSize;
    private String baseMaxSize;
    private String quoteMaxSize;
    private String baseIncrement;
    private String quoteIncrement;
    private String minFunds;
    private boolean enableTrading;

}
