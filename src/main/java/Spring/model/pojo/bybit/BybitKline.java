package Spring.model.pojo.bybit;

import lombok.Data;

@Data
public class BybitKline {
    private String startTime;
    private String openPrice;
    private String highPrice;
    private String lowPrice;
    private String closePrice;
    private String volume;
    private String turnover;
}
