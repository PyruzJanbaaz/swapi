package com.pyruz.dzm.swapi.utility;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class RestTemplateUtility {

    final RestTemplate restTemplate;

    public ResponseEntity<String> sendRequestUrlencoded(UriComponentsBuilder builder, HttpMethod httpMethod) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(builder.toUriString(), httpMethod, entity, String.class);
    }

}
