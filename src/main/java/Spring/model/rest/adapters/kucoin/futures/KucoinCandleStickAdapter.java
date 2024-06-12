package Spring.model.rest.adapters.kucoin.futures;

import Spring.model.pojo.kucoin.futures.KucoinKline;
import Spring.model.rest.interfaces.CandleStick;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KucoinCandleStickAdapter implements CandleStick {
    private final KucoinKline pojo;

    @Override
    public String getStartTime() {
        return pojo.getTime();
    }

    @Override
    public String getCloseTime() {
        return null;
    }

    @Override
    public String getOpenPrice() {
        return pojo.getEntryPrice();
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
        return null;
    }

    @Override
    public String getQuoteVolume() {
        return null;
    }
}
