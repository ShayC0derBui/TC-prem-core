package Spring.model.pojo.kucoin.futures;

import lombok.Data;

import java.util.List;

@Data
public class KucoinFuturesSnapshot {
    private String symbol;
    private long sequence;
    private List<List<String>> asks;
    private List<List<String>> bids;
}
