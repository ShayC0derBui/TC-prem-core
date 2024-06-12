package Spring.model.pojo.gateio.spot;

import lombok.Data;

@Data
public class GateioSpotOrder {
    private String id;
    private String create_time;
    private String update_time;
    private String currency_pair;
    private String status;
    private String type;
    private String account;
    private String side;
    private String amount;
    private String price;
    private String left;
    private String filled_total;
    private String fee;
    private String fee_currency;
}
