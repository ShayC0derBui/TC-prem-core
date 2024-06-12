package Spring.model.pojo.bybit.futures.perpetual;

import lombok.Data;

import java.util.List;

@Data
public class BybitGenericResult<T> {
    private String symbol;
    private String category;
    private List<T> list;
}
