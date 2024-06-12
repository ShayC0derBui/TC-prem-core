package Spring.model.pojo.gateio.spot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GateioSpotTrade {
    @SerializedName("id")
    private String tradeId;
    @SerializedName("create_time_ms")
    private String timeMS;
    private String side;
    private String amount;
    private String price;
}
