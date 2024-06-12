package Spring.model.rest.adapters.gateio.spot;

import Spring.model.pojo.gateio.spot.GateioSpotTrade;
import Spring.model.rest.interfaces.RecentTrades;

public class GateioRecentTradesSpotAdapter implements RecentTrades {
    private final GateioSpotTrade pojo;

    public GateioRecentTradesSpotAdapter(GateioSpotTrade pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getTradeId() {
        return  pojo.getTradeId();
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
        return pojo.getAmount();
    }

    @Override
    public String getQuoteQuantity() {
        String baseQuantity = getBaseQuantity();
        String tradePrice = getTradePrice();
        return String.valueOf(Double.parseDouble(baseQuantity) * Double.parseDouble(tradePrice));
    }

    @Override
    public String getSide() {
        return pojo.getSide();
    }

    @Override
    public String getTimeInMilliseconds() {
        return pojo.getTimeMS();
    }
}
