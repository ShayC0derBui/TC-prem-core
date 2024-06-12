package Spring.service.generalParams.general.bybit;

import Spring.service.generalParams.general.GeneralParamMapping;
import Spring.service.generalParams.general.ParamGeneralKeys;

public class BybitGeneralParamMapping extends GeneralParamMapping {
    public BybitGeneralParamMapping() {
        super();
        paramMapping.put(ParamGeneralKeys.market.toString(), "category");
        paramMapping.put(ParamGeneralKeys.symbol.toString(), "symbol");
        paramMapping.put(ParamGeneralKeys.interval.toString(), "interval");
        paramMapping.put(ParamGeneralKeys.limit.toString(), "limit");
        paramMapping.put(ParamGeneralKeys.quantity.toString(), "qty");
        paramMapping.put(ParamGeneralKeys.price.toString(),"price");
        paramMapping.put(ParamGeneralKeys.orderId.toString(),"orderId");
        paramMapping.put(ParamGeneralKeys.type.toString(),"type");
        paramMapping.put(ParamGeneralKeys.timeInForce.toString(),"timeInForce");
    }
}
