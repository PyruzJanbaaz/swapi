package com.pyruz.dzm.swapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pyruz.dzm.swapi.model.dto.base.BaseDTO;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDTOs;
import com.pyruz.dzm.swapi.service.PeopleService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class PeopleController {

    final PeopleService peopleService;

    /**
     * Returns list of star war characters.
     * <p>
     * This method always returns immediately, whether the star war characters exists.
     *
     * @param pageNumber the number of page
     * @param search     the query to search on the name property
     * @return List<People> the list of star war characters
     */
    @GetMapping(value = "v1/people")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PeopleDTOs> getPeople(@RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
                                                @RequestParam(value = "search", required = false) String search) throws JsonProcessingException {
        return ResponseEntity.ok(peopleService.getPeople(pageNumber, search));
    }

    @DeleteMapping(value = "v1/people/cache")
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value = "people", allEntries = true)
    public String clearCache() {
        return "'People' cache has cleared!";
    }
}
