package backendhm.serviciosRest.models.azure.dtos.asistencial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class AbstractClient {
    @Value("${apis.base-url}")
    protected String baseUrl;

    protected final RestTemplate restTemplate;

    protected AbstractClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected HttpHeaders buildAuthToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }
}
