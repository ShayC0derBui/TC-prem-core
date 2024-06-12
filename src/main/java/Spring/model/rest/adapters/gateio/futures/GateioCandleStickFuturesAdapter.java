package Spring.model.rest.adapters.gateio.futures;

import Spring.model.pojo.gateio.futures.GateioFuturesCandlesticks;
import Spring.model.rest.interfaces.CandleStick;

public class GateioCandleStickFuturesAdapter implements CandleStick {
    private final GateioFuturesCandlesticks pojo;

    public GateioCandleStickFuturesAdapter(GateioFuturesCandlesticks pojo) {
        this.pojo = pojo;
    }

    @Override
    public String getStartTime() {
        return pojo.getTimeStamp();
    }

    @Override
    public String getCloseTime() {
        return null;
    }

    @Override
    public String getOpenPrice() {
        return pojo.getOpen();
    }

    @Override
    public String getHighPrice() {
        return pojo.getHigh();
    }

    @Override
    public String getLowPrice() {
        return pojo.getLow();
    }

    @Override
    public String getClosePrice() {
        return pojo.getClose();
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
