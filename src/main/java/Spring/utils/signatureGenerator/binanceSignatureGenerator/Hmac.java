package Spring.utils.signatureGenerator.binanceSignatureGenerator;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import Spring.service.user.KeyService;
import org.apache.http.client.methods.HttpRequestBase;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;
public class Hmac implements BinanceSign{
    public static final String API_KEY_HEADER = "X-MBX-APIKEY";
    private final KeyService keyservice;

    public Hmac(KeyService keyService) {
        this.keyservice = keyService;
    }

    public void addHeader(HttpRequestBase request) {
        String apiKey = keyservice.getKeys().get("BINANCE_API_KEY");
        request.setHeader(API_KEY_HEADER, apiKey);
    }

//    public String signatureGenerator(Map<String,Object> parameters){
//        try {
//            String secretKey = keyservice.getKeys().get("BINANCE_API_SECRET");
//            // Convert your parameters map into a string
//            StringJoiner sj = new StringJoiner("&");
//            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
//                sj.add(entry.getKey() + "=" + entry.getValue());
//            }
//            String totalParams = sj.toString();
//
//            // Create a Mac instance with the HMACSHA256 algorithm
//            Mac mac = Mac.getInstance("HmacSHA256");
//
//            // Initialize the Mac with your secret key
//            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
//            mac.init(secretKeySpec);
//
//            // Compute the HMAC of the totalParams
//            byte[] hmac = mac.doFinal(totalParams.getBytes(StandardCharsets.UTF_8));
//
//            // Convert the HMAC to a hexadecimal string
//            String signature = bytesToHex(hmac);
//
//            // Append the signature at the end of the totalParams
//            String totalParamsWithSignature = totalParams + "&signature=" + signature;
//
//            System.out.println("Total Params with Signature: " + totalParamsWithSignature);
//            return totalParamsWithSignature;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//    }

    @Override
    public String getKey() {
        return keyservice.getKeys().get("BINANCE_API_KEY");
    }

    @Override
    public String getSignature(String s) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        String secretKey = keyservice.getKeys().get("BINANCE_API_SECRET");

        // Initialize the Mac with your secret key
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKeySpec);

        // Compute the HMAC of the totalParams
        byte[] hmac = mac.doFinal(s.getBytes(StandardCharsets.UTF_8));

        // Convert the HMAC to a hexadecimal string
        return bytesToHex(hmac);
    }



    // Method to convert byte array to hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
