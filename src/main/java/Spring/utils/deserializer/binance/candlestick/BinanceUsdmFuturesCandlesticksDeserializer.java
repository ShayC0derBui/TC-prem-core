package Spring.utils.deserializer.binance.candlestick;

import Spring.model.pojo.binance.BinanceCandlesticks;
import com.google.gson.*;

import java.lang.reflect.Type;

public class BinanceUsdmFuturesCandlesticksDeserializer implements JsonDeserializer<BinanceCandlesticks> {
    @Override
    public BinanceCandlesticks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();

        BinanceCandlesticks data = new BinanceCandlesticks();
        data.setOpenTime(jsonArray.get(0).getAsString());
        data.setOpen(jsonArray.get(1).getAsString());
        data.setHigh(jsonArray.get(2).getAsString());
        data.setLow(jsonArray.get(3).getAsString());
        data.setClose(jsonArray.get(4).getAsString());
        data.setVolume(jsonArray.get(5).getAsString());
        data.setCloseTime(jsonArray.get(6).getAsString());
        data.setQuoteAssetVolume(jsonArray.get(7).getAsString());
        data.setNumberOfTrades(jsonArray.get(8).getAsString());
        data.setTakerBuyBaseAssetVolume(jsonArray.get(9).getAsString());
        data.setTakerBuyQuoteAssetVolume(jsonArray.get(10).getAsString());

        return data;
    }

}
