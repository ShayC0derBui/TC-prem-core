package Spring.model.rest.adapters.binance;

import Spring.model.pojo.binance.BinanceOrderBook;
import Spring.model.rest.interfaces.OrderBook;
import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;

import java.util.ArrayList;
import java.util.List;

public class BinanceOrderBookAdapter implements OrderBook {
    private final BinanceOrderBook pojo;
    private final String symbol;

    public BinanceOrderBookAdapter(BinanceOrderBook pojo, String symbol) {
        this.pojo = pojo;
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getMessageOutputTimeMS() {
        return String.valueOf(pojo.getMessageOutputTimeMS());
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
        List<Ask> askList = new ArrayList();
        for (List<String> entry : pojo.getAsks()) {
            String price = entry.get(0);
            String quantity = entry.get(1);
            Ask ask = new Ask(price, quantity);
            askList.add(ask);
        }
        return askList;
    }
}
