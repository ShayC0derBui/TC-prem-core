package Spring.model.rest.adapters.binance;

import Spring.model.pojo.binance.BinanceFuturesOpenInterest;
import Spring.model.rest.interfaces.OpenInterest;

public class BinanceOpenInterestAdapter implements OpenInterest {
    private final BinanceFuturesOpenInterest pojo;

    public BinanceOpenInterestAdapter(BinanceFuturesOpenInterest pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getOpenInterest() {
        return pojo.getOpenInterest();
    }

    @Override
    public long getTimeStamp() {
        return pojo.getTime();
    }
}
