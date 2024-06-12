package Spring.service.generalParams.general.kucoin;

import Spring.service.generalParams.general.GeneralParamMapping;

import static Spring.service.generalParams.general.ParamGeneralKeys.*;

public class KucoinGeneralParamMapping extends GeneralParamMapping {
    public KucoinGeneralParamMapping() {
        super();
        paramMapping.put(symbol.toString(), "symbol");
    }
}
