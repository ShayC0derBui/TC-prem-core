package Spring.utils.signatureGenerator.binanceSignatureGenerator;

import org.apache.http.client.methods.HttpRequestBase;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface BinanceSign {
    void addHeader(HttpRequestBase request);

    String getKey();

    String getSignature(String s) throws NoSuchAlgorithmException, InvalidKeyException;
}
