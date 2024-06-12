package Spring.model.pojo.binance.spot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AggregateTrades{
    @SerializedName("a")
    private long aggregateTradeId;

    @SerializedName("p")
    private String price;

    @SerializedName("q")
    private String quantity;

    @SerializedName("f")
    private long firstTradeId;

    @SerializedName("l")
    private long lastTradeId;

    @SerializedName("T")
    private long timestamp;

    @SerializedName("m")
    private boolean buyerMaker;

    @SerializedName("M")
    private boolean bestPriceMatch;
}

