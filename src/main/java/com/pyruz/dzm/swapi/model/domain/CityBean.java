package com.pyruz.dzm.swapi.model.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CityBean {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Integer population;
    @NotNull
    @NotBlank
    private String postalCode;
    @NotNull
    private Integer countryId;
}
