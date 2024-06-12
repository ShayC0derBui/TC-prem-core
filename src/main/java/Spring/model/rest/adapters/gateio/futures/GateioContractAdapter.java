package Spring.model.rest.adapters.gateio.futures;

import Spring.model.pojo.gateio.futures.GateioFuturesTickers;
import Spring.model.rest.interfaces.Contracts;

public class GateioContractAdapter implements Contracts {

    private final GateioFuturesTickers pojo;

    public GateioContractAdapter(GateioFuturesTickers pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getContract();
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
        return pojo.getVolume_24h_usd();
    }
}
