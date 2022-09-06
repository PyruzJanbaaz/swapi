package com.pyruz.dzm.swapi.model.dto.base;

import com.pyruz.dzm.swapi.utility.ApplicationMessages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetaDTO {
    private Integer code;
    private String message;

    public static MetaDTO getInstance(ApplicationMessages applicationMessages) {
        return new MetaDTO(HttpStatus.OK.value(), applicationMessages.getProperty("application.message.success.text"));
    }
}
