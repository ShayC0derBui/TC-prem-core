package Spring.model.pojo.binance;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class BinanceOrderBook {
    private long lastUpdateId;

    @SerializedName("E")
    private long messageOutputTimeMS;
    private List<List<String>>  bids;
    private List<List<String>>  asks;
}