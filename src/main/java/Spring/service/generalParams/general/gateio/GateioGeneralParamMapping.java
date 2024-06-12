package Spring.service.generalParams.general.gateio;

import Spring.service.generalParams.general.GeneralParamMapping;
import Spring.service.generalParams.general.ParamGeneralKeys;

public class GateioGeneralParamMapping extends GeneralParamMapping {
    public GateioGeneralParamMapping() {
        super();
        paramMapping.put(ParamGeneralKeys.symbol.toString(), "currency_pair");
        paramMapping.put(ParamGeneralKeys.interval.toString(), "interval");
        paramMapping.put(ParamGeneralKeys.limit.toString(),"limit");
        paramMapping.put(ParamGeneralKeys.startTime.toString(),"from");
        paramMapping.put(ParamGeneralKeys.endTime.toString(),"to");
        paramMapping.put(ParamGeneralKeys.settle.toString(),"side");
    }
}
