package Spring.model.pojo.binance.futures.usdm;

public class OrderBookEntry {
    private double price;
    private double quantity;

    public OrderBookEntry(double price, double quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }
}
