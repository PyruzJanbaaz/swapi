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
public class CountryBean {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String code;
}
