package com.pyruz.dzm.swapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pyruz.dzm.swapi.service.impl.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

    final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping(value = "/people")
    public ResponseEntity<?> getPeople(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                       @RequestParam(value = "search", required = false) String search) throws JsonProcessingException {
        return new ResponseEntity<>(peopleService.getPeople(pageNumber, search), HttpStatus.OK);
    }
}
