package Spring.model.rest.adapters.bybit;

import Spring.model.pojo.bybit.futures.perpetual.BybitPerpetualFuturesOpenInterest;
import Spring.model.rest.interfaces.OpenInterest;

public class BybitOpenInterestAdapter implements OpenInterest {
    private final BybitPerpetualFuturesOpenInterest pojo;
    private final String symbol;

    public BybitOpenInterestAdapter(BybitPerpetualFuturesOpenInterest pojo, String symbol) {
        this.pojo = pojo;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getOpenInterest() {
        return pojo.getOpenInterest();
    }

    @Override
    public long getTimeStamp() {
        return Long.parseLong(pojo.getTimestamp());
    }
}
