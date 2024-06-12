package Spring.model.pojo.kucoin.futures;

import lombok.Data;

import java.util.List;

@Data
public class KucoinFuturesKlineEntry {
    private List<KucoinKline> data;
    public KucoinFuturesKlineEntry() {
        this.data = data;
    }
}