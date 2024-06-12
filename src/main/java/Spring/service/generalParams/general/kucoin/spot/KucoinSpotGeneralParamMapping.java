package Spring.service.generalParams.general.kucoin.spot;

import Spring.service.generalParams.general.kucoin.KucoinGeneralParamMapping;

import static Spring.service.generalParams.general.ParamGeneralKeys.*;

public class KucoinSpotGeneralParamMapping extends KucoinGeneralParamMapping {
    public KucoinSpotGeneralParamMapping() {
        super();
        paramMapping.put(startTime.toString(), "startAt");
        paramMapping.put(endTime.toString(), "endAt");
        paramMapping.put(interval.toString(), "type");
    }
}
