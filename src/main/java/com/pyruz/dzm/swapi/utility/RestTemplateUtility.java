package com.pyruz.dzm.swapi.utility;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateUtility {

    final RestTemplate restTemplate;

    public RestTemplateUtility(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> sendRequest(String url, HttpMethod httpMethod, Object body) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);
        return restTemplate.exchange(url, httpMethod, httpEntity, String.class);
    }

    public ResponseEntity<String> sendRequestUrlencoded(MultiValueMap<String, Object> multiValueMap, String url, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(multiValueMap, headers);
        return restTemplate.exchange(url, httpMethod, httpEntity, String.class);
    }

    public ResponseEntity<String> sendRequestUrlencoded(UriComponentsBuilder builder, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(builder.toUriString(), httpMethod, entity, String.class);
    }


}
