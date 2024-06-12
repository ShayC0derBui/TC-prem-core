package Spring.model.rest.adapters.gateio.futures;

import Spring.model.pojo.gateio.futures.GateioFuturesOrderbook;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;
import java.util.ArrayList;
import java.util.List;

public class GateioOrderBookFuturesAdapter implements OrderBook {
    private final GateioFuturesOrderbook pojo;
    private final String symbol;

    public GateioOrderBookFuturesAdapter(GateioFuturesOrderbook pojo, String symbol) {
        this.pojo = pojo;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getMessageOutputTimeMS() {
        Double time =  pojo.getUpdate() * 1000;
        return String.valueOf(time.longValue());
    }

    @Override
    public List<Bid> getBids() {
        List<Bid> bidList = new ArrayList<>();

        for (GateioFuturesOrderbook.Order entry : pojo.getBids()) {
            String price = entry.getPrice().toString();
            String quantity = entry.getQuantity().toString();
            Bid bid = new Bid(price, quantity);
            bidList.add(bid);
        }
        return bidList;
    }

    @Override
    public List<Ask> getAsks() {
        List<Ask> askList = new ArrayList<>();

        for (GateioFuturesOrderbook.Order entry : pojo.getBids()) {
            String price = entry.getPrice().toString();
            String quantity = entry.getQuantity().toString();
            Ask ask = new Ask(price, quantity);
            askList.add(ask);
        }
        return askList;
    }
}