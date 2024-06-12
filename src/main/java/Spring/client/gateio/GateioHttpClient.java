package Spring.client.gateio;

import Spring.client.TCHttpClient;
import Spring.service.user.KeyService;
import Spring.utils.signatureGenerator.GateioSignatureGenerator;
import io.github.cdimascio.dotenv.Dotenv;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

import static Spring.utils.signatureGenerator.GateioSignatureGenerator.*;

public class GateioHttpClient implements TCHttpClient {
    private KeyService keyService;
    private final CloseableHttpClient httpClient;
    private final String baseUrl;
    private  final GateioSignatureGenerator signatureHandler;

    @Autowired
    public GateioHttpClient(String baseUrl, KeyService keyService) {
        this.baseUrl = baseUrl;
        this.httpClient = HttpClients.createDefault();
        this.signatureHandler = new GateioSignatureGenerator(keyService);
    }

//    Map<String,String> keys = keyService.getKeys();
//    private final String gateioApiKey = keys.get("GATEIO_API_KEY");
//    private final String getGateioSecretKey = keys.get("GATEIO_API_SECRET");
//    private GateioSignatureGenerator signatureHandler = new GateioSignatureGenerator(gateioApiKey, getGateioSecretKey);

    @Override
    public String get(String endpoint) throws IOException {
        HttpGet request = new HttpGet(baseUrl + endpoint);
        request.addHeader("Accept", "application/json");

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;
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

    public String getSignedWithQueryParams(String endpoint, Map<String, Object> queryParams) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), (String) entry.getValue());
        }
        HttpGet request = new HttpGet(uriBuilder.build());
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");
        Map<String, String> headers = signatureHandler.createHeaders(request, Collections.emptyMap());
        request.addHeader(API_KEY_HEADER, headers.get(API_KEY_HEADER));
        request.addHeader(SIGNATURE_HEADER, headers.get(SIGNATURE_HEADER));
        request.addHeader(TIMESTAMP_HEADER, headers.get(TIMESTAMP_HEADER));

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;
    }

    @Override
    public String getSigned(String endpoint) throws IOException {
        HttpGet request = new HttpGet(baseUrl + endpoint);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");

        Map<String, String> headers = signatureHandler.createHeaders(request, Collections.emptyMap());
        request.addHeader(API_KEY_HEADER, headers.get(API_KEY_HEADER));
        request.addHeader(SIGNATURE_HEADER, headers.get(SIGNATURE_HEADER));
        request.addHeader(TIMESTAMP_HEADER, headers.get(TIMESTAMP_HEADER));

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;
    }

    @Override
    public String post(String endpoint, Map<String, Object> bodyParams) throws IOException {
        HttpPost request = new HttpPost(baseUrl + endpoint);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");
        Map<String, String> headers = signatureHandler.createHeaders(request, bodyParams);
        request.addHeader(API_KEY_HEADER, headers.get(API_KEY_HEADER));
        request.addHeader(SIGNATURE_HEADER, headers.get(SIGNATURE_HEADER));
        request.addHeader(TIMESTAMP_HEADER, headers.get(TIMESTAMP_HEADER));
        Gson gson = new GsonBuilder().create();
        String requestBody = gson.toJson(bodyParams);
        request.setEntity(new StringEntity(requestBody));

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;
    }

    public String postCustomBody(String endpoint, Map<String, Object> bodyParams) throws IOException {
        HttpPost request = new HttpPost(baseUrl + endpoint);
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");
        Map<String, String> headers = signatureHandler.createHeadersCustom(request, bodyParams);
        request.addHeader(API_KEY_HEADER, headers.get(API_KEY_HEADER));
        request.addHeader(SIGNATURE_HEADER, headers.get(SIGNATURE_HEADER));
        request.addHeader(TIMESTAMP_HEADER, headers.get(TIMESTAMP_HEADER));
        Gson gson = new GsonBuilder().create();
        String requestBody = gson.toJson(bodyParams);
        request.setEntity(new StringEntity(requestBody));

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;
    }

    @Override
    public String patch(String endpoint, String queryParamKey, String queryParamValue, Map<String, Object> bodyParams) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
        if (!queryParamKey.equals("")) {
            uriBuilder.addParameter(queryParamKey, queryParamValue);
        }
        HttpPatch request = new HttpPatch(uriBuilder.build());

        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");

        Map<String, String> headers = signatureHandler.createHeaders(request, bodyParams);
        request.addHeader(API_KEY_HEADER, headers.get(API_KEY_HEADER));
        request.addHeader(SIGNATURE_HEADER, headers.get(SIGNATURE_HEADER));
        request.addHeader(TIMESTAMP_HEADER, headers.get(TIMESTAMP_HEADER));


        Gson gson = new GsonBuilder().create();
        String requestBody = gson.toJson(bodyParams);
        request.setEntity(new StringEntity(requestBody));

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;
    }

    @Override
    public String put(String endpoint, Map<String, Object> bodyParams) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
        HttpPut request = new HttpPut(uriBuilder.build());

        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");

        Map<String, String> headers = signatureHandler.createHeadersCustom(request, bodyParams);
        request.addHeader(API_KEY_HEADER, headers.get(API_KEY_HEADER));
        request.addHeader(SIGNATURE_HEADER, headers.get(SIGNATURE_HEADER));
        request.addHeader(TIMESTAMP_HEADER, headers.get(TIMESTAMP_HEADER));


        Gson gson = new GsonBuilder().create();
        String requestBody = gson.toJson(bodyParams);
        request.setEntity(new StringEntity(requestBody));

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;


    }

    @Override
    public String delete(String endpoint, Map<String, Object> bodyParams) throws IOException, URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(baseUrl + endpoint);
        for (Map.Entry<String, Object> entry : bodyParams.entrySet()) {
            uriBuilder.addParameter(entry.getKey(), (String) entry.getValue());
        }
        HttpDelete request = new HttpDelete(uriBuilder.build());
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-type", "application/json");
        Map<String, String> headers = signatureHandler.createHeaders(request, Collections.emptyMap());
        request.addHeader(API_KEY_HEADER, headers.get(API_KEY_HEADER));
        request.addHeader(SIGNATURE_HEADER, headers.get(SIGNATURE_HEADER));
        request.addHeader(TIMESTAMP_HEADER, headers.get(TIMESTAMP_HEADER));

        String responseBody;
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            responseBody = EntityUtils.toString(entity);
        }
        return responseBody;
    }
}