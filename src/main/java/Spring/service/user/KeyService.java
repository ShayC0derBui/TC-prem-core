package Spring.service.user;

import Spring.security.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class KeyService {
    private final ThreadLocal<Map<String, String>> keys = new ThreadLocal<>(){
        @Override
        protected Map<String, String> initialValue() {
            return new HashMap<>();
        }
    };
    @Autowired
    private JwtService jwtService;

    public void loadKeys(String jwt) {
        Keys keys1 = jwtService.getKeys(jwt).orElseThrow();
        Map<String, String> map = new HashMap<>();
        map.put("BYBIT_API_KEY", keys1.getBybitApiKey());
        map.put("BYBIT_API_SECRET", keys1.getBybitApiSecret());
        map.put("BINANCE_API_KEY", keys1.getBinanceApiKey());
        map.put("BINANCE_API_SECRET", keys1.getBinanceApiSecret());
        map.put("GATEIO_API_KEY", keys1.getGateioApiKey());
        map.put("GATEIO_API_SECRET", keys1.getGateioApiSecret());
        map.put("KUCOIN_API_KEY", keys1.getKucoinApiKey());
        map.put("KUCOIN_API_SECRET", keys1.getKucoinApiSecret());
        map.put("KUCOIN_API_PASSPHRASE", keys1.getKucoinApiPassphrase());
        keys.set(map);
    }

    public Map<String, String> getKeys() {
//        System.out.println(keys);
        return keys.get();
    }

    public void clearKeys() {
        keys.remove();
    }
}
