package Spring.utils.deserializer.bybit;

import Spring.model.pojo.bybit.BybitKline;
import Spring.model.pojo.bybit.futures.perpetual.BybitKlineGenericResponse;
import Spring.model.pojo.bybit.futures.perpetual.BybitKlineGenericResult;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class BybitKlineDeserializer implements JsonDeserializer<BybitKlineGenericResponse> {
    @Override
    public BybitKlineGenericResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BybitKlineGenericResponse response = new BybitKlineGenericResponse();

        // Deserialize the JSON into the response object as needed
        JsonObject jsonObject = json.getAsJsonObject();
        response.setRetCode(jsonObject.get("retCode").getAsInt());
        response.setRetMsg(jsonObject.get("retMsg").getAsString());

        // Deserialize the 'result' object and 'list' array
        JsonObject resultObject = jsonObject.getAsJsonObject("result");
        if (resultObject != null) {
            response.setResult(new BybitKlineGenericResult());

            // Deserialize 'category'
            response.getResult().setCategory(resultObject.get("category").getAsString());

            // Deserialize 'list' array (use your existing logic)
            if (resultObject.has("list")) {
                JsonArray listArray = resultObject.getAsJsonArray("list");
                List<BybitKline> klines = new ArrayList<>();

                for (JsonElement element : listArray) {
                    if (!element.isJsonArray()) {
                        throw new JsonParseException("Invalid element in the 'list' array. Expected an array.");
                    }

                    JsonArray klineArray = element.getAsJsonArray();

                    // Ensure that the array has at least 7 elements (adjust as needed)
                    if (klineArray.size() < 7) {
                        throw new JsonParseException("Invalid number of elements in a 'list' array element.");
                    }

                    // Extract data from the array and create a BybitKline object
                    BybitKline kline = new BybitKline();
                    kline.setStartTime(klineArray.get(0).getAsString());
                    kline.setOpenPrice(klineArray.get(1).getAsString());
                    kline.setHighPrice(klineArray.get(2).getAsString());
                    kline.setLowPrice(klineArray.get(3).getAsString());
                    kline.setClosePrice(klineArray.get(4).getAsString());
                    kline.setVolume(klineArray.get(5).getAsString());
                    kline.setTurnover(klineArray.get(6).getAsString());

                    // Add the kline object to the list
                    klines.add(kline);

                }

                response.getResult().setList(klines);
            }
        }

        return response;
    }
}
