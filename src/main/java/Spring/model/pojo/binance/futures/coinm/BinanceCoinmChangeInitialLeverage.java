package Spring.model.pojo.binance.futures.coinm;

import lombok.Data;

/*
* Pojo:
* {
    "leverage": 21,
    "maxQty": "1000",  // maximum quantity of base asset
    "symbol": "BTCUSD_200925"
}
* */
@Data
public class BinanceCoinmChangeInitialLeverage {
    private int leverage;
    private String maxQty;
    private String symbol;
}
