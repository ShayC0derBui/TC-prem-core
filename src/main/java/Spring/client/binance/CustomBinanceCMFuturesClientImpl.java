package Spring.client.binance;

import com.binance.connector.futures.client.enums.HttpMethod;
import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import com.binance.connector.futures.client.impl.cm_futures.CMAccount;
import java.util.LinkedHashMap;

public class CustomBinanceCMFuturesClientImpl {
    private final CMFuturesClientImpl cmFuturesClient;

    public CustomBinanceCMFuturesClientImpl(String apiKey, String apiSecret) {
        this.cmFuturesClient = new CMFuturesClientImpl(apiKey, apiSecret);
    }

    public CustomAccount account() {
        return new CustomAccount(cmFuturesClient.account());
    }

    // Other methods delegating to cmFuturesClient if necessary...

    public static class CustomAccount {
        private final CMAccount account;

        public CustomAccount(CMAccount account) {
            this.account = account;
        }

        public String modifyMultipleOrders(LinkedHashMap<String, Object> parameters) {
            return account.getRequestHandler().sendSignedRequest(account.getProductUrl(), "/v1/batchOrders", parameters, HttpMethod.PUT, account.getShowLimitUsage());
        }


    }
}
