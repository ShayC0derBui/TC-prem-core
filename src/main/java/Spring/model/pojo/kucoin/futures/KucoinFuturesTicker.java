package Spring.model.pojo.kucoin.futures;

import lombok.Data;

@Data
public class KucoinFuturesTicker {
    private long sequence;
    private String symbol;
    private String side;
    private int size;
    private String price;
    private int bestBidSize;
    private String bestBidPrice;
    private int bestAskSize;
    private String bestAskPrice;
    private String tradeId;
    private long ts;
}
