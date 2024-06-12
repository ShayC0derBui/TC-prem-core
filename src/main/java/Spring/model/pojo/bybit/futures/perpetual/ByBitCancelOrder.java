package Spring.model.pojo.bybit.futures.perpetual;
import lombok.Data;

@Data
public class ByBitCancelOrder {
    private String orderId;
    private String orderLinkId;
}
