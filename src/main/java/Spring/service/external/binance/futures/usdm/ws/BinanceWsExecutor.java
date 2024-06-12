package Spring.service.external.binance.futures.usdm.ws;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinanceWsExecutor {
    private static BinanceWsExecutor instance = null;
    private ExecutorService executor;
    private Map<URI, BinanceWsService> services;
    private static final Logger logger = Logger.getLogger(BinanceWsExecutor.class.getName());

    private BinanceWsExecutor() {
        // initializing the executor service with a fixed thread pool
        executor = Executors.newFixedThreadPool(10);  // adjust the size as per your requirement
        services = new ConcurrentHashMap<>();
    }

    public static BinanceWsExecutor getInstance() {
        if (instance == null) {
            synchronized (BinanceWsExecutor.class) {
                if (instance == null) {
                    instance = new BinanceWsExecutor();
                }
            }
        }
        return instance;
    }

    public void startWsConnection(URI endpointURI, BinanceWsMessageHandler messageHandler) {
        if (services.containsKey(endpointURI)) {
            logger.log(Level.INFO, "Connection to {0} is already running", endpointURI);
            return;
        }

        executor.execute(() -> {
            BinanceWsService service = new BinanceWsService(endpointURI, messageHandler);
            services.put(endpointURI, service);
            logger.log(Level.INFO, "Started WebSocket connection to {0}", endpointURI);
        });
    }

    public void subscribeToChannel(URI endpointURI, String channel) {
        if (!services.containsKey(endpointURI)) {
            logger.log(Level.WARNING, "No connection to {0}", endpointURI);
            return;
        }

        executor.execute(() -> {
            services.get(endpointURI).subscribeToChannel(channel);
            logger.log(Level.INFO, "Subscribed to channel {0} on {1}", new Object[]{channel, endpointURI});
        });
    }

    public void shutdown() {
        executor.shutdown();
        // You may also want to close all WebSocket connections here
        logger.log(Level.INFO, "Executor service shut down");
    }
}