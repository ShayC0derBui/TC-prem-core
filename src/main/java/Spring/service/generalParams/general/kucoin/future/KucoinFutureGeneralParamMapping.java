package Spring.service.generalParams.general.kucoin.future;

import Spring.service.generalParams.general.GeneralParamMapping;
import Spring.service.generalParams.general.kucoin.KucoinGeneralParamMapping;

import static Spring.service.generalParams.general.ParamGeneralKeys.*;

public class KucoinFutureGeneralParamMapping extends KucoinGeneralParamMapping {
    public KucoinFutureGeneralParamMapping() {
        super();
        paramMapping.put(interval.toString(),"granularity");
        paramMapping.put(startTime.toString(),"from");
        paramMapping.put(endTime.toString(),"to");
    }
}
