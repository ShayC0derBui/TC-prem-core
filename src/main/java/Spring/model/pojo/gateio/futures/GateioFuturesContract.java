package Spring.model.pojo.gateio.futures;

import lombok.Data;

@Data
public class GateioFuturesContract {
    private String name;
    private String type;
    private String quanto_multiplier;
    private String ref_discount_rate;
    private String order_price_deviate;
    private String maintenance_rate;
    private String mark_type;
    private String last_price;
    private String mark_price;
    private String index_price;
    private String funding_rate_indicative;
    private String mark_price_round;
    private int funding_offset;
    private boolean in_delisting;
    private String risk_limit_base;
    private String interest_rate;
    private String order_price_round;
    private int order_size_min;
    private String ref_rebate_rate;
    private int funding_interval;
    private String risk_limit_step;
    private String leverage_min;
    private String leverage_max;
    private String risk_limit_max;
    private String maker_fee_rate;
    private String taker_fee_rate;
    private String funding_rate;
    private int order_size_max;
    private long funding_next_apply;
    private int short_users;
    private long config_change_time;
    private long trade_size;
    private int position_size;
    private int long_users;
    private String funding_impact_value;
    private int orders_limit;
    private long trade_id;
    private long orderbook_id;
    private boolean enable_bonus;
    private boolean enable_credit;
    private long create_time;

}