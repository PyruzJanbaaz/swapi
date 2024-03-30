package com.pyruz.dzm.swapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pyruz.dzm.swapi.model.dto.base.BaseDTO;
import com.pyruz.dzm.swapi.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class PeopleController {

    final PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    /**
     * Returns list of star war characters.
     * <p>
     * This method always returns immediately, whether the star war characters exists.
     *
     * @param  pageNumber  the number of page
     * @param  search the query to search on the name property
     * @return List<People> the list of star war characters
     */
    @GetMapping(value = "v1/people")
    public ResponseEntity<BaseDTO> getPeople(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                             @RequestParam(value = "search", required = false) String search) throws JsonProcessingException {
        return new ResponseEntity<>(peopleService.getPeople(pageNumber, search), HttpStatus.OK);
    }
}
