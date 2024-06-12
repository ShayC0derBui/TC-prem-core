package Spring.utils.deserializer.gateio;

import Spring.model.pojo.gateio.spot.GateioSpotOrderbook;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GateioSpotOrderbookDeserializer implements JsonDeserializer<GateioSpotOrderbook> {
    @Override
    public GateioSpotOrderbook deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        GateioSpotOrderbook orderbook = new GateioSpotOrderbook();
        List<GateioSpotOrderbook.Order> asks = new ArrayList<>();
        List<GateioSpotOrderbook.Order> bids = new ArrayList<>();
        orderbook.setUpdate(jsonElement.getAsJsonObject().get("update").getAsDouble());
        for(int i = 0; i < jsonElement.getAsJsonObject().get("asks").getAsJsonArray().size(); i++) {
            String price = jsonElement.getAsJsonObject().get("asks").getAsJsonArray().get(i).getAsJsonArray().get(0).getAsString();
            String quantity = jsonElement.getAsJsonObject().get("asks").getAsJsonArray().get(i).getAsJsonArray().get(1).getAsString();
            asks.add(new GateioSpotOrderbook.Order(price, quantity));
        }
        for(int i = 0; i < jsonElement.getAsJsonObject().get("bids").getAsJsonArray().size(); i++) {
            String price = jsonElement.getAsJsonObject().get("bids").getAsJsonArray().get(i).getAsJsonArray().get(0).getAsString();
            String quantity = jsonElement.getAsJsonObject().get("bids").getAsJsonArray().get(i).getAsJsonArray().get(1).getAsString();
            bids.add(new GateioSpotOrderbook.Order(price, quantity));
        }
        orderbook.setAsks(asks);
        orderbook.setBids(bids);
        return orderbook;
    }
}
