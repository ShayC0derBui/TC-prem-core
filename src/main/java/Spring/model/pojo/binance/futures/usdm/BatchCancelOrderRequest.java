package Spring.model.pojo.binance.futures.usdm;

import lombok.Data;

import java.util.List;

@Data
public class BatchCancelOrderRequest {
    private List<Long> orderIdList;
    private List<String> origClientOrderIdList;
    private String symbol;
}
