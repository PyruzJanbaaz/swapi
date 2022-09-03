package com.pyruz.dzm.swapi.model.dto.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO {
    private MetaDTO meta;
    private Object data;
}
