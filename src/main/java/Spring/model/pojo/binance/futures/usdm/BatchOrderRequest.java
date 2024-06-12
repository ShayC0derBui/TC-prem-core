package Spring.model.pojo.binance.futures.usdm;

import lombok.Data;

@Data
public class BatchOrderRequest {
    private String type;
    private String timeInForce;
    private String symbol;
    private String side;
    private String price;
    private String quantity;
}
