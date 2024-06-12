package Spring.model.pojo.kucoin.spot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class KucoinSpotTickersResponse {
    @SerializedName("time")
    private long timeMs;
    private List<KucoinSpotTickers> ticker;
}
