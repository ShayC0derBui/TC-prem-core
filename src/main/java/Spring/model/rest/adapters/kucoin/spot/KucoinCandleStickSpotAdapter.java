package Spring.model.rest.adapters.kucoin.spot;

import Spring.model.pojo.kucoin.spot.KucoinSpotCandlesticksGeneric;
import Spring.model.rest.interfaces.CandleStick;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KucoinCandleStickSpotAdapter implements CandleStick {
    private final KucoinSpotCandlesticksGeneric.KucoinSpotCandlesticks pojo;

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
