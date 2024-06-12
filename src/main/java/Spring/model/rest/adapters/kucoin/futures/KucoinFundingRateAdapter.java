package Spring.model.rest.adapters.kucoin.futures;

import Spring.model.pojo.kucoin.futures.KucoinFuturesFundingRate;
import Spring.model.rest.interfaces.FundingRate;

import java.sql.Time;

public class KucoinFundingRateAdapter implements FundingRate {
    private final KucoinFuturesFundingRate pojo;

    public KucoinFundingRateAdapter(KucoinFuturesFundingRate pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getTimePoint() {
        return String.valueOf(pojo.getTimePoint());
    }

    @Override
    public String getFundingRate() {
        return String.valueOf(pojo.getValue());
    }

    @Override
    public String getNextFundingInterval() {
        return String.valueOf(pojo.getValue() + Time.valueOf("00:08:00").getTime());
    }
}
