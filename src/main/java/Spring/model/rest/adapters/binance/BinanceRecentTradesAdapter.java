package Spring.model.rest.adapters.binance;

import Spring.model.pojo.binance.BinanceRecentTrades;
import Spring.model.rest.interfaces.RecentTrades;

public class BinanceRecentTradesAdapter implements RecentTrades {
    private final BinanceRecentTrades pojo;
    private final String endpoint;
    private final String symbol;

    public BinanceRecentTradesAdapter(BinanceRecentTrades pojo, String endpoint, String symbol) {
        this.pojo = pojo;
        this.endpoint = endpoint;
        this.symbol = symbol;
    }

    @Override
    public String getTradeId() {
        return String.valueOf(pojo.getId());
    }

    @Override
    public String getSymbol(){
        return symbol;
    }

    @Override
    public String getTradePrice() {
        return pojo.getPrice();
    }

    @Override
    public String getBaseQuantity() {
        if (endpoint.contains("dapi")) {
            return pojo.getBaseQuantity();
        } else if (endpoint.contains("fapi")) {
            return pojo.getQuantity();
        } else if (endpoint.contains("api")) {
            return pojo.getQuantity();
        }
        return null;
    }

    @Override
    public String getQuoteQuantity() {
        if (endpoint.contains("dapi")) {
            return pojo.getQuantity();
        } else if (endpoint.contains("fapi")) {
            return pojo.getQuoteQuantity();
        } else if (endpoint.contains("api")) {
            return pojo.getQuoteQuantity();
        }
        return null;
    }

    @Override
    public String getSide() {
        return pojo.isBuyerMaker() ? "Buy" : "Sell";
    }

    @Override
    public String getTimeInMilliseconds() {
        return String.valueOf(pojo.getTime());
    }

}
