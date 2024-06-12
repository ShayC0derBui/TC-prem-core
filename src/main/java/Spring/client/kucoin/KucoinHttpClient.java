//package Spring.client.kucoin;
//
//import Spring.client.TCHttpClient;
//import Spring.service.user.KeyService;
//import Spring.utils.constants.kucoin.futures.KucoinFuturesConstants;
//import Spring.utils.constants.kucoin.spot.KucoinConstants;
//import Spring.utils.signatureGenerator.KucoinSignatureGenerator;
//import io.github.cdimascio.dotenv.Dotenv;
//import Spring.service.external.kucoin.spot.restapi.HttpDeleteWithBody;
//import com.google.gson.Gson;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.*;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.nio.charset.StandardCharsets;
//import java.time.Instant;
//import java.util.Base64;
//import java.util.Map;
//public class KucoinHttpClient implements TCHttpClient {
//    private final CloseableHttpClient httpClient;
//    private final String baseUrl;
//    private final KucoinSignatureGenerator signatureHandler;
//
//    @Autowired
//    public KucoinHttpClient(String baseUrl, KeyService keyService) {
//        this.baseUrl = baseUrl;
//        this.httpClient = HttpClients.createDefault();
//        this.signatureHandler = new KucoinSignatureGenerator(keyService);
//    }
//
////    Map<String,String> keys = keyService.getKeys();
////    private final String API_KEY = keys.get("KUCOIN_API_KEY");
////    private final String API_SECRET = keys.get("KUCOIN_API_SECRET");
////    private final String API_PASSPHRASE = keys.get("KUCOIN_API_PASSPHRASE");
////
////    private KucoinSignatureGenerator signatureHandler=new KucoinSignatureGenerator(API_KEY,API_SECRET);
//
//    private static String generateSignature(String secret, String data) throws Exception {
//        Mac sha256HMAC = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
//        sha256HMAC.init(secretKeySpec);
//        byte[] hash = sha256HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
//        return Base64.getEncoder().encodeToString(hash);
//    }
//
//    private String sendRequest(HttpUriRequest request) throws IOException {
//        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            HttpEntity entity = response.getEntity();
//            return EntityUtils.toString(entity);
//        }
//    }
//
//    @Override
//    public String get(String endpoint) throws IOException {
//        HttpGet request = new HttpGet(baseUrl + endpoint);
//        request.addHeader("Accept", "application/json");
//
//        String responseBody;
//        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            responseBody = EntityUtils.toString(response.getEntity());
//        }
//        return responseBody;
//    }
//
//    @Override
//    public String getWithQueryParams(String endpoint, Map<String, Object> queryParams) throws IOException, URISyntaxException {
//        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
//        for(Map.Entry<String, Object> entry : queryParams.entrySet()) {
//            uriBuilder.addParameter(entry.getKey(), (String) entry.getValue());
//        }
//        HttpGet request = new HttpGet(uriBuilder.build());
//        request.addHeader("Accept", "application/json");
//
//        String responseBody;
//        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            HttpEntity entity = response.getEntity();
//            responseBody = EntityUtils.toString(entity);
//        }
//        return responseBody;
//    }
//
//    @Override
//    public String getSigned(String endpoint) throws Exception {
//        long now = Instant.now().toEpochMilli();
//        String strToSign = now + "GET" + endpoint;
//
//        String signature = generateSignature(API_SECRET, strToSign);
//        String passphrase = generateSignature(API_SECRET, API_PASSPHRASE);
//
//        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
//        HttpGet request = new HttpGet(uriBuilder.build());
//
//        request.addHeader("KC-API-SIGN", signature);
//        request.addHeader("KC-API-TIMESTAMP", String.valueOf(now));
//        request.addHeader("KC-API-KEY", API_KEY);
//        request.addHeader("KC-API-PASSPHRASE", passphrase);
//        request.addHeader("KC-API-KEY-VERSION", "2");
//
//        String response = sendRequest(request);
//        System.out.println(response);
//        return response;
//    }
//
//    @Override
//    public String getSignedWithQueryParams(String endpoint, Map<String, Object> queryParams) throws Exception {
//        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
//
//        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
//            uriBuilder.addParameter(entry.getKey(), (String) entry.getValue());
//        }
//
//        long now = Instant.now().toEpochMilli();
//        String strToSign = now + "GET" + uriBuilder.build().toString().replace(baseUrl, "");
//        String signature = generateSignature(API_SECRET, strToSign);
//        String passphrase = generateSignature(API_SECRET, API_PASSPHRASE);
//
//        HttpGet request = new HttpGet(uriBuilder.build());
//
//        request.addHeader("KC-API-SIGN", signature);
//        request.addHeader("KC-API-TIMESTAMP", String.valueOf(now));
//        request.addHeader("KC-API-KEY", API_KEY);
//        request.addHeader("KC-API-PASSPHRASE", passphrase);
//        request.addHeader("KC-API-KEY-VERSION", "2");
//
//        return sendRequest(request);
//    }
//
//    public String kucoinSignedDeleteRequest(String endpoint) throws Exception {
//        long now = Instant.now().toEpochMilli();
//        String strToSign = now + "DELETE" + endpoint;
//
//        String signature = generateSignature(API_SECRET, strToSign);
//        String passphrase = generateSignature(API_SECRET, API_PASSPHRASE);
//
//        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
//        HttpDelete request = new HttpDelete(uriBuilder.build());
//
//        request.addHeader("KC-API-SIGN", signature);
//        request.addHeader("KC-API-TIMESTAMP", String.valueOf(now));
//        request.addHeader("KC-API-KEY", API_KEY);
//        request.addHeader("KC-API-PASSPHRASE", passphrase);
//        request.addHeader("KC-API-KEY-VERSION", "2");
//
//        String response = sendRequest(request);
//        System.out.println(response);
//        return response;
//    }
//
//    @Override
//    public String post(String endpoint, Map<String, Object> bodyParams) throws Exception {
//        long now = Instant.now().toEpochMilli();
//
//        Gson gson = new Gson();
//        String payload = gson.toJson(bodyParams);
//        StringEntity entity = new StringEntity(payload);
//
//        String strToSign = now + "POST" + endpoint + payload;
//        String signature = generateSignature(API_SECRET, strToSign);
//        String passphrase = generateSignature(API_SECRET, API_PASSPHRASE);
//
//        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
//        HttpPost request = new HttpPost(uriBuilder.build());
//        request.setEntity(entity);
//
//        request.addHeader("KC-API-SIGN", signature);
//        request.addHeader("KC-API-TIMESTAMP", String.valueOf(now));
//        request.addHeader("KC-API-KEY", API_KEY);
//        request.addHeader("KC-API-PASSPHRASE", passphrase);
//        request.addHeader("KC-API-KEY-VERSION", "2");
//        request.addHeader("Content-type", "application/json");
//
//        String response = sendRequest(request);
//        System.out.println(response);
//        return response;
//    }
//
//    @Override
//    public String delete(String endpoint, Map<String, Object> bodyParams) throws IOException, URISyntaxException {
//        return null;
//    }
//
//    @Override
//    public String postCustomBody(String endpoint, Map<String, Object> bodyParams) throws IOException {
//        return null;
//    }
//
//    @Override
//    public String put(String endpoint, Map<String, Object> bodyParams) throws IOException, URISyntaxException {
//        return null;
//    }
//
//    @Override
//    public String patch(String endpoint, String queryParamKey, String queryParamValue, Map<String, Object> bodyParams) throws URISyntaxException, IOException {
//        return null;
//    }
//}
//
//
//
//
//
