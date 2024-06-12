package Spring.model.rest.adapters.gateio.futures;

import Spring.model.pojo.gateio.futures.GateioFuturesTrade;
import Spring.model.rest.interfaces.RecentTrades;

public class GateioRecentTradesFuturesAdapter implements RecentTrades {
    private final GateioFuturesTrade pojo;

    public GateioRecentTradesFuturesAdapter(GateioFuturesTrade pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getTradeId() {
        return pojo.getId().toString();
    }

    @Override
    public String getSymbol() {
        return null;
    }

    @Override
    public String getTradePrice() {
        return pojo.getPrice();
    }

    @Override
    public String getBaseQuantity() {
        String price = pojo.getPrice();
        int size = pojo.getSize();
        return String.valueOf(size / Double.parseDouble(price));
    }

    @Override
    public String getQuoteQuantity() {
        return String.valueOf(pojo.getSize());
    }

    @Override
    public String getSide() {
        int size = pojo.getSize();
        if (size >= 0) {
            return "buy";
        } else {
            return "sell";
        }
    }

    @Override
    public String getTimeInMilliseconds() {
        return String.valueOf(pojo.getCreate_time());
    }
}
