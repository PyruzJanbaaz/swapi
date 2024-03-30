package com.pyruz.dzm.swapi.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ServiceExceptionDTO extends RuntimeException {
    private final Integer code;
    private final String message;
}
