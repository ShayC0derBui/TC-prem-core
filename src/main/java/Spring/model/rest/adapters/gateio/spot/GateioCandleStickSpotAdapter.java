package Spring.model.rest.adapters.gateio.spot;

import Spring.model.pojo.gateio.spot.GateioSpotCandlesticks;
import Spring.model.rest.interfaces.CandleStick;

public class GateioCandleStickSpotAdapter implements CandleStick  {
    private final GateioSpotCandlesticks pojo;

    public GateioCandleStickSpotAdapter(GateioSpotCandlesticks pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getStartTime() {
        return pojo.getTimestamp();
    }

    @Override
    public String getCloseTime() {
        return null;
    }

    @Override
    public String getOpenPrice() {
        return pojo.getOpenPrice();
    }

    @Override
    public String getHighPrice() {
        return pojo.getHighestPrice();
    }

    @Override
    public String getLowPrice() {
        return pojo.getLowestPrice();
    }

    @Override
    public String getClosePrice() {
        return pojo.getClosePrice();
    }

    @Override
    public String getBaseVolume() {
        return null;
    }

    @Override
    public String getQuoteVolume() {
        return null;
    }
}