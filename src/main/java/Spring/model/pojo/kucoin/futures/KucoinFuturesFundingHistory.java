package Spring.model.pojo.kucoin.futures;

import lombok.Data;

@Data
public class KucoinFuturesFundingHistory {
    private long id;
    private String symbol;
    private long timePoint;
    private double fundingRate;
    private double markPrice;
    private int positionQty;
    private double positionCost;
    private double funding;
    private String settleCurrency;
}
