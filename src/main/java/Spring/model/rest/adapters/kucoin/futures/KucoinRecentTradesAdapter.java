package Spring.model.rest.adapters.kucoin.futures;

import Spring.model.pojo.kucoin.futures.KucoinFuturesTradeHistory;
import Spring.model.rest.interfaces.RecentTrades;

public class KucoinRecentTradesAdapter implements RecentTrades {
    private final KucoinFuturesTradeHistory pojo;

    public KucoinRecentTradesAdapter(KucoinFuturesTradeHistory pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getTradeId() {
        return pojo.getTradeId();
    }

    @Override
    public String getSymbol() {
        return null;
    }

    @Override
    public String getTradePrice() {
        return pojo.getPrice();
    }

    // TODO: figure out how to get base quantity
    @Override
    public String getBaseQuantity() {
        return "todo";
    }

    // TODO: figure out how to get quoute quantity
    @Override
    public String getQuoteQuantity() {
        return "todo";
    }

    @Override
    public String getSide() {
        return pojo.getSide();
    }

    @Override
    public String getTimeInMilliseconds() {
        return pojo.getTs().toString();
    }
}
