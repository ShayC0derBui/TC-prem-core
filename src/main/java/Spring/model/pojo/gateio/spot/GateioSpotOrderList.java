package Spring.model.pojo.gateio.spot;

import lombok.Data;

import java.util.List;

@Data
public class GateioSpotOrderList {
    private String currency_pair;
    private int total;
    private List<GateioSpotOrder> orders;


}

