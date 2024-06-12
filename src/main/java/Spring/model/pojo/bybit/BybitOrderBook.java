package Spring.model.pojo.bybit;

import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class BybitOrderBook {
    @SerializedName("s")
    private String symbol;
    @SerializedName("a")
    private List<Ask> asks;
    @SerializedName("b")
    private List<Bid> bids;
    @SerializedName("ts")
    private long timestamp;

}
