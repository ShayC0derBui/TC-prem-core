package Spring.model.rest.adapters.gateio.spot;

import Spring.model.pojo.gateio.spot.GateioSpotTicker;
import Spring.model.rest.interfaces.Contracts;

public class GateioContractSpotAdapter implements Contracts {
    private final GateioSpotTicker pojo;

    public GateioContractSpotAdapter(GateioSpotTicker pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getCurrency_pair();
    }

    @Override
    public String getPrice() {
        return pojo.getLast();
    }

    @Override
    public String get24hChangePercentage() {
        return pojo.getChange_percentage()+"%";
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
        return pojo.getQuote_volume();
    }
}
