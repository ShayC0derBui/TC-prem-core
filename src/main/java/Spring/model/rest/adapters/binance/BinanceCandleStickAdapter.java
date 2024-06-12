package Spring.model.rest.adapters.binance;

import Spring.model.pojo.binance.BinanceCandlesticks;
import Spring.model.rest.interfaces.CandleStick;

public class BinanceCandleStickAdapter implements CandleStick {

    private final BinanceCandlesticks pojo;
    private final String endpoint;
    private final String symbol;

    public BinanceCandleStickAdapter(BinanceCandlesticks pojo, String endpoint, String symbol) {
        this.pojo = pojo;
        this.endpoint = endpoint;
        this.symbol = symbol;
    }

    @Override
    public String getCloseTime() {
        return pojo.getCloseTime();
    }

    @Override
    public String getStartTime() {
        return String.valueOf(pojo.getOpenTime());
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
        if (endpoint.contains("dapi")) {
            return pojo.getBaseAssetVolume();
        } else if (endpoint.contains("fapi")) {
            return pojo.getVolume();
        } else if (endpoint.contains("api")) {
            return pojo.getVolume();
        }
        return null;
    }

    @Override
    public String getQuoteVolume() {
        if (endpoint.contains("dapi")) {
            return pojo.getVolume();
        } else if (endpoint.contains("fapi")) {
            return pojo.getQuoteAssetVolume();
        } else if (endpoint.contains("api")) {
            return pojo.getQuoteAssetVolume();
        }
        return null;
    }
}
