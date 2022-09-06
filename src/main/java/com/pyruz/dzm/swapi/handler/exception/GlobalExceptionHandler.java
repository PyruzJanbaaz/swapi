package com.pyruz.dzm.swapi.handler.exception;

import com.pyruz.dzm.swapi.model.dto.ServiceExceptionDTO;
import com.pyruz.dzm.swapi.model.dto.base.BaseDTO;
import com.pyruz.dzm.swapi.model.dto.base.MetaDTO;
import com.pyruz.dzm.swapi.utility.ApplicationMessages;
import com.pyruz.dzm.swapi.utility.ApplicationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    final ApplicationProperties applicationProperties;
    final ApplicationMessages applicationMessages;

    public GlobalExceptionHandler(ApplicationProperties applicationProperties, ApplicationMessages applicationMessages) {
        this.applicationProperties = applicationProperties;
        this.applicationMessages = applicationMessages;
    }

    // --> ServiceLevelValidation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        BaseDTO baseDTO = new BaseDTO();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            baseDTO.setMeta(
                    MetaDTO.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .message(
                                    String.format(
                                            applicationMessages.getProperty("application.message.validation.error.text"),
                                            error.getField()
                                    )
                            )
                            .build()
            );
            break;
        }
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ResponseEntity<BaseDTO> handleMissingParameterException(MissingServletRequestParameterException ex, WebRequest request) {
        Object convertedFieldName = applicationMessages.getProperty(ex.getParameterName());
        MetaDTO metaDTO = MetaDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(
                        String.format(
                                applicationMessages.getProperty("application.message.missing.parameter.text"),
                                convertedFieldName == null ? ex.getParameterName() : convertedFieldName.toString()
                        )
                ).build();
        return new ResponseEntity<>(BaseDTO.builder().meta(metaDTO).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        BaseDTO baseDTO = BaseDTO.builder()
                .meta(
                        MetaDTO.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .message(ex.getMessage())
                                .build()
                )
                .build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public Object handleGlobalException(Exception ex, HttpServletRequest request) {
        BaseDTO baseDTO = BaseDTO.builder()
                .meta(
                        MetaDTO.builder()
                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(ex.getMessage())
                                .build()
                )
                .build();
        return new ResponseEntity<>(baseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceExceptionDTO.class)
    public final ResponseEntity<BaseDTO> handleServiceException(ServiceExceptionDTO ex, WebRequest request) {
        BaseDTO baseDTO = BaseDTO.builder()
                .meta(
                        MetaDTO.builder()
                                .code(ex.getCode())
                                .message(ex.getMessage())
                                .build()
                )
                .build();
        HttpStatus httpStatus = ex.getCode() == null ? HttpStatus.INTERNAL_SERVER_ERROR : HttpStatus.resolve(ex.getCode());
        assert httpStatus != null;
        return new ResponseEntity<>(baseDTO, httpStatus);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public Object handleHttpClientErrorException(HttpClientErrorException ex) {
        BaseDTO baseDTO = BaseDTO.builder()
                .meta(
                        MetaDTO.builder()
                                .code(ex.getStatusCode().value())
                                .message(ex.getStatusText())
                                .build()
                )
                .build();
        return new ResponseEntity<>(baseDTO, ex.getStatusCode());
    }
}
