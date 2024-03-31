package com.pyruz.dzm.swapi.model.dto.base;

import java.time.LocalDateTime;
import java.util.List;

public record ExceptionDto(String error, List<String> messages, LocalDateTime dateTime) {
    public ExceptionDto(String error, List<String> messages) {
        this(error, messages, LocalDateTime.now());
    }
}