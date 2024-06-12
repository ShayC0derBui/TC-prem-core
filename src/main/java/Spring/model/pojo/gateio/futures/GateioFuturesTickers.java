package Spring.model.pojo.gateio.futures;

import lombok.Data;

@Data
public class GateioFuturesTickers {
    private String contract;
    private String last;
    private String low_24h;
    private String high_24h;
    private String change_percentage;
    private String total_size;
    private String volume_24h;
    private String volume_24h_btc;
    private String volume_24h_usd;
    private String volume_24h_base;
    private String volume_24h_quote;
    private String volume_24h_settle;
    private String mark_price;
    private String funding_rate;
    private String funding_rate_indicative;
    private String index_price;
    private String highest_bid;
    private String lowest_ask;
}
