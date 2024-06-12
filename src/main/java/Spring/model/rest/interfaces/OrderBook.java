package Spring.model.rest.interfaces;

import Spring.model.rest.pojo.Ask;
import Spring.model.rest.pojo.Bid;

import java.util.List;

public interface OrderBook {
    String getSymbol();

    String getMessageOutputTimeMS();

    List<Bid> getBids();

    List<Ask> getAsks();
}
