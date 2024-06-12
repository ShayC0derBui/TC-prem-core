package Spring.client.binance;

import Spring.client.TCHttpClient;
import Spring.service.user.KeyService;
import Spring.utils.signatureGenerator.binanceSignatureGenerator.BinanceSign;
import Spring.utils.signatureGenerator.binanceSignatureGenerator.Ed25519;
import Spring.utils.signatureGenerator.binanceSignatureGenerator.Hmac;
import Spring.utils.urlBuilder.UrlBuilder;
import okhttp3.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class BinanceHttpClient implements TCHttpClient {

    private static final String CONTENT_TYPE = "application/json;charset=utf-8";
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    private final CloseableHttpClient httpClient;
    private final String baseUrl;
    private final KeyService keyService;
    private BinanceSign signatureHandler;


    public BinanceHttpClient(String baseUrl, KeyService keyService) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.createDefault();
        this.signatureHandler = new Ed25519(keyService);
        this.keyService = keyService;
    }

    // Map<String,String> keys = keyService.getKeys();
    // private final String binanceApiKey = keys.get("BINANCE_API_KEY");
    // private final String binanceSecretKey = keys.get("BINANCE_SECRET_KEY");
    // private final Hmac signatureHandler = new Hmac(binanceApiKey, binanceSecretKey);

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
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), (String) entry.getValue());
        }
        HttpGet request = new HttpGet(uriBuilder.build());
        request.addHeader("Accept", "application/json");

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;
    }

    @Override
    public String post(String endpoint, Map<String, Object> bodyParams) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
//        System.out.println("body params: "+bodyParams);
//        HttpPost request = new HttpPost(baseUrl+endpoint);
        if (endpoint.contains("dapi")) {
            this.signatureHandler = new Hmac(this.keyService);
        }
        String key = signatureHandler.getKey();
        String signature = signatureHandler.getSignature(UrlBuilder.joinQueryParameters(bodyParams));
        String fullUrl = UrlBuilder.buildFullUrl(baseUrl + endpoint, "", bodyParams, signature);
        System.out.println("Full URL: " + fullUrl);
        final Request.Builder requestBuilder = new Request.Builder()
                .addHeader("Content-Type", CONTENT_TYPE)
                .addHeader("X-MBX-APIKEY", key)
                .url(fullUrl);
//        bodyParams.put("signature", signature);
        Request request = requestBuilder.post(RequestBody.create("",JSON_TYPE)).build();
        System.out.println("Request: " + request);
//        System.out.println(bodyParams);

//        FormBody.Builder formBuilder = new FormBody.Builder();
//        for (Map.Entry<String, Object> entry : bodyParams.entrySet()) {
//            formBuilder.add(entry.getKey(),  entry.getValue().toString());
//        }
//        RequestBody requestBody = formBuilder.build();
//        Request request = new Request.Builder()
//                .url(baseUrl + endpoint)
//                .post(requestBody)
//                .addHeader("X-MBX-APIKEY", key)
//                .build();
        OkHttpClient client = new OkHttpClient();
        Response response = null;

        String res = "";

        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                System.out.println("Unexpected code " + response);
                res = response.body() != null ? response.body().string() : "No response body";
                System.out.println("Response body: " + res);
            } else {
                res = response.body() != null ? response.body().string() : "No response body";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return res;
    }

    @Override
    public String postCustomBody(String endpoint, Map<String, Object> bodyParams) throws IOException {
        return null;
    }

    /* TODO: Implement this method */
    @Override
    public String put(String endpoint, Map<String, Object> bodyParams) throws IOException, URISyntaxException {
//        String signedString = signatureHandler.signatureGenerator(bodyParams);
//        URIBuilder uriBuilder = new URIBuilder(endpoint + "?" + signedString);
//        HttpPut request = new HttpPut(uriBuilder.build());
//        signatureHandler.addHeader(request);
//        String responseBody;
//        try (CloseableHttpResponse response = httpClient.execute(request)) {
//            HttpEntity entity = response.getEntity();
//            responseBody = EntityUtils.toString(entity);
//            System.out.println("Response body: " + responseBody);
//        }
//        return responseBody;
        return null;
    }

    @Override
    public String delete(String endpoint, Map<String, Object> queryParams) throws IOException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {

        String signedString = signatureHandler.getSignature(UrlBuilder.joinQueryParameters(queryParams));
        queryParams.put("signature", signedString);

        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
        }

        HttpDelete request = new HttpDelete(uriBuilder.build());

        signatureHandler.addHeader(request);
        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
            System.out.println("Response body: " + responseBody);
        }
        return responseBody;
    }


    @Override
    public String patch(String endpoint, String queryParamKey, String queryParamValue, Map<String, Object> bodyParams) throws URISyntaxException, IOException {
        return null;
    }

    @Override
    public String getSignedWithQueryParams(String endpoint, Map<String, Object> queryParams) throws UnsupportedEncodingException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException {
        // Prepare the HttpGet
        System.out.println("debug query params: " + queryParams);

        String signedString = signatureHandler.getSignature(UrlBuilder.joinQueryParameters(queryParams));
        queryParams.put("signature", signedString);
//        queryParams.entrySet().stream()
//                .map(p -> urlEncodeUTF8(p.getKey()) + "=" + urlEncodeUTF8(p.getValue()))
//                .reduce((p1, p2) -> p1 + "&" + p2)
//                .orElse("");
        URIBuilder builder = new URIBuilder(baseUrl + endpoint);
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            builder.addParameter(entry.getKey(), entry.getValue().toString());
        }
        HttpGet request = new HttpGet(builder.toString());
        System.out.println("Request: " + builder);
//        HttpGet request = new HttpGet(baseUrl+endpoint+"?");
        signatureHandler.addHeader(request);
        String responseBody;
        try (CloseableHttpClient client = HttpClients.createDefault();) {
            HttpResponse response = client.execute(request);
            System.out.println("Status code: " + response.getStatusLine().getStatusCode());
            responseBody = EntityUtils.toString(response.getEntity());
            System.out.println("Response body: " + responseBody);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }
}
