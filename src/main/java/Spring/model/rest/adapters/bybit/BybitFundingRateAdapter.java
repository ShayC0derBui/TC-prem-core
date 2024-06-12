package Spring.model.rest.adapters.bybit;

import Spring.model.pojo.bybit.futures.perpetual.BybitPerpetualFuturesFundingRateHistory;
import Spring.model.rest.interfaces.FundingRate;

import java.util.concurrent.TimeUnit;

public class BybitFundingRateAdapter implements FundingRate {
    private final BybitPerpetualFuturesFundingRateHistory pojo;

    public BybitFundingRateAdapter(BybitPerpetualFuturesFundingRateHistory pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getTimePoint() {
        return pojo.getFundingRateTimestamp();
    }

    @Override
    public String getFundingRate() {
        return pojo.getFundingRate();
    }

    @Override
    public String getNextFundingInterval() {
        long interval = TimeUnit.HOURS.toMillis(8);
        return String.valueOf(interval + Long.parseLong(getTimePoint()));
    }
}
