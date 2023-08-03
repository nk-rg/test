package com.dico.test.util;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class UtilExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> exceptionalErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        ApiErrorResponse errorResponse = ApiErrorResponse.getInstance(status.value(), (Serializable) exceptionalErrors);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler({EntityNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiErrorResponse> entityNotFoundExceptionHandler(Exception ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.getInstance(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getMessage());
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> exceptionHandler(Exception ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.getInstance(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
