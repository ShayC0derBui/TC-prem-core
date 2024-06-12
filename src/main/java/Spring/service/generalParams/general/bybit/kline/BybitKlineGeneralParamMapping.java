package Spring.service.generalParams.general.bybit.kline;

import Spring.service.generalParams.general.bybit.BybitGeneralParamMapping;
import Spring.service.generalParams.general.ParamGeneralKeys;

public class BybitKlineGeneralParamMapping extends BybitGeneralParamMapping {
    public BybitKlineGeneralParamMapping() {
        super();
        paramMapping.put(ParamGeneralKeys.startTime.toString(),"start");
        paramMapping.put(ParamGeneralKeys.endTime.toString(),"end");
    }
}
