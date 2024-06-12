package Spring.model.rest.adapters.bybit;

import Spring.model.pojo.bybit.BybitKline;
import Spring.model.rest.interfaces.CandleStick;

public class BybitCandleStickAdapter implements CandleStick {

    private final BybitKline pojo;
    private final String interval;

    public BybitCandleStickAdapter(BybitKline pojo, String interval) {
        this.pojo = pojo;
        this.interval = interval;
    }

    @Override
    public String getStartTime() {
        return pojo.getStartTime();
    }

    @Override
    public String getCloseTime() {
        return String.valueOf(Long.parseLong(pojo.getStartTime()) + Long.parseLong(interval)*60000);
    }

    @Override
    public String getOpenPrice() {
        return pojo.getOpenPrice();
    }

    @Override
    public String getHighPrice() {
        return pojo.getHighPrice();
    }

    @Override
    public String getLowPrice() {
        return pojo.getLowPrice();
    }

    @Override
    public String getClosePrice() {
        return pojo.getClosePrice();
    }

    @Override
    public String getBaseVolume() {
        return pojo.getVolume();
    }

    @Override
    public String getQuoteVolume() {
        return pojo.getTurnover();
    }
}
