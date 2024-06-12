package Spring.client.bybit;
import Spring.client.TCHttpClient;
import Spring.service.user.KeyService;
import Spring.utils.signatureGenerator.BybitSignatureGenerator;
import org.json.JSONObject;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class BybitHttpClient implements TCHttpClient {
    private final CloseableHttpClient httpClient;
    private final String baseUrl;
    private final BybitSignatureGenerator signatureHandler;

    @Autowired
    public BybitHttpClient(String baseUrl, KeyService keyService) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.createDefault();
        this.signatureHandler = new BybitSignatureGenerator(keyService);
    }

//    Map<String,String> keys = keyService.getKeys();
//    private final String bybitApiKey = keys.get("BYBIT_API_KEY");
//    private final String bybitSecretKey = keys.get("BYBIT_API_SECRET");
//
//    private final BybitSignatureGenerator signatureHandler = new BybitSignatureGenerator(bybitApiKey,bybitSecretKey);


    @Override
    public String get(String endpoint) throws IOException {
        HttpGet request = new HttpGet(baseUrl + endpoint);
        request.addHeader("Accept", "application/json");

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            responseBody = EntityUtils.toString(response.getEntity());
        }
        return responseBody;
    }

    @Override
    public String getSigned(String endpoint) throws IOException {
        return null;
    }

    @Override
    public String getWithQueryParams(String endpoint, Map<String, Object> queryParams) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
        for(Map.Entry<String, Object> entry : queryParams.entrySet()){
            uriBuilder.addParameter(entry.getKey(), (String) entry.getValue());
        }
        HttpGet request = new HttpGet(uriBuilder.build());
        request.addHeader("Accept", "application/json");

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            responseBody = EntityUtils.toString(response.getEntity());
        }
        return responseBody;
    }

    @Override
    public String post(String endpoint, Map<String, Object> bodyParams) throws NoSuchAlgorithmException, InvalidKeyException {
        endpoint = baseUrl + endpoint;
        JSONObject jsonObject = new JSONObject(bodyParams);
        String jsonMap = jsonObject.toString();
        HttpPost httpPost = new HttpPost(endpoint);
        StringEntity entity = new StringEntity(jsonMap, "UTF-8");
        httpPost.setEntity(entity);
        signatureHandler.addHeader(httpPost);
        String signature=signatureHandler.genPostSign(jsonMap);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setHeader("X-BAPI-SIGN", signature);


        String responseBody = "";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            org.apache.http.HttpResponse response = client.execute(httpPost);
            System.out.println("Status code: " + response.getStatusLine().getStatusCode());
            responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response body: " + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBody;

    }

    @Override
    public String postCustomBody(String endpoint, Map<String, Object> bodyParams) throws IOException {
        return null;
    }

    @Override
    public String put(String endpoint, Map<String, Object> bodyParams) throws IOException, URISyntaxException {
        return null;
    }

    @Override
    public String delete(String endpoint, Map<String, Object> bodyParams) throws IOException, URISyntaxException {
        return null;
    }

    @Override
    public String patch(String endpoint, String queryParamKey, String queryParamValue, Map<String, Object> bodyParams) throws URISyntaxException, IOException {
        return null;
    }

    @Override
    public String getSignedWithQueryParams(String endpoint, Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        endpoint = baseUrl + endpoint;
        URIBuilder uriBuilder = new URIBuilder(endpoint);
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
        }
        URI uri = uriBuilder.build();
        HttpGet httpGet = new HttpGet(uri);
        signatureHandler.addHeader(httpGet);
        String signature=signatureHandler.genGetSign(queryParams);
        httpGet.setHeader("X-BAPI-SIGN", signature);
        signatureHandler.addHeader(httpGet);
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            org.apache.http.HttpResponse response = client.execute(httpGet);
            System.out.println("Status code: " + response.getStatusLine().getStatusCode());
            String responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response body: " + responseBody);
            return responseBody;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}