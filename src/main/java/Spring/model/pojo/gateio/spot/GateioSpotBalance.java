package Spring.model.pojo.gateio.spot;


import lombok.Data;

@Data
public class GateioSpotBalance {

    private String timestamp;
    private String currency;
    private String change;
    private String total;
    private String available;
    private String freeze; //how much money is frozen, have but cant use
    private String change_type;

}
