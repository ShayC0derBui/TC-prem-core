package Spring.client.util;

import java.util.function.Supplier;

public class Retry {
    public <T> T executeWithRetry(Supplier<T> function) throws Exception {
        int maxAttempts = 5;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return function.get();
            } catch (Exception e) {
                if (attempt == maxAttempts) {
                    throw e;
                }
                System.out.println("Attempt " + attempt + " failed, retrying...");
            }
        }
        return null;
    }
}
