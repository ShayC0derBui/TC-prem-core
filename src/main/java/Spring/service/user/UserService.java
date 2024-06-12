package Spring.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final KeysRepository keyRepository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }


    public void insertKeys(User user, Map<String, String> keys) {
        // There can be a case when when some keys are missing in the keys map, handle it
        Keys build = Keys.builder()
                .bybitApiKey(keys.getOrDefault("BYBIT_API_KEY",null))
                .bybitApiSecret(keys.getOrDefault("BYBIT_API_SECRET",null))
                .binanceApiKey(keys.getOrDefault("BINANCE_API_KEY",null))
                .binanceApiSecret(keys.getOrDefault("BINANCE_SECRET_KEY",null))
                .gateioApiKey(keys.getOrDefault("GATEIO_API_KEY",null))
                .gateioApiSecret(keys.getOrDefault("GATEIO_API_SECRET",null))
                .kucoinApiKey(keys.getOrDefault("KUCOIN_API_KEY",null))
                .kucoinApiSecret(keys.getOrDefault("KUCOIN_API_SECRET",null))
                .kucoinApiPassphrase(keys.getOrDefault("KUCOIN_API_PASSPHRASE",null))
                .user(user)
                .build();

        keyRepository.save(build);
    }

    public void updateKeys(User user, Map<String, String> keys) {
        Keys keys1 = keyRepository.findByUser(user).orElseThrow();
        keys1.setBybitApiKey(keys.getOrDefault("BYBIT_API_KEY",null));
        keys1.setBybitApiSecret(keys.getOrDefault("BYBIT_API_SECRET",null));
        keys1.setBinanceApiKey(keys.getOrDefault("BINANCE_API_KEY",null));
        keys1.setBinanceApiSecret(keys.getOrDefault("BINANCE_SECRET_KEY",null));
        keys1.setGateioApiKey(keys.getOrDefault("GATEIO_API_KEY",null));
        keys1.setGateioApiSecret(keys.getOrDefault("GATEIO_API_SECRET",null));
        keys1.setKucoinApiKey(keys.getOrDefault("KUCOIN_API_KEY",null));
        keys1.setKucoinApiSecret(keys.getOrDefault("KUCOIN_API_SECRET",null));
        keys1.setKucoinApiPassphrase(keys.getOrDefault("KUCOIN_API_PASSPHRASE",null));
        keyRepository.save(keys1);
    }
}
