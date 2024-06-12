package Spring.model.pojo.kucoin.futures;

import lombok.Data;

@Data
public class KucoinKline {
    private String time;
    private String entryPrice;
    private String highPrice;
    private String lowPrice;
    private String closePrice;
    private String tradingVolume;
}
