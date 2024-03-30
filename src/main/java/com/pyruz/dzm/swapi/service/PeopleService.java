package com.pyruz.dzm.swapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyruz.dzm.swapi.model.dto.base.BaseDTO;
import com.pyruz.dzm.swapi.model.dto.base.MetaDTO;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDTOs;
import com.pyruz.dzm.swapi.service.intrface.IPeople;
import com.pyruz.dzm.swapi.utility.RestTemplateUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;

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
    public BaseDTO getPeople(int pageNumber, String search) throws JsonProcessingException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseURL + path).queryParam(query, search).queryParam(page, pageNumber);
        String result = restTemplateUtility.sendRequestUrlencoded(builder, HttpMethod.GET).getBody();
        PeopleDTOs peopleDTOs = new ObjectMapper().readValue(result, PeopleDTOs.class);
        return response(peopleDTOs);
    }

    private BaseDTO response(Object data) {
        return BaseDTO.builder()
                .meta(MetaDTO.builder()
                        .code(HttpStatus.OK.value())
                        .message(messageSource.getMessage(
                                "application.message.success.text",
                                null,
                                Locale.ENGLISH))
                        .build())
                .data(data)
                .build();
    }

}
