package Spring.model.pojo.gateio.spot;

import lombok.Data;

@Data
public class GateioSpotTicker {
    private String currency_pair;
    private String last;
    private String base_volume;
    private String change_percentage;
    private String quote_volume;
    private String high_24h;
    private String low_24h;
    private Long time_ms;
}