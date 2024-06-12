package Spring.model.rest.adapters.kucoin.futures;

import Spring.model.pojo.kucoin.futures.KucoinFuturesOrderbook;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;

import java.util.ArrayList;
import java.util.List;

public class KucoinOrderBookAdapter implements OrderBook {
    private final KucoinFuturesOrderbook pojo;

    public KucoinOrderBookAdapter(KucoinFuturesOrderbook pojo, String symbol) {
        this.pojo = pojo;
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getMessageOutputTimeMS() {
        return String.valueOf(pojo.getTs());
    }

    @Override
    public List<Bid> getBids() {
        List<Bid> bidList = new ArrayList<>();
        for (List<Double> entry : pojo.getBids()) {
            String price = entry.get(0).toString();
            String quantity = entry.get(1).toString();
            Bid bid = new Bid(price, quantity);
            bidList.add(bid);
        }
        return bidList;
    }

    @Override
    public List<Ask> getAsks() {
        List<Ask> askList = new ArrayList<>();
        for (List<Double> entry : pojo.getAsks()) {
            String price = entry.get(0).toString();
            String quantity = entry.get(1).toString();
            Ask ask = new Ask(price, quantity);
            askList.add(ask);
        }
        return askList;
    }
}
