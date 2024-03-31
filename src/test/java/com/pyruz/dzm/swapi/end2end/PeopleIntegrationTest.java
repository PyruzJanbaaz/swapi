package com.pyruz.dzm.swapi.end2end;

import com.pyruz.dzm.swapi.SpringBootSWAPIApplication;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDtos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootSWAPIApplication.class)
class PeopleIntegrationTest {

    private final String API_URI = "/api/v1/people?pageNumber=%s&search=%s";
    private final String QUERY = "Owen";
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void getPeople_valid_success() {
        // given
        String uri = String.format(API_URI, 1, QUERY);
        // when
        var actual = testRestTemplate.getForEntity(uri, PeopleDtos.class);
        // then
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        assertFalse(Objects.requireNonNull(actual.getBody()).getResults().isEmpty());
    }

    @Test
    void getPeople_invalidParams_success() {
        // given
        String uri = String.format(API_URI, "page", QUERY);
        // when
        var actual = testRestTemplate.getForEntity(uri, PeopleDtos.class);
        // then
        assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }
}
