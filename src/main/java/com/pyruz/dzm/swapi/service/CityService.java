package com.pyruz.dzm.swapi.service;

import com.pyruz.dzm.swapi.model.domain.CityBean;
import com.pyruz.dzm.swapi.model.entity.City;
import com.pyruz.dzm.swapi.repository.CityRepository;
import com.pyruz.dzm.swapi.service.intrface.ICity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CityService implements ICity {

    final CityRepository cityRepository;
    final CountryService countryService;

    @Override
    public void addCity(CityBean cityBean) {
        cityRepository.save(City.builder()
                .name(cityBean.getName())
                .population(cityBean.getPopulation())
                .postalCode(cityBean.getPostalCode())
                .country(countryService.findById(cityBean.getCountryId()))
                .build());
    }
}
