package Spring.model.pojo.binance.futures.coinm;

import lombok.Data;

import java.util.List;

@Data
public class CoinmExchnageInfoResponse {
    private List<BinanceCoinmFuturesContracts> symbols;

}
