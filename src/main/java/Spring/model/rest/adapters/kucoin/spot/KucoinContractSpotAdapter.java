package Spring.model.rest.adapters.kucoin.spot;

import Spring.model.pojo.kucoin.spot.KucoinSpotTickers;
import Spring.model.rest.interfaces.Contracts;

public class KucoinContractSpotAdapter implements Contracts {
    private final KucoinSpotTickers pojo;

    public KucoinContractSpotAdapter(KucoinSpotTickers pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getPrice() {
        return pojo.getLast();
    }

    @Override
    public String get24hChangePercentage() {
        return pojo.getChangeRate()+"%";
    }

    @Override
    public String get24hHigh() {
        return null;
    }

    @Override
    public String get24hLow() {
        return null;
    }

    @Override
    public String get24hVolume() {
        return pojo.getVolValue();
    }
}
