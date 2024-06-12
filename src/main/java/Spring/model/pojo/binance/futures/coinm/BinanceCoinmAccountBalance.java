package Spring.model.pojo.binance.futures.coinm;

import lombok.Data;

@Data
public class BinanceCoinmAccountBalance {
    private String accountAlias;
    private String asset;
    private String balance;
    private String withdrawAvailable;
    private String crossWalletBalance;
    private String crossUnPnl;
    private String availableBalance;
    private Long updateTime;
}
