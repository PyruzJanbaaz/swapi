package com.pyruz.dzm.swapi.handler.exception;

import com.pyruz.dzm.swapi.model.dto.base.ExceptionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Collections;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    final MessageSource messageSource;

    // --> ServiceLevelValidation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ExceptionDto> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        String errorMessage = "Unknown exception occurred!";
        var fields = ex.getBindingResult().getFieldErrors();
        if (!fields.isEmpty()) {
            errorMessage = messageSource.getMessage(
                    "application.message.validation.error.text",
                    new Object[]{fields.get(0).getField()},
                    Locale.ENGLISH);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), Collections.singletonList(errorMessage)));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<ExceptionDto> handleMissingParameterException(MissingServletRequestParameterException ex) {
        String errorMessage = messageSource.getMessage(
                "application.message.missing.parameter.text",
                new Object[]{ex.getParameterName()},
                Locale.ENGLISH);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(HttpStatus.BAD_REQUEST.getReasonPhrase(),
                Collections.singletonList(errorMessage)));

    }

    @ExceptionHandler({NoHandlerFoundException.class, HttpClientErrorException.class, Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                Collections.singletonList(ex.getMessage())));
    }

}
