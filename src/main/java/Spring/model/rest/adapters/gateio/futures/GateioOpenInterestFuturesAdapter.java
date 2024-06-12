package Spring.model.rest.adapters.gateio.futures;

import Spring.model.pojo.gateio.futures.GateioFuturesOpenInterest;
import Spring.model.rest.interfaces.OpenInterest;

public class GateioOpenInterestFuturesAdapter implements OpenInterest {
    private final GateioFuturesOpenInterest pojo;
    private final String contract;

    public GateioOpenInterestFuturesAdapter(GateioFuturesOpenInterest pojo, String contract) {
        this.pojo = pojo;
        this.contract = contract;
    }

    @Override
    public String getSymbol() {
        return contract;
    }

    @Override
    public String getOpenInterest() {
        return String.valueOf(pojo.getOpen_interest());
    }

    @Override
    public long getTimeStamp() {
        return pojo.getTime();
    }
}