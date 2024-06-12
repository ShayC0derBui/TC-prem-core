package Spring.model.pojo.binance.futures.usdm;

import lombok.Data;

/* pojo:
*  {
        "accountAlias": "SgsR",    // unique account code
        "asset": "USDT",    // asset name
        "balance": "122607.35137903", // wallet balance
        "crossWalletBalance": "23.72469206", // crossed wallet balance
        "crossUnPnl": "0.00000000"  // unrealized profit of crossed positions
        "availableBalance": "23.72469206",       // available balance
        "maxWithdrawAmount": "23.72469206",     // maximum amount for transfer out
        "marginAvailable": true,    // whether the asset can be used as margin in Multi-Assets mode
        "updateTime": 1617939110373
    }
* */
@Data
public class BinanceUsdmAccountBalance {
    private String accountAlias;
    private String asset;
    private String balance;
    private String crossWalletBalance;
    private String crossUnPnl;
    private String availableBalance;
    private String maxWithdrawAmount;
    private boolean marginAvailable;
    private Long updateTime;
}
