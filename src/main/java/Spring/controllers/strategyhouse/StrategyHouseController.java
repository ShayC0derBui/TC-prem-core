//package Spring.controllers.strategyhouse;
//
//
//import Spring.controllers.strategyhouse.pojo.RunningStrategy;
//import Spring.controllers.strategyhouse.pojo.StrategyStartResponse;
//import com.google.gson.Gson;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/strategyhouse")
//public class StrategyHouseController {
//
//    private final StrategyHouseRestClient client;
//
//    public StrategyHouseController(StrategyHouseRestClient client) {
//        this.client = client;
//    }
//
//
//    @DeleteMapping("/stop/{id}")
//    public ResponseEntity<String> stopStrategy(@PathVariable("id") String id){
//
//        System.out.println("Trying to stop: " + id);
//
//        try{
//            String response = client.stopStrategy(id).block();
//            System.out.println(response);
//
//            if (response.contains("message")){
//                return ResponseEntity.ok().body(response);
//            }
//            else {
//                return ResponseEntity.badRequest().body(response);
//            }
//        } catch (Exception e){
//            return ResponseEntity.internalServerError().body("Cant stop process " + id + ", make sure to wait 30 sec after turning on the strategy");
//        }
//    }
//
//
//    @DeleteMapping("/stopAll")
//    public ResponseEntity<String> stopAll() {
//        try{
//            String response = client.stopAll().block();
//
//            if (response.contains("message")){
//                return ResponseEntity.ok().body(response);
//            }
//            else {
//                return ResponseEntity.badRequest().body(response);
//            }
//        } catch (Exception e){
//            return ResponseEntity.internalServerError().body("Cant stop all, make sure to wait 30 sec after most recent strategy start");
//        }
//    }
//
//    @GetMapping("/getRunning")
//    public ResponseEntity<String> getRunning() {
//        try {
//            List<RunningStrategy> runningStrategies = client.getRunning().block();
//            for(RunningStrategy r: runningStrategies){
//                System.out.println(r);
//            }
//            Gson gson = new Gson();
//            return ResponseEntity.ok().body(gson.toJson(runningStrategies));
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Error fetching running strategies, check logs");
//        }
//    }
//
//
//    @PostMapping("/start")
//    public ResponseEntity<String> startStrategy(@RequestBody Map<String, String> request) {
//
//        /**
//         * Enable and make sure of the necessary data streaming here, should be based on request params
//         * rn we are sending whatever because it is not being used in the code anywhere
//         *
//         * IMPORTANT: assign different group-id to each of the strategies, otherwise they compete for data
//         */
//
//
//        try {
//            StrategyStartResponse response = client.startStrategy(request).block();
//            if (response != null && "Process started".equals(response.getMessage())) {
//                System.out.println("Process started, ID: " + response.getId());
//                return ResponseEntity.ok().body(response.toJson());
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unexpected response received");
//            }
//        } catch (Exception e) {
//            return ResponseEntity.internalServerError().body("Error starting strategy, check logs");
//        }
//    }
//}