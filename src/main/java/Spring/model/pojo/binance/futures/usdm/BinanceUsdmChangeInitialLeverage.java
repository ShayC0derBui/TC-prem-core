package Spring.model.pojo.binance.futures.usdm;

import lombok.Data;

/*
* Pojo:
* {
    "leverage": 21,
    "maxNotionalValue": "1000000",
    "symbol": "BTCUSDT"
}
* */
@Data
public class BinanceUsdmChangeInitialLeverage {
    private int leverage;
    private String maxNotionalValue;
    private String symbol;

}
