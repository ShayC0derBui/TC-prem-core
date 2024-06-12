package Spring.client.binance;

import com.binance.connector.futures.client.enums.HttpMethod;
import com.binance.connector.futures.client.impl.CMFuturesClientImpl;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.binance.connector.futures.client.impl.cm_futures.CMAccount;
import com.binance.connector.futures.client.impl.um_futures.UMAccount;

import java.util.LinkedHashMap;

public class CustomBinanceUMFutuesClientImpl {
    private final UMFuturesClientImpl umFuturesClient;

    public CustomBinanceUMFutuesClientImpl(String apiKey, String apiSecret) {
        this.umFuturesClient = new UMFuturesClientImpl(apiKey, apiSecret);
    }

    public CustomAccount account() {
        return new CustomAccount(umFuturesClient.account());
    }

    // Other methods delegating to cmFuturesClient if necessary...

    public static class CustomAccount {
        private final UMAccount account;

        public CustomAccount(UMAccount account) {
            this.account = account;
        }

        public String modifyOrder(LinkedHashMap<String, Object> parameters) {
            return account.getRequestHandler().sendSignedRequest(account.getProductUrl(), "/v1/order", parameters, HttpMethod.PUT, account.getShowLimitUsage());
        }

        public String modifyMultipleOrders(LinkedHashMap<String, Object> parameters) {
            return account.getRequestHandler().sendSignedRequest(account.getProductUrl(), "/v1/batchOrders", parameters, HttpMethod.PUT, account.getShowLimitUsage());
        }


    }
}
