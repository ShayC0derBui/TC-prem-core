package Spring.model.pojo.gateio.spot;

import lombok.Data;

@Data
public class GateioSpotAccount {
    private String currency;
    private String available;
    private String locked;
}
