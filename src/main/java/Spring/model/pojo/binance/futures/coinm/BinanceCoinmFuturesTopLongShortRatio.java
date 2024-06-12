package Spring.model.pojo.binance.futures.coinm;
import lombok.Data;
@Data
public class BinanceCoinmFuturesTopLongShortRatio {
    private String pair;
    private String longShortRatio;
    private String longAccount;
    private String shortAccount;
    private long timestamp;
}
