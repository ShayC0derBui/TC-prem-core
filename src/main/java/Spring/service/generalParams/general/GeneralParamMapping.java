package Spring.service.generalParams.general;

import java.util.HashMap;
import java.util.Map;

public abstract class GeneralParamMapping {
    protected Map<String, Object> paramMapping;

    public GeneralParamMapping() {
        this.paramMapping = new HashMap<String,Object>();
    }

    public Map<String, Object> convertGeneralParams(Map<String, Object> generalParams) {
        for (Map.Entry<String, Object> entry : paramMapping.entrySet()) {
            String generalParam = entry.getKey();
            Object exchangeParam = entry.getValue();

            if (generalParams.containsKey(generalParam)) {
                Object value = generalParams.remove(generalParam);
                generalParams.put((String) exchangeParam, value);
            }
        }
        return generalParams;
    }
}
