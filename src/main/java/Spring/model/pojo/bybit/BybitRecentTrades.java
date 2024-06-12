package Spring.model.pojo.bybit;

import lombok.Data;

@Data
public class BybitRecentTrades {

    private String execId;
    private String symbol;
    private String price;
    private String size;
    private String side;
    private String time;
    private boolean isBlockTrade;
}
