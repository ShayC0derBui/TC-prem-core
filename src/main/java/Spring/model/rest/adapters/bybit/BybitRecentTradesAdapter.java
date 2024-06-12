package Spring.model.rest.adapters.bybit;

import Spring.model.pojo.bybit.BybitRecentTrades;
import Spring.model.rest.interfaces.RecentTrades;

public class BybitRecentTradesAdapter implements RecentTrades {
    private final BybitRecentTrades pojo;

    public BybitRecentTradesAdapter(BybitRecentTrades pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getTradeId() {
        return pojo.getExecId();
    }

    @Override
    public String getSymbol() {
        return pojo.getSymbol();
    }

    @Override
    public String getTradePrice() {
        return pojo.getPrice();
    }

    @Override
    public String getBaseQuantity() {
        return pojo.getPrice();
    }

    @Override
    public String getQuoteQuantity() {
        double quoteQty = (Double.parseDouble(pojo.getPrice()) * Double.parseDouble(pojo.getSize()));
        return Double.toString(quoteQty);
    }


    @Override
    public String getSide() {
        return pojo.getSide().toUpperCase();
    }

    @Override
    public String getTimeInMilliseconds() {
        return pojo.getTime();
    }
}
