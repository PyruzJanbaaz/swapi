package com.pyruz.dzm.swapi.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ServiceExceptionDTO extends  RuntimeException{
    private Integer code;
    private String message;
}
