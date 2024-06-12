package Spring.model.rest.adapters.binance;

import Spring.model.pojo.binance.BinanceFuturesFundingRateHistory;
import Spring.model.rest.interfaces.FundingRate;

import java.util.concurrent.TimeUnit;

public class BinanceFundingRateAdapter implements FundingRate {
    private final BinanceFuturesFundingRateHistory pojo;

    public BinanceFundingRateAdapter(BinanceFuturesFundingRateHistory pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getTimePoint() {
        return String.valueOf(pojo.getFundingTime());
    }

    @Override
    public String getFundingRate() {
        return pojo.getFundingRate();
    }

    @Override
    public String getNextFundingInterval() {
        return String.valueOf(pojo.getFundingTime() + TimeUnit.HOURS.toMillis(8 ));
    }
}
