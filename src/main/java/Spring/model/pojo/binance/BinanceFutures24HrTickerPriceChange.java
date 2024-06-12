package Spring.model.pojo.binance;

import lombok.Data;

@Data
public class BinanceFutures24HrTickerPriceChange {
    private String symbol;
    private String pair;
    private String priceChange;
    private String priceChangePercent;
    private String weightedAvgPrice;
    private String lastPrice;
    private String lastQty;
    private String openPrice;
    private String highPrice;
    private String lowPrice;
    private String volume;
    private String baseVolume;
    private String quoteVolume;
    private long openTime;
    private long closeTime;
    private long firstId;
    private long lastId;
    private long count;
}
