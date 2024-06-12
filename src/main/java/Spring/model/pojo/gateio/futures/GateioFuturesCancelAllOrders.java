package Spring.model.pojo.gateio.futures;

import Spring.model.pojo.gateio.futures.perpetual.Initial;
import Spring.model.pojo.gateio.futures.perpetual.Trigger;
import lombok.Data;

@Data
public class GateioFuturesCancelAllOrders {
    private Initial initial;
    private Trigger trigger;
    private int id;
    private int user;
    private long create_time;
    private long finish_time;
    private int trade_id;
    private String status;
    private String finish_as;
    private String reason;
    private String order_type;
}
