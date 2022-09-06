package com.pyruz.dzm.swapi.utility;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateUtility {

    final RestTemplate restTemplate;

    public RestTemplateUtility(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> sendRequestUrlencoded(UriComponentsBuilder builder, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(builder.toUriString(), httpMethod, entity, String.class);
    }

}
