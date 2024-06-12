package Spring.controllers.strategyhouse.pojo;

import lombok.Data;

@Data
public class RunningStrategyParams {
    private String strategyName;
    private String instrument;
    private String type;
    private String exchange;
    private String currencyPair;
    private String positionSize;
    private String leverage;
    private String resolution;
    private String rollingWindow;
    private String longThreshold;
    private String shortThreshold;


}
