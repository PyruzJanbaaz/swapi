package com.pyruz.dzm.swapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyruz.dzm.swapi.handler.configuration.ApplicationContextHolder;
import com.pyruz.dzm.swapi.model.dto.base.BaseDTO;
import com.pyruz.dzm.swapi.model.dto.base.MetaDTO;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDTOs;
import com.pyruz.dzm.swapi.service.intrface.IPeople;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PeopleService extends ApplicationContextHolder implements IPeople {

    /**
     * Returns list of star war characters.
     * <p>
     * This method always returns immediately, whether or not the star war characters exists.
     *
     * @param  pageNumber  the number of page
     * @param  search the query to search on the name property
     * @return List<People> the list of star war characters
     */
    public BaseDTO getPeople(int pageNumber, String search) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                applicationProperties.getProperty("swapi.base.url") + applicationProperties.getProperty("swapi.path.people"))
                .queryParam(applicationProperties.getProperty("swapi.param.search"), search)
                .queryParam(applicationProperties.getProperty("swapi.param.page"), pageNumber);
        String result = restTemplateUtility.sendRequestUrlencoded(builder, HttpMethod.GET).getBody();
        PeopleDTOs peopleDTOs = new ObjectMapper().readValue(result, PeopleDTOs.class);
        return BaseDTO.builder()
                .meta(MetaDTO.getInstance(applicationMessages))
                .data(peopleDTOs)
                .build();
    }

}
