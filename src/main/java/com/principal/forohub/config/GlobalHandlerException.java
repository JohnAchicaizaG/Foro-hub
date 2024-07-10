package com.principal.forohub.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handlerHttpRequestMethodNotSupportedException(Exception ex){
        return ResponseEntity.badRequest().body("Metodo incorrecto");
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handlerEntityNotFoundException(Exception ex){
        return ResponseEntity.badRequest().body("No se encuentra el registro en la bd");
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handlerDataIntegrityViolationException(Exception ex){
        return ResponseEntity.badRequest().body("Error en la data enviada");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> handlerNoResourceFoundException(Exception ex){
        return ResponseEntity.badRequest().body("Hay un error en la url brindada");
    }
}
