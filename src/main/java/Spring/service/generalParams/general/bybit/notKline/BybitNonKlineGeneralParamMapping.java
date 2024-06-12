package Spring.service.generalParams.general.bybit.notKline;

import Spring.service.generalParams.general.bybit.BybitGeneralParamMapping;
import Spring.service.generalParams.general.ParamGeneralKeys;

public class BybitNonKlineGeneralParamMapping extends BybitGeneralParamMapping {
    public BybitNonKlineGeneralParamMapping() {
        super();
        paramMapping.put(ParamGeneralKeys.startTime.toString(),"startTime");
        paramMapping.put(ParamGeneralKeys.endTime.toString(),"endTime");

    }
}
