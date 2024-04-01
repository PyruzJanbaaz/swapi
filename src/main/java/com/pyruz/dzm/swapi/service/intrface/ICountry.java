package com.pyruz.dzm.swapi.service.intrface;


import com.pyruz.dzm.swapi.model.domain.CountryBean;
import com.pyruz.dzm.swapi.model.entity.Country;

import java.util.List;

public interface ICountry {
    void addCountry(CountryBean countryBean);
    void editCountry(Integer id, CountryBean countryBean);
    Country findById(Integer id);
    List<Country> findAllCountries();
}
