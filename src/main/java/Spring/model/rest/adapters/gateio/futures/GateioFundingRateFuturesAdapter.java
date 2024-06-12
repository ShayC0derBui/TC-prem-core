package Spring.model.rest.adapters.gateio.futures;

import Spring.model.pojo.gateio.futures.GateioFuturesFundingRate;
import Spring.model.rest.interfaces.FundingRate;

import java.util.concurrent.TimeUnit;

public class GateioFundingRateFuturesAdapter implements FundingRate {
    private final GateioFuturesFundingRate pojo;

    public GateioFundingRateFuturesAdapter(GateioFuturesFundingRate pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getName();
    }

    @Override
    public String getTimePoint() {
        return String.valueOf(pojo.getFunding_next_apply() - TimeUnit.HOURS.toMillis(8 ));
    }

    @Override
    public String getFundingRate() {
        return pojo.getFunding_rate();
    }

    @Override
    public String getNextFundingInterval() {
        return String.valueOf(pojo.getFunding_next_apply());
    }
}
