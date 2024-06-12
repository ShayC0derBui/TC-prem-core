package Spring.model.pojo.gateio.spot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class GateioSpotOrderbook {
    private Double update;
    private List<Order> asks;
    private List<Order> bids;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Order {
        private String price;
        private String quantity;
    }
}