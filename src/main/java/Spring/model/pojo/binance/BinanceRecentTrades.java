package Spring.model.pojo.binance;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BinanceRecentTrades {
    private long id;
    private String price;
    @SerializedName("qty")
    private String quantity;
    @SerializedName("quoteQty")
    private String quoteQuantity;
    private long time;
    private boolean isBuyerMaker;
    @SerializedName("baseQty")
    private String baseQuantity;
    private String isBestMatch;
}
