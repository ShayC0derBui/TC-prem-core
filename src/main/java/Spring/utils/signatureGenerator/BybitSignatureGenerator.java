package Spring.utils.signatureGenerator;

import Spring.service.user.KeyService;
import org.apache.http.client.methods.HttpRequestBase;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class BybitSignatureGenerator {
    public static final String API_KEY_HEADER = "X-BAPI-API-KEY";
    public static final String TIMESTAMP_HEADER = "X-BAPI-TIMESTAMP";
    public static final String RECEIVE_WINDOW_HEADER = "X-BAPI-RECV-WINDOW";
    private final KeyService keyService;
    private final String RECV_WINDOW;

    public BybitSignatureGenerator(KeyService keyService) {
        this.keyService = keyService;
        this.RECV_WINDOW = "5000";
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private static StringBuilder genQueryStr(Map<String, Object> map) {
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            String key = iter.next();
            sb.append(key)
                    .append("=")
                    .append(map.get(key))
                    .append("&");
        }
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb;
    }

    public void addHeader(HttpRequestBase request) {
        String API_KEY = keyService.getKeys().get("BYBIT_API_KEY");
        String TIMESTAMP = Long.toString(ZonedDateTime.now().toInstant().toEpochMilli());
        request.setHeader(API_KEY_HEADER, API_KEY);
        System.out.println(API_KEY_HEADER + " : " + API_KEY);
        request.setHeader(TIMESTAMP_HEADER, TIMESTAMP);
        request.setHeader(RECEIVE_WINDOW_HEADER, RECV_WINDOW);
        request.setHeader("Content-type", "application/json");
//        System.out.println(request);
    }

    public String genPostSign(String params) throws NoSuchAlgorithmException, InvalidKeyException {
        String TIMESTAMP = Long.toString(ZonedDateTime.now().toInstant().toEpochMilli());
        String API_KEY = keyService.getKeys().get("BYBIT_API_KEY");
        String API_SECRET = keyService.getKeys().get("BYBIT_API_SECRET");
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(API_SECRET.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        String sb = TIMESTAMP + API_KEY + RECV_WINDOW + params;
        return bytesToHex(sha256_HMAC.doFinal(sb.getBytes()));
    }

    public String genGetSign(Map<String, Object> params) throws NoSuchAlgorithmException, InvalidKeyException {
        String TIMESTAMP = Long.toString(ZonedDateTime.now().toInstant().toEpochMilli());
        String API_KEY = keyService.getKeys().get("BYBIT_API_KEY");
        String API_SECRET = keyService.getKeys().get("BYBIT_API_SECRET");
        StringBuilder sb = genQueryStr(params);
        String queryStr = TIMESTAMP + API_KEY + RECV_WINDOW + sb;

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(API_SECRET.getBytes(), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        return bytesToHex(sha256_HMAC.doFinal(queryStr.getBytes()));
    }

}
