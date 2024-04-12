package com.pyruz.dzm.swapi.controller;

import com.pyruz.dzm.swapi.model.domain.CityBean;
import com.pyruz.dzm.swapi.model.domain.CountryBean;
import com.pyruz.dzm.swapi.model.entity.Country;
import com.pyruz.dzm.swapi.service.CityService;
import com.pyruz.dzm.swapi.service.CountryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Slf4j
public class CountryController {

    final CountryService countryService;
    final CityService cityService;

    @PostMapping("/v1/country")
    public ResponseEntity<String> addCountry(@Valid @RequestBody CountryBean countryBean) {
        countryService.addCountry(countryBean);
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED!");
    }

    @PutMapping("/v1/country")
    public ResponseEntity<Country> editCountry(@RequestParam Integer id,
                                               @Valid @RequestBody CountryBean countryBean) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.editCountry(id ,countryBean));
    }

    @GetMapping("/v1/countries")
    public ResponseEntity<List<Country>> findAllCountries() {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.findAllCountries());
    }

    @GetMapping("/v1/country")
    public ResponseEntity<Country> findCountryById(@RequestParam Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(countryService.findById(id));
    }


    @PostMapping("/v1/city")
    public ResponseEntity<String> addCity(@Valid @RequestBody CityBean cityBean) {
        cityService.addCity(cityBean);
        return ResponseEntity.status(HttpStatus.CREATED).body("CREATED!");
    }

    @GetMapping(value = "v1/country/cacheEvict")
    @Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
    @CacheEvict(value = "countries", allEntries = true)
    @ResponseStatus(HttpStatus.OK)
    public void cacheEvict() {
        log.info("Country's cache has cleared!" + new Date());
    }

}
