package Spring.model.rest.adapters.binance;

import Spring.model.pojo.binance.BinanceFutures24HrTickerPriceChange;
import Spring.model.pojo.binance.BinanceSymbol;
import Spring.model.rest.interfaces.Contracts;

public class BinanceContractAdapter implements Contracts {

    private final BinanceFutures24HrTickerPriceChange ChangePojo;
    private final String endpoint;
    private final BinanceSymbol symbol;

    public BinanceContractAdapter(BinanceFutures24HrTickerPriceChange ChangePojo, BinanceSymbol symbol, String endpoint) {
        this.ChangePojo = ChangePojo;
        this.symbol = symbol;
        this.endpoint = endpoint;
    }

    @Override
    public String getSymbol() {
        return symbol.getSymbol();
    }

    @Override
    public String getPrice() {
        return symbol.getPrice();
    }

    @Override
    public String get24hChangePercentage() {
        return ChangePojo.getPriceChangePercent()+"%";
    }

    @Override
    public String get24hHigh() {
        return ChangePojo.getHighPrice();
    }

    @Override
    public String get24hLow() {
        return ChangePojo.getLowPrice();
    }

    @Override
    public String get24hVolume() {
        if(endpoint.contains("dapi"))
            return ChangePojo.getVolume();
        else if(endpoint.contains("fapi"))
            return ChangePojo.getQuoteVolume();
        else if(endpoint.contains("api"))
            return ChangePojo.getQuoteVolume();
        return null;
    }
}
