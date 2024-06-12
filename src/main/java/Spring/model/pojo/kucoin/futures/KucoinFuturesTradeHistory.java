package Spring.model.pojo.kucoin.futures;


import lombok.Data;

@Data
public class KucoinFuturesTradeHistory {
    private long sequence;
    private String side;
    private int size;
    private String price;
    private String takerOrderId;
    private String makerOrderId;
    private String tradeId;
    private Long ts;
}
