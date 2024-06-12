package Spring.model.rest.pojo;

import lombok.Data;

@Data
public class Ask {
    String price;
    String quantity;

    public Ask(String price, String quantity) {
        this.price = price;
        this.quantity = quantity;
    }
}
