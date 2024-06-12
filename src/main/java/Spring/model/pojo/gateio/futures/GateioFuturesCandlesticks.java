package Spring.model.pojo.gateio.futures;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GateioFuturesCandlesticks {
    @SerializedName("t")
    private String timeStamp;
    @SerializedName("v")
    private String volume;
    @SerializedName("c")
    private String close;
    @SerializedName("h")
    private String high;
    @SerializedName("l")
    private String low;
    @SerializedName("o")
    private String open;
    private String sum;

}
