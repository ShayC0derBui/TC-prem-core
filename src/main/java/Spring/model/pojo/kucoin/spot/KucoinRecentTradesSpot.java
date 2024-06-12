package Spring.model.pojo.kucoin.spot;

import lombok.Data;

@Data
public class KucoinRecentTradesSpot {
    private String sequence;
    private String price;
    private String size;
    private String side;
    private long time;
}
