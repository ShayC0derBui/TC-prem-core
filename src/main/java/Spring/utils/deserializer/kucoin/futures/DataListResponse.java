package Spring.utils.deserializer.kucoin.futures;

import java.util.List;
import lombok.Data;

@Data
public class DataListResponse<T> {
    private List<T> data;
}