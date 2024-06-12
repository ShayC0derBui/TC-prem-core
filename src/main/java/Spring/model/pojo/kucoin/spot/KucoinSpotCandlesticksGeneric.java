package Spring.model.pojo.kucoin.spot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class KucoinSpotCandlesticksGeneric {
    private String code;
    private List<KucoinSpotCandlesticks> data;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class KucoinSpotCandlesticks {
        private String time;
        private String open;
        private String close;
        private String high;
        private String low;
        private String volume;
        private String amount;
    }
}
