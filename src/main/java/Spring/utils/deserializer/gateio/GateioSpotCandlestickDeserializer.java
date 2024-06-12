package Spring.utils.deserializer.gateio;

import Spring.model.pojo.gateio.spot.GateioSpotCandlesticks;
import com.google.gson.*;

import java.lang.reflect.Type;

public class GateioSpotCandlestickDeserializer implements JsonDeserializer<GateioSpotCandlesticks> {
    @Override
    public GateioSpotCandlesticks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonArray jsonArray = json.getAsJsonArray();
        GateioSpotCandlesticks data = new GateioSpotCandlesticks();
        data.setTimestamp(String.valueOf(jsonArray.get(0).getAsLong()));
        data.setQuoteCurrencyTradingVolume(jsonArray.get(1).getAsString());
        data.setClosePrice(jsonArray.get(2).getAsString());
        data.setHighestPrice(jsonArray.get(3).getAsString());
        data.setLowestPrice(jsonArray.get(4).getAsString());
        data.setOpenPrice(jsonArray.get(5).getAsString());
        data.setBaseCurrencyTradingAmount(jsonArray.get(6).getAsString());
        return data;
    }
}