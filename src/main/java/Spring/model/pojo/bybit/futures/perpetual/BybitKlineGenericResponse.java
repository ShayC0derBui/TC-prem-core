package Spring.model.pojo.bybit.futures.perpetual;

import lombok.Data;

@Data
public class BybitKlineGenericResponse {
    private int retCode;
    private String retMsg;
    private BybitKlineGenericResult result;
}
