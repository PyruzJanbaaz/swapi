package com.pyruz.dzm.swapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pyruz.dzm.swapi.model.domain.CountryBean;
import com.pyruz.dzm.swapi.model.dto.people.PeopleDtos;
import com.pyruz.dzm.swapi.model.entity.Country;
import com.pyruz.dzm.swapi.repository.CountryRepository;
import com.pyruz.dzm.swapi.service.intrface.ICountry;
import com.pyruz.dzm.swapi.service.intrface.IPeople;
import com.pyruz.dzm.swapi.utility.RestTemplateUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryService implements ICountry {

    final CountryRepository countryRepository;

    @Override
    @CacheEvict(cacheNames = "countries", beforeInvocation = true, allEntries = true)
    public void addCountry(CountryBean countryBean) {
        countryRepository.save(Country.builder()
                .code(countryBean.getCode())
                .name(countryBean.getName())
                .build());
    }

    @Override
    @CachePut(cacheNames = "countries", key = "#id")
    public void editCountry(Integer id, CountryBean countryBean) {
        Country country = this.findById(id);
        country.setCode(countryBean.getCode());
        country.setName(countryBean.getName());
        countryRepository.save(country);
    }

    @Override
    public Country findById(Integer id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found!")
        );
    }

    @Override
    @Cacheable("countries")
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }
}
