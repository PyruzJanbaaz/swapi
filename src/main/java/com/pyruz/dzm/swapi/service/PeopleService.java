package com.pyruz.dzm.swapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDtos;
import com.pyruz.dzm.swapi.service.intrface.IPeople;
import com.pyruz.dzm.swapi.utility.RestTemplateUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class PeopleService implements IPeople {

    @Value("${swapi.base.url}")
    private String baseURL;
    @Value("${swapi.path.people}")
    private String path;
    @Value("${swapi.param.search}")
    private String query;
    @Value("${swapi.param.page}")
    private String page;

    final RestTemplateUtility restTemplateUtility;
    final MessageSource messageSource;

    /**
     * Returns list of star war characters.
     * <p>
     * This method always returns immediately, whether the star war characters exists.
     *
     * @param pageNumber the number of page
     * @param search     the query to search on the name property
     * @return List<People> the list of star war characters
     */
    @Cacheable(value = "people", key = "#pageNumber + '_' + #search", unless = "#result == null")
    public PeopleDtos getPeople(int pageNumber, String search) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + path).queryParam(query, search).queryParam(page, pageNumber);
        String response = restTemplateUtility.sendRequestUrlencoded(builder, HttpMethod.GET).getBody();
        return new ObjectMapper().readValue(response, PeopleDtos.class);
    }

    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    @CacheEvict(value = "people", allEntries = true)
    public void cacheEvict() {
        log.info("People's cache has cleared!" + new Date());
    }
}
