package com.pyruz.dzm.swapi.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO implements Serializable {
    private MetaDTO meta;
    private Object data;
}
