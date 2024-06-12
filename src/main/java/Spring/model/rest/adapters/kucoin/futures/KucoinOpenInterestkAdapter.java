package Spring.model.rest.adapters.kucoin.futures;

import Spring.model.pojo.kucoin.futures.KucoinFuturesOpenInterestGenericResponse;
import Spring.model.rest.interfaces.OpenInterest;

public class KucoinOpenInterestkAdapter implements OpenInterest {
    private final KucoinFuturesOpenInterestGenericResponse.KucoinFuturesOpenInterest pojo;

    public KucoinOpenInterestkAdapter(KucoinFuturesOpenInterestGenericResponse.KucoinFuturesOpenInterest pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getOpenInterest() {
        return String.valueOf(pojo.getValue());
    }

    @Override
    public long getTimeStamp() {
        return pojo.getTimePoint();
    }
}
