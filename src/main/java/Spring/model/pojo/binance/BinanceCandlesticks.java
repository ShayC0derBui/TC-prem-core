package Spring.model.pojo.binance;

import lombok.Data;

@Data
public class BinanceCandlesticks {
    private String openTime;
    private String open;
    private String high;
    private String low;
    private String close;
    private String volume;
    private String closeTime;
    private String quoteAssetVolume;
    private String baseAssetVolume;
    private String numberOfTrades;
    private String takerBuyVolume;
    private String takerBuyQuoteAssetVolume;
    private String takerBuyBaseAssetVolume;
}

