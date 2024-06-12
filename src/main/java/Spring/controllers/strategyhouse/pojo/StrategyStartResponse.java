package Spring.controllers.strategyhouse.pojo;

import com.google.gson.Gson;
import lombok.Data;

@Data
public class StrategyStartResponse {
    private String message;
    private long id;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }



}
