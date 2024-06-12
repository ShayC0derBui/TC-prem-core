package Spring.client;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public interface TCHttpClient {
    String get(String endpoint) throws IOException;
    String getSigned(String endpoint) throws IOException, Exception;
    String getWithQueryParams(String endpoint, Map<String, Object> queryParams) throws IOException, URISyntaxException;
    String getSignedWithQueryParams(String endpoint, Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException, Exception;
    String post(String endpoint, Map<String, Object> bodyParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException, Exception;
    String postCustomBody(String endpoint, Map<String, Object> bodyParams) throws IOException;
    String put(String endpoint, Map<String, Object> bodyParams) throws IOException, URISyntaxException;
    String delete(String endpoint, Map<String, Object> bodyParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException;
    String patch(String endpoint, String queryParamKey, String queryParamValue, Map<String, Object> bodyParams) throws URISyntaxException, IOException;
}
