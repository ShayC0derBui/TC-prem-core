package Spring.model.pojo.binance.futures.usdm;

import lombok.Data;

@Data
public class BinanceUsdmFuturesTopLongShortRatio {
    private String symbol;
    private String longShortRatio;
    private String longAccount;
    private String shortAccount;
    private String timestamp;
}
