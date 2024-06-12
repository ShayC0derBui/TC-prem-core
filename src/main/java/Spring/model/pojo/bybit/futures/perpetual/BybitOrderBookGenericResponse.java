package Spring.model.pojo.bybit.futures.perpetual;

import lombok.Data;

@Data
public class BybitOrderBookGenericResponse {
    private Integer retCode;
    private String retMsg;
    private BybitOrderBookGenericResult result;
}
