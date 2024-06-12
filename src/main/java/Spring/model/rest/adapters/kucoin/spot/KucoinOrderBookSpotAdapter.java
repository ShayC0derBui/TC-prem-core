package Spring.model.rest.adapters.kucoin.spot;

import Spring.model.pojo.kucoin.spot.KucoinSpotOrderbook;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class KucoinOrderBookSpotAdapter implements OrderBook {
    private final KucoinSpotOrderbook pojo;
    private final String symbol;

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getMessageOutputTimeMS() {
        return pojo.getTime();
    }

    @Override
    public List<Bid> getBids() {
        List<Bid> bidList = new ArrayList<>();
        for (List<String> entry : pojo.getBids()) {
            String price = entry.get(0);
            String quantity = entry.get(1);
            Bid bid = new Bid(price, quantity);
            bidList.add(bid);
        }
        return bidList;
    }

    @Override
    public List<Ask> getAsks() {
        List<Ask> askList = new ArrayList<>();
        for (List<String> entry : pojo.getAsks()) {
            String price = entry.get(0);
            String quantity = entry.get(1);
            Ask ask = new Ask(price, quantity);
            askList.add(ask);
        }
        return askList;
    }
}
