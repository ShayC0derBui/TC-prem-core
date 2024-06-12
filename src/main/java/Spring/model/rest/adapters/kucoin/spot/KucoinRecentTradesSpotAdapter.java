package Spring.model.rest.adapters.kucoin.spot;

import Spring.model.pojo.kucoin.spot.KucoinRecentTradesSpot;
import Spring.model.rest.interfaces.RecentTrades;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KucoinRecentTradesSpotAdapter implements RecentTrades {
    private final KucoinRecentTradesSpot pojo;

    @Override
    public String getTradeId() {
        return pojo.getSequence();
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
        return pojo.getSize();
    }

    @Override
    public String getQuoteQuantity() {
        return String.valueOf(Double.parseDouble(pojo.getSize()) * Double.parseDouble(pojo.getPrice()));
    }

    @Override
    public String getSide() {
        return pojo.getSide();
    }

    @Override
    public String getTimeInMilliseconds() {
        return String.valueOf(pojo.getTime());
    }
}
