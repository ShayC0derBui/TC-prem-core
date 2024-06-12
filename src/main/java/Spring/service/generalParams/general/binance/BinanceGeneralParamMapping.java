package Spring.service.generalParams.general.binance;

import Spring.service.generalParams.general.GeneralParamMapping;
import Spring.service.generalParams.general.ParamGeneralKeys;

public class BinanceGeneralParamMapping extends GeneralParamMapping {
    public BinanceGeneralParamMapping() {
        super();
        paramMapping.put(ParamGeneralKeys.symbol.toString(),"symbol");
        paramMapping.put(ParamGeneralKeys.limit.toString(),"limit");
        paramMapping.put(ParamGeneralKeys.interval.toString(),"interval");
        paramMapping.put(ParamGeneralKeys.orderId.toString(),"orderId");
        paramMapping.put(ParamGeneralKeys.fromId.toString(),"fromId");
        paramMapping.put(ParamGeneralKeys.startTime.toString(),"startTime");
        paramMapping.put(ParamGeneralKeys.endTime.toString(),"endTime");
        paramMapping.put(ParamGeneralKeys.side.toString(),"side");
        paramMapping.put(ParamGeneralKeys.type.toString(),"type");
        paramMapping.put(ParamGeneralKeys.quantity.toString(),"quantity");
        paramMapping.put(ParamGeneralKeys.price.toString(),"price");
        paramMapping.put(ParamGeneralKeys.timeInForce.toString(),"timeInForce");

    }
}
