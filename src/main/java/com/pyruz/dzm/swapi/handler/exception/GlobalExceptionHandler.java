package com.pyruz.dzm.swapi.handler.exception;

import com.pyruz.dzm.swapi.model.dto.ServiceExceptionDTO;
import com.pyruz.dzm.swapi.model.dto.base.BaseDTO;
import com.pyruz.dzm.swapi.model.dto.base.MetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    final MessageSource messageSource;

    // --> ServiceLevelValidation
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseDTO> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        BaseDTO baseDTO = new BaseDTO();
        var fields = ex.getBindingResult().getFieldErrors();
        if (!fields.isEmpty()) {
            baseDTO.setMeta(
                    MetaDTO.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .message(
                                    messageSource.getMessage(
                                            "application.message.validation.error.text",
                                            new Object[]{fields.get(0).getField()},
                                            Locale.ENGLISH))
                            .build());
        }
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public final ResponseEntity<BaseDTO> handleMissingParameterException(MissingServletRequestParameterException ex) {
        MetaDTO metaDTO = MetaDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(
                        messageSource.getMessage(
                                "application.message.missing.parameter.text",
                                new Object[]{ex.getParameterName()},
                                Locale.ENGLISH))
                .build();
        return new ResponseEntity<>(BaseDTO.builder().meta(metaDTO).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handleNoHandlerFoundException(NoHandlerFoundException ex) {
        BaseDTO baseDTO = BaseDTO.builder()
                .meta(
                        MetaDTO.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .message(ex.getMessage())
                                .build())
                .build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public Object handleGlobalException(Exception ex) {
        BaseDTO baseDTO = BaseDTO.builder()
                .meta(
                        MetaDTO.builder()
                                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .message(ex.getMessage())
                                .build())
                .build();
        return new ResponseEntity<>(baseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceExceptionDTO.class)
    public final ResponseEntity<BaseDTO> handleServiceException(ServiceExceptionDTO ex) {
        BaseDTO baseDTO = BaseDTO.builder()
                .meta(
                        MetaDTO.builder()
                                .code(ex.getCode())
                                .message(ex.getMessage())
                                .build())
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
                                .build())
                .build();
        return new ResponseEntity<>(baseDTO, ex.getStatusCode());
    }
}
