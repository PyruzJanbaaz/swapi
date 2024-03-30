package com.pyruz.dzm.swapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pyruz.dzm.swapi.model.dto.base.BaseDTO;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDTOs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PeopleServiceTest {

    @Autowired
    private  PeopleService peopleService;

    @Test
    void getPeople() throws JsonProcessingException {
        PeopleDTOs peopleDTOs = peopleService.getPeople(1, "luke");
        assertNotNull(peopleDTOs.getResults());
        assertFalse(peopleDTOs.getResults().isEmpty());
    }
}
