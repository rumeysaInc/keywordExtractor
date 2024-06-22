package projects.keywordextractor.RestClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import projects.keywordextractor.request.concretes.KeywordRequest;

import java.util.List;

@Component
public class RestRequest {
    @Qualifier("restTemplate")
    private final RestTemplate restTemplate;

    public RestRequest(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<String> scholarKeyword(KeywordRequest request) {
        String url = "http://localhost:8071/extract/scholar";
        ResponseEntity<List<String>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<List<String>>() {
                });
        return response.getBody();
    }

    public List<String> newsKeyword(KeywordRequest request) {
        String url = "http://localhost:8071/extract/news";
        ResponseEntity<List<String>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(request),
                new ParameterizedTypeReference<List<String>>() {
                });
        return response.getBody();
    }

}
