package Spring.model.rest.adapters.bybit;

import Spring.model.pojo.bybit.futures.perpetual.BybitOrderBookGenericResult;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;

import java.util.List;

public class BybitOrderBookAdapter implements OrderBook {
    private final BybitOrderBookGenericResult pojo;

    public BybitOrderBookAdapter(BybitOrderBookGenericResult pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getMessageOutputTimeMS() {
        return String.valueOf(pojo.getTimestamp());
    }

    @Override
    public List<Bid> getBids() {
        return pojo.getBids();
    }

    @Override
    public List<Ask> getAsks() {
        return pojo.getAsks();
    }
}


