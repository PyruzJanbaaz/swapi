package com.pyruz.dzm.swapi.service.intrface;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDTOs;

public interface IPeople {
    PeopleDTOs getPeople(int pageNumber, String search) throws JsonProcessingException;
}
