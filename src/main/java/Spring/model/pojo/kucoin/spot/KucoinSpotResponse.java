package Spring.model.pojo.kucoin.spot;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class KucoinSpotResponse {

    @SerializedName("code")
    private String statuscode;
    private JsonObject data;
}
