package Spring.service.generalParams;

import Spring.service.generalParams.general.binance.BinanceGeneralParamMapping;
import Spring.service.generalParams.general.bybit.kline.BybitKlineGeneralParamMapping;
import Spring.service.generalParams.general.bybit.notKline.BybitNonKlineGeneralParamMapping;
import Spring.service.generalParams.general.gateio.GateioGeneralParamMapping;
import Spring.service.generalParams.general.kucoin.future.KucoinFutureGeneralParamMapping;
import Spring.service.generalParams.general.kucoin.spot.KucoinSpotGeneralParamMapping;

import java.util.Map;

public class GeneralParamConverter {

    public Map<String, Object> convertParams(Map<String, Object> generalParams, String exchange, String type) {
        switch (exchange.toUpperCase()) {
            case "BYBIT":
                if(type.toLowerCase().contains("kline")){
                    return new BybitKlineGeneralParamMapping().convertGeneralParams(generalParams);
                }
                else{
                    return new BybitNonKlineGeneralParamMapping().convertGeneralParams(generalParams);
                }
            case "BINANCE":
                return new BinanceGeneralParamMapping().convertGeneralParams(generalParams);
            case "GATEIO":
                return new GateioGeneralParamMapping().convertGeneralParams(generalParams);
            case "KUCOIN":
                if(type.toLowerCase().contains("future")){
                    return new KucoinFutureGeneralParamMapping().convertGeneralParams(generalParams);
                } else if (type.toLowerCase().contains("spot")) {
                    return new KucoinSpotGeneralParamMapping().convertGeneralParams(generalParams);
                }
            default:
                break;
        }
        return null;
    }
}

