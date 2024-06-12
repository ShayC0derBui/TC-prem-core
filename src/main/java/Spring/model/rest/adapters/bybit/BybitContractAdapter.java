package Spring.model.rest.adapters.bybit;

import Spring.model.pojo.bybit.BybitSymbol;
import Spring.model.rest.interfaces.Contracts;

public class BybitContractAdapter implements Contracts {

    private final BybitSymbol pojo;

    public BybitContractAdapter(BybitSymbol pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getPrice() {
        return pojo.getLastPrice();
    }

    @Override
    public String get24hChangePercentage() {
        return pojo.getPrice24hPcnt()+"%";
    }

    @Override
    public String get24hHigh() {
        return pojo.getHighPrice24h();
    }

    @Override
    public String get24hLow() {
        return pojo.getLowPrice24h();
    }

    @Override
    public String get24hVolume() {
        return pojo.getVolume24h();
    }
}
