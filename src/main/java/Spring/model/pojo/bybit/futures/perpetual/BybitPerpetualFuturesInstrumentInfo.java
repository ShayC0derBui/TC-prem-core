package Spring.model.pojo.bybit.futures.perpetual;

import lombok.Data;

@Data
public class BybitPerpetualFuturesInstrumentInfo {

    private String symbol;
    private String contractType;
    private String status;
    private String baseCoin;
    private String quoteCoin;
    private String launchTime;
    private String deliveryTime;
    private String deliveryFeeRate;
    private String priceScale;
    private LeverageFilter leverageFilter;
    private PriceFilter priceFilter;
    private LotSizeFilter lotSizeFilter;
    private String unifiedMarginTrade;
    private String fundingInterval;
    private String settleCoin;

    @Data
    public static class LeverageFilter {
        private String minLeverage;
        private String maxLeverage;
        private String leverageStep;
    }

    @Data
    public static class PriceFilter {
        private String minPrice;
        private String maxPrice;
        private String tickSize;
    }

    @Data
    public static class LotSizeFilter {
        private String maxOrderQty;
        private String minOrderQty;
        private String qtyStep;
        private String postOnlyMaxOrderQty;
    }
}

