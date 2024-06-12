package Spring.security.auth;

import Spring.security.config.JwtService;
import Spring.security.token.Token;
import Spring.security.token.TokenRepository;
import Spring.security.token.TokenType;
import Spring.service.user.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final KeysRepository keysRepository;
    private final KeyService keyService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        var savedUser = repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        Keys keys = Keys.builder()
                .user(savedUser)
                .bybitApiKey("test")
                        .build();
        keysRepository.save(keys);
        saveUserToken(savedUser, jwtToken, keys);
        keyService.loadKeys(jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);


        Keys keys = Keys.builder()
                .bybitApiKey(request.getBybitApiKey())
                .bybitApiSecret(request.getBybitApiSecret())
                .binanceApiKey(request.getBinanceApiKey())
                .binanceApiSecret(request.getBinanceApiSecret())
                .gateioApiKey(request.getGateioApiKey())
                .gateioApiSecret(request.getGateioApiSecret())
                .kucoinApiKey(request.getKucoinApiKey())
                .kucoinApiSecret(request.getKucoinApiSecret())
                .kucoinApiPassphrase(request.getKucoinApiPassphrase())
                .user(user)
                .build();

        keysRepository.findByUser(user).ifPresent(keysRepository::delete);
        keysRepository.save(keys);

        saveUserToken(user, jwtToken, keys);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken, Keys keys) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .keys(keys)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty()) return;
        tokenRepository.deleteAll(validUserTokens);

//        tokenRepository.deleteAll();
//    validUserTokens.forEach(token -> {
//      token.setExpired(true);
//      token.setRevoked(true);
//    });
//    tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.repository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                Keys keys = keysRepository.findByUser(user).orElseThrow();
                saveUserToken(user, accessToken, keys);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper()
                        .writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
