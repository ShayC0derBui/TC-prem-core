package Spring.model.pojo.gateio.futures;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GateioFuturesOrderbook {
    private Double update;
    private List<Order> asks;
    private List<Order> bids;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Order {
        @SerializedName("s")
        private Integer quantity;
        @SerializedName("p")
        private Double price;
    }
}