package com.pyruz.dzm.swapi.service;

import com.pyruz.dzm.swapi.model.domain.CountryBean;
import com.pyruz.dzm.swapi.model.entity.Country;
import com.pyruz.dzm.swapi.repository.CountryRepository;
import com.pyruz.dzm.swapi.service.intrface.ICountry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import java.util.List;

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
    @Caching(
            evict = { @CacheEvict(cacheNames = "countries", beforeInvocation = true, allEntries = true)},
            put = { @CachePut(cacheNames = "countries", key = "#id")}
    )
    public Country editCountry(Integer id, CountryBean countryBean) {
        var country = findById(id);
        country.setCode(countryBean.getCode());
        country.setName(countryBean.getName());
        return countryRepository.save(country);
    }

    @Override
    @Cacheable(value = "countries", key = "#id")
    public Country findById(Integer id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not found!")
        );
    }

    @Override
    @Cacheable(value = "countries")
    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }
}
