package Spring.model.rest.adapters.gateio.spot;

import Spring.model.pojo.gateio.spot.GateioSpotOrderbook;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;

import java.util.ArrayList;
import java.util.List;

public class GateioOrderBookSpotAdapter implements OrderBook {
    private final GateioSpotOrderbook pojo;
    private final String symbol;

    public GateioOrderBookSpotAdapter(GateioSpotOrderbook pojo, String symbol) {
        this.pojo = pojo;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getMessageOutputTimeMS() {
        Double time = pojo.getUpdate() * 1000;
        return String.valueOf(time.longValue());
    }

    @Override
    public List<Bid> getBids() {
        List<Bid> bids = new ArrayList<>();
        for (GateioSpotOrderbook.Order order : pojo.getBids()) {
            Bid bid = new Bid(String.valueOf(order.getPrice()),String.valueOf(order.getQuantity()));
            bids.add(bid);
        }
        return bids;
    }

    @Override
    public List<Ask> getAsks() {
        List<Ask> asks = new ArrayList<>();
        for (GateioSpotOrderbook.Order order : pojo.getAsks()) {
            Ask ask = new Ask(String.valueOf(order.getPrice()),String.valueOf(order.getQuantity()));
            asks.add(ask);
        }
        return asks;
    }
}