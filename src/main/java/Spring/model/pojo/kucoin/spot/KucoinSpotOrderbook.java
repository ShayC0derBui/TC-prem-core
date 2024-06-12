package Spring.model.pojo.kucoin.spot;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class KucoinSpotOrderbook {
    private List<List<String>> bids;
    private List<List<String>> asks;
    private String time;
    private String sequence;
}






