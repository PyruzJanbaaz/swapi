package com.pyruz.dzm.swapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDTOs;
import com.pyruz.dzm.swapi.service.intrface.IPeople;
import com.pyruz.dzm.swapi.utility.RestTemplateUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;


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
    @Cacheable(value = "people", key = "#pageNumber + '_' + #search")
    public PeopleDTOs getPeople(int pageNumber, String search) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + path).queryParam(query, search).queryParam(page, pageNumber);
        String response = restTemplateUtility.sendRequestUrlencoded(builder, HttpMethod.GET).getBody();
        return new ObjectMapper().readValue(response, PeopleDTOs.class);
    }
}
