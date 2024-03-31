package com.pyruz.dzm.swapi.service.intrface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDtos;

public interface IPeople {
    PeopleDtos getPeople(int pageNumber, String search) throws JsonProcessingException;
    void cacheEvict();
}
