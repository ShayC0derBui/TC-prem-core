package Spring.model.pojo.gateio.spot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GateioSpotCurrencyPair {
    @SerializedName("id")
    private String name;

    @SerializedName("base")
    private String baseCurrency;

    @SerializedName("quote")
    private String quoteCurrency;

    private String fee;

    private String min_base_amount;

    private String min_quote_amount;

    private Long amount_precision;
    private Long precision;


}
