package com.pyruz.dzm.swapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDtos;
import com.pyruz.dzm.swapi.utility.RestTemplateUtility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {

    private final String QUERY = "searchQuery";

    @Autowired
    private PeopleService peopleService;

    @MockBean
    private RestTemplateUtility restTemplateUtility;

    @AfterEach
    void cacheEvict(){
        peopleService.cacheEvict();
    }

    @Test
    void getPeople_valid_success() throws JsonProcessingException {
        // given
        String validResponse = getValidResponse();
        // when
        when(restTemplateUtility.sendRequestUrlencoded(any(UriComponentsBuilder.class), eq(HttpMethod.GET)))
                .thenReturn(ResponseEntity.ok(validResponse));
        PeopleDtos result = peopleService.getPeople(1, QUERY);
        // then
        assertNotNull(result);
    }

    @Test
    void getPeople_withCache_success() throws JsonProcessingException {
        // given
        String validResponse = getValidResponse();
        // when
        when(restTemplateUtility.sendRequestUrlencoded(any(UriComponentsBuilder.class), eq(HttpMethod.GET)))
                .thenReturn(ResponseEntity.ok(validResponse));
        PeopleDtos remoteCalling = peopleService.getPeople(1, QUERY);
        PeopleDtos cacheCalling = peopleService.getPeople(1, QUERY);
        // then
        assertNotNull(remoteCalling);
        assertNotNull(cacheCalling);
        assertEquals(remoteCalling, cacheCalling);
        // verify
        verify(restTemplateUtility, times(1)).sendRequestUrlencoded(any(UriComponentsBuilder.class), eq(HttpMethod.GET));
    }

    @Test
    void getPeople_evictedCache_success() throws JsonProcessingException {
        // given
        String validResponse = getValidResponse();
        // when
        when(restTemplateUtility.sendRequestUrlencoded(any(UriComponentsBuilder.class), eq(HttpMethod.GET)))
                .thenReturn(ResponseEntity.ok(validResponse));
        PeopleDtos firstRemoteCalling = peopleService.getPeople(1, QUERY);
        peopleService.cacheEvict();
        PeopleDtos secondRemoteCalling = peopleService.getPeople(1, QUERY);
        // then
        assertNotNull(firstRemoteCalling);
        assertNotNull(secondRemoteCalling);
        assertEquals(firstRemoteCalling, secondRemoteCalling);
        // verify
        verify(restTemplateUtility, times(2)).sendRequestUrlencoded(any(UriComponentsBuilder.class), eq(HttpMethod.GET));
    }

    private String getValidResponse() {
        return """
                {
                  "count": 82,
                  "next": "https://swapi.dev/api/people/?search=&page=2",
                  "previous": null,
                  "results": []
                 }
                 """;
    }
}
