//package Spring.utils.signatureGenerator;
//
//import Spring.service.user.KeyService;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.apache.commons.codec.binary.Hex;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.http.client.methods.HttpRequestBase;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.time.Instant;
//import java.util.HashMap;
//import java.util.Map;
//
//public class KucoinSignatureGenerator {
//    public static final String API_KEY_HEADER = "KEY";
//    public static final String HMAC512Algo = "HmacSHA512";
//    public static final String SIGNATURE_HEADER = "SIGN";
//
//    public static final String TIMESTAMP_HEADER = "Timestamp";
//    private final KeyService keyService;
//    //    public static final Long timestamp = Instant.now().getEpochSecond(); this was causing request timeout
////    private final String apiKey;
////    private final String secretKey;
//
//    public KucoinSignatureGenerator(KeyService keyService) {
//        this.keyService = keyService;
//    }
//
//    public Map<String, String> createHeaders(HttpRequestBase request, Map<String, Object> bodyParams) {
//        String apiKey = keyService.getKeys().get("KUCOIN_API_KEY");
//        Map<String, String> headers = new HashMap<>();
//        headers.put(API_KEY_HEADER, apiKey);
//        headers.put(TIMESTAMP_HEADER, String.valueOf(Instant.now().getEpochSecond()));
//        String signature = createSignature(request, headers, bodyParams);
//        headers.put(SIGNATURE_HEADER, signature);
//        return headers;
//    }
//
//    private String createSignature(HttpRequestBase request, Map<String, String> headers, Map<String, Object> bodyParams) {
//        String secretKey = keyService.getKeys().get("KUCOIN_API_SECRET");
//        String method = request.getMethod().toUpperCase();
//        String endpoint = request.getURI().getPath();
//        String queryString = request.getURI().getQuery() == null ? "" : request.getURI().getQuery();
//        String body = "";
//
//        if (bodyParams != null && !bodyParams.isEmpty()) {
//            Gson gson = new GsonBuilder().create();
//            body = gson.toJson(bodyParams);
//        }
//
//        String stringToSign = method + "\n" +
//                endpoint + "\n" +
//                queryString + "\n" +
//                DigestUtils.sha512Hex(body) + "\n" +
//                headers.get(TIMESTAMP_HEADER);
//        String signature;
//        try {
//            Mac mac = Mac.getInstance(HMAC512Algo);
//            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC512Algo);
//            mac.init(secretKeySpec);
//            signature = Hex.encodeHexString(mac.doFinal(stringToSign.getBytes()));
//            return signature;
//        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "Error generating signature";
//    }
//
//    public Map<String, String> createHeadersCustom(HttpRequestBase request, Map<String, Object> bodyParams) {
//        String apiKey = keyService.getKeys().get("KUCOIN_API_KEY");
//        Map<String, String> headers = new HashMap<>();
//        headers.put(API_KEY_HEADER, apiKey);
//        headers.put(TIMESTAMP_HEADER, String.valueOf(Instant.now().getEpochSecond()));
//        String signature = createSignatureCustom(request, headers, bodyParams);
//        headers.put(SIGNATURE_HEADER, signature);
//        return headers;
//    }
//
//    private String createSignatureCustom(HttpRequestBase request, Map<String, String> headers, Map<String, Object> bodyParams) {
//        String secretKey = keyService.getKeys().get("KUCOIN_API_SECRET");
//        String method = request.getMethod().toUpperCase();
//        String endpoint = request.getURI().getPath();
//        String queryString = request.getURI().getQuery() == null ? "" : request.getURI().getQuery();
//        String body = "";
//
//        if (bodyParams != null && !bodyParams.isEmpty()) {
//            Gson gson = new GsonBuilder().create();
//            body = gson.toJson(bodyParams);
//        }
//
//        String stringToSign = method + "\n" +
//                endpoint + "\n" +
//                queryString + "\n" +
//                DigestUtils.sha512Hex(body) + "\n" +
//                headers.get(TIMESTAMP_HEADER);
//        String signature;
//        try {
//            Mac mac = Mac.getInstance(HMAC512Algo);
//            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), HMAC512Algo);
//            mac.init(secretKeySpec);
//            signature = Hex.encodeHexString(mac.doFinal(stringToSign.getBytes()));
//            return signature;
//        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return "Error generating signature";
//    }
//}
