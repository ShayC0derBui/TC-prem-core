package Spring.controllers.strategyhouse.pojo;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class RunningStrategy {
    private long id;
    @SerializedName("request_body")
    private RunningStrategyParams request_body;
    @SerializedName("time_created")
    private double time_created;

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}