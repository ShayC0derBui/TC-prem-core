package Spring.model.rest.adapters.kucoin.futures;

import Spring.model.pojo.kucoin.futures.KucoinFuturesContracts;
import Spring.model.rest.interfaces.Contracts;

public class KucoinContractAdapter implements Contracts {
    private final KucoinFuturesContracts pojo;

    public KucoinContractAdapter(KucoinFuturesContracts pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getPrice() {
        return String.valueOf(pojo.getLastTradePrice());
    }

    @Override
    public String get24hChangePercentage() {
        return String.valueOf(pojo.getPriceChgPct());
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
        return String.valueOf(pojo.getVolumeOf24h());
    }
}
