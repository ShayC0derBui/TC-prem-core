package Spring.utils.signatureGenerator.binanceSignatureGenerator;//package Spring.utils.signatureGenerator.binanceSignatureGenerator;
//
//import Spring.service.user.KeyService;
//import Spring.utils.urlBuilder.UrlBuilder;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.bouncycastle.util.io.pem.PemObject;
//import org.bouncycastle.util.io.pem.PemReader;
//
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.nio.charset.StandardCharsets;
//import java.security.*;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.util.Base64;
//import java.util.Map;
//
//public final class Ed25519 {
//
//    private final PrivateKey privateKey;
//    public static final String API_KEY_HEADER = "X-MBX-APIKEY";
//    private final KeyService keyservice;
//
//    public Ed25519(KeyService keyService) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
//        String PRIVATE_KEY_PATH = "src/main/resources/binancePrivateKey.pem";
//        this.keyservice = keyService;
//        // Read the PEM file
//        try (Reader reader = new FileReader(PRIVATE_KEY_PATH);
//             PemReader pemReader = new PemReader(reader)) {
//            PemObject pemObject = pemReader.readPemObject();
//            byte[] privateKeyBytes = pemObject.getContent();
//            // Decode the PEM content
//            privateKey = decodePrivateKey(privateKeyBytes);
//        }
//    }
//
//    // Decode the private key from PEM format
//    private PrivateKey decodePrivateKey(byte[] privateKeyBytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        KeyFactory kf = KeyFactory.getInstance("EdDSA");
//        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
//        return kf.generatePrivate(spec);
//    }
//
//    public String getSignature(Map<String, Object> params) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
//
////        String payload = String.join("&", params.entrySet().stream()
////                .map(e -> e.getKey() + "=" + e.getValue())
////                .toArray(String[]::new));
//        String payload = UrlBuilder.joinQueryParameters(params);
//        System.out.println(payload);
//        Signature signature = Signature.getInstance("EdDSA");
//        signature.initSign(privateKey);
//        signature.update(payload.getBytes(StandardCharsets.UTF_8));
//        byte[] signatureBytes = signature.sign();
//        return Base64.getEncoder().encodeToString(signatureBytes);
//    }
//
//    public String signatureGenerator(Map<String, Object> parameters) {
//        String signature = "";
//        try {
//            // Generate signature
//            signature = getSignature(parameters);
//
//            // Append the signature at the end of the totalParams
//            System.out.println("Signature: " + signature);
////            String totalParamsWithSignature = totalParams + "&signature=" + signature;
//
////            System.out.println("Total Params with Signature: " + totalParamsWithSignature);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return signature;
//    }
//    public void addHeader(HttpRequestBase request) {
//        String apiKey = keyservice.getKeys().get("BINANCE_API_KEY");
//        System.out.println("API Key: " + apiKey);
//        request.setHeader(API_KEY_HEADER, apiKey);
//    }
//
//    public String getKey() {
//        return keyservice.getKeys().get("BINANCE_API_KEY");
//    }
//}


import Spring.service.user.KeyService;
import org.apache.http.client.methods.HttpRequestBase;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.crypto.util.PrivateKeyFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;


public final class Ed25519 implements BinanceSign {

    public static final String API_KEY_HEADER = "X-MBX-APIKEY";
    private final int offset = 0;
    private final KeyService keyservice;
    private Ed25519PrivateKeyParameters privateKey;

    public Ed25519(KeyService keyservice) throws FileNotFoundException, IOException {
        this.keyservice = keyservice;
        String PRIVATE_KEY_PATH = "src/main/resources/binancePrivateKey.pem";

        Security.addProvider(new BouncyCastleProvider());
        PemReader pemReader = new PemReader(new FileReader(PRIVATE_KEY_PATH));
        PemObject pemObject = pemReader.readPemObject();
        byte[] privateKeyBytes = pemObject.getContent();
        this.privateKey = (Ed25519PrivateKeyParameters) PrivateKeyFactory.createKey(privateKeyBytes);
        pemReader.close();
    }

    public String getSignature(String data) {

        byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);

        Ed25519Signer signer = new Ed25519Signer();
        signer.init(true, this.privateKey);
        signer.update(dataBytes, offset, dataBytes.length);
        byte[] signatureBytes = signer.generateSignature();
        return Base64.getEncoder().encodeToString(signatureBytes);

    }

    public void addHeader(HttpRequestBase request) {
        String apiKey = keyservice.getKeys().get("BINANCE_API_KEY");
        System.out.println("API Key: " + apiKey);
        request.setHeader(API_KEY_HEADER, apiKey);
    }


    public String getKey() {
        return keyservice.getKeys().get("BINANCE_API_KEY");
    }

}