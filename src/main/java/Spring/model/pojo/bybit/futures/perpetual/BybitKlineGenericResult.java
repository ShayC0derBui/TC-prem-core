package Spring.model.pojo.bybit.futures.perpetual;

import Spring.model.pojo.bybit.BybitKline;
import lombok.Data;

import java.util.List;

@Data
public class BybitKlineGenericResult {
    private String category;
    private List<BybitKline> list;
}
