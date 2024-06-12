package Spring.model.pojo.kucoin.futures;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class KucoinFuturesOpenInterestGenericResponse {
    private List<KucoinFuturesOpenInterest> dataList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static
    public class KucoinFuturesOpenInterest {
        private String symbol;
        private long granularity;
        private long timePoint;
        // TODO: value is in weired format.
        private double value;
    }
}
