package Spring.model.pojo.bybit.futures.perpetual;

import lombok.Data;

@Data
public class BybitGenericResponse<T> {
    private int retCode;
    private String retMsg;
    private BybitGenericResult<T> result;
}