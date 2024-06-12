package Spring.security.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private LogoutHandler logoutHandler;

    //  @PostMapping("/register")
//  public ResponseEntity<AuthenticationResponse> register(
//      @RequestBody RegisterRequest request
//  ) {
//    return ResponseEntity.ok(service.register(request));
//  }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse cookie = service.authenticate(request);
        System.out.println(request.getBybitApiKey());

//        if (request.getBybitApiKey() != null && request.getBybitApiSecret() != null) {
//            System.setProperty("BYBIT_API_KEY", request.getBybitApiKey());
//            System.setProperty("BYBIT_API_SECRET", request.getBybitApiSecret());
//        }
//        if (request.getBinanceApiKey() != null && request.getBinanceApiSecret() != null) {
//            System.setProperty("BINANCE_API_KEY", request.getBinanceApiKey());
//            System.setProperty("BINANCE_SECRET_KEY", request.getBinanceApiSecret());
//        }
//        if (request.getGateioApiKey() != null && request.getGateioApiSecret() != null) {
//            System.setProperty("GATEIO_API_KEY", request.getGateioApiKey());
//            System.setProperty("GATEIO_API_SECRET", request.getGateioApiSecret());
//        }
//        if (request.getKucoinApiKey() != null && request.getKucoinApiSecret() != null && request.getKucoinApiPassphrase() != null) {
//            System.setProperty("KUCOIN_API_KEY", request.getKucoinApiKey());
//            System.setProperty("KUCOIN_API_SECRET", request.getKucoinApiSecret());
//            System.setProperty("KUCOIN_API_PASSPHRASE", request.getKucoinApiPassphrase());
//        }


        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + cookie.getAccessToken())
                .header("Set-Cookie", "accessToken=" + cookie.getAccessToken() + ";HttpOnly;Path=/;")
                .header("Set-Cookie", "refreshToken=" + cookie.getRefreshToken() + ";HttpOnly;Path=/;")
                .body(cookie);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthenticationResponse> logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        logoutHandler.logout(request, response, authentication);
        return ResponseEntity.ok().build();
    }
}
