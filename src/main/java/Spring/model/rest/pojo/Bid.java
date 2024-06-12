package Spring.model.rest.pojo;

import lombok.Data;

@Data
public class Bid {
    String price;
    String quantity;

    public Bid(String price, String quantity) {
        this.price = price;
        this.quantity = quantity;
    }
}
