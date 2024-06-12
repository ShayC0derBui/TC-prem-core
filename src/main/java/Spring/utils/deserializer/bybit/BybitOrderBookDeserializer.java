package Spring.utils.deserializer.bybit;

import Spring.model.pojo.bybit.futures.perpetual.*;
import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BybitOrderBookDeserializer implements JsonDeserializer<BybitOrderBookGenericResponse> {
    @Override
    public BybitOrderBookGenericResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BybitOrderBookGenericResponse response = new BybitOrderBookGenericResponse();

        // Deserialize the JSON into the response object as needed
        JsonObject jsonObject = json.getAsJsonObject();
        response.setRetCode(jsonObject.get("retCode").getAsInt());
        response.setRetMsg(jsonObject.get("retMsg").getAsString());

        // Deserialize the 'result' object and 'list' array
        JsonObject resultObject = jsonObject.getAsJsonObject("result");
        response.setResult(new BybitOrderBookGenericResult());

        // Deserialize 'category'
        response.getResult().setSymbol(resultObject.get("s").getAsString());
        response.getResult().setTimestamp(resultObject.get("ts").getAsLong());

        // Deserialize 'ask' array
        if (resultObject.has("a")) {
            JsonArray listArray = resultObject.getAsJsonArray("a");
            List<Ask> asks = new ArrayList<>();

            for (JsonElement element : listArray) {
                JsonArray asksArray = element.getAsJsonArray();

                Ask ask = new Ask(asksArray.get(0).getAsString(), asksArray.get(1).getAsString());

                asks.add(ask);
            }
            response.getResult().setAsks(asks);
        }
        if (resultObject.has("b")) {
            JsonArray listArray = resultObject.getAsJsonArray("b");
            List<Bid> bids = new ArrayList<>();

            for (JsonElement element : listArray) {
                JsonArray bidsArray = element.getAsJsonArray();

                Bid bid = new Bid(bidsArray.get(0).getAsString(), bidsArray.get(1).getAsString());

                bids.add(bid);
            }
            response.getResult().setBids(bids);
        }

        return response;
    }
}
