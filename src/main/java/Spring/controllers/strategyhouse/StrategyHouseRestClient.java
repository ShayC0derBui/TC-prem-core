//package Spring.controllers.strategyhouse;
//
//import Spring.controllers.strategyhouse.pojo.RunningStrategy;
//import Spring.controllers.strategyhouse.pojo.StrategyStartResponse;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class StrategyHouseRestClient {
//
//    private final WebClient webClient;
//
//    public StrategyHouseRestClient() {
//        this.webClient = WebClient.builder()
//                .baseUrl("http://127.0.0.1:8000")
//                .build();
//    }
//
//    public Mono<String> stopAll() {
//        return webClient.delete()
//                .uri("/stopAll")
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//
//    public Mono<String> stopStrategy(String id) {
//        return webClient.delete()
//                .uri("/stop/" + id)
//                .retrieve()
//                .bodyToMono(String.class);
//    }
//
//    public Mono<StrategyStartResponse> startStrategy(Map<String, String> requestBody) {
//        return webClient.post()
//                .uri("/start")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(requestBody)
//                .retrieve()
//                .bodyToMono(StrategyStartResponse.class);
//    }
//
//    public Mono<List<RunningStrategy>> getRunning() {
//        return webClient.get()
//                .uri("/getRunning")
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<RunningStrategy>>() {});
//    }
//
//
//}