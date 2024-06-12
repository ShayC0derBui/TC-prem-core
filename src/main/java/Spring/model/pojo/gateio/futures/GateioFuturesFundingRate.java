package Spring.model.pojo.gateio.futures;

import lombok.Data;

@Data
public class GateioFuturesFundingRate {
    private String name;
    private String funding_rate;
    private String funding_rate_indicative;
    private long funding_next_apply;
}
