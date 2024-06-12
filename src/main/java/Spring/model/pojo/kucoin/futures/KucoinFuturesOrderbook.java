package Spring.model.pojo.kucoin.futures;

import lombok.Data;

import java.util.List;

@Data
public class KucoinFuturesOrderbook {
    private String symbol;
    private long sequence;
    private List<List<Double>> asks;
    private List<List<Double>> bids;
    private long ts;
}
