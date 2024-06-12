package Spring.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String password;
    private String email;
    private String gateioApiKey;
    private String gateioApiSecret;
    private String kucoinApiKey;
    private String kucoinApiSecret;
    private String kucoinApiPassphrase;
    private String binanceApiKey;
    private String binanceApiSecret;
    private String bybitApiKey;
    private String bybitApiSecret;

}
