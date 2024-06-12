package Spring.model.pojo.gateio.futures;

import lombok.Data;

@Data
public class GateioFuturesTrade {
    private String contract;
    private String price;
    private int size;
    private double create_time;
    private Long id;
}
