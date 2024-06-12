package Spring.model.pojo.kucoin.spot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class KucoinSpotCurrencyPairsResponse {
    @SerializedName("code")
    private String statuscode;
    private List<KucoinSpotCurrencyPair> data;
}
