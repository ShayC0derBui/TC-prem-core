package Spring.model.rest.interfaces;

public interface RecentTrades {
    String getTradeId();

    String getSymbol();

    String getTradePrice();

    String getBaseQuantity();

    String getQuoteQuantity();

    String getSide(); // Implement this in classes when it available.

    String getTimeInMilliseconds();
}
