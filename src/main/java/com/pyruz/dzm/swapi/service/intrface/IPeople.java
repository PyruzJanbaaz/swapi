package com.pyruz.dzm.swapi.service.intrface;

import com.pyruz.dzm.swapi.model.dto.base.BaseDTO;

public interface IPeople {
    BaseDTO getPeople(int pageNumber, String search) throws Exception;
}
