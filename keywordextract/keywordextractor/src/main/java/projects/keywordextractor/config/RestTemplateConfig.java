package projects.keywordextractor.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Collections;

@Configuration
public class RestTemplateConfig {
    //@Value("${keywordextractor.url}")
    private String baseUrl;

    @Bean
    public RestTemplate restTemplate() {
        return createRestTemplate("http://localhost:8071/extract/");
    }

    public RestTemplate createRestTemplate(String baseUrl){

        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());

        restTemplate.setRequestFactory(clientHttpRequestFactory());

        restTemplate.setInterceptors(Collections.singletonList((request, body, execution) -> {

            request.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            request.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

            return execution.execute(request, body);

        }));

        restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));

        return restTemplate;

    }


    private ClientHttpRequestFactory clientHttpRequestFactory() {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(15000);

        factory.setReadTimeout(15000);

        return factory;

    }
}
