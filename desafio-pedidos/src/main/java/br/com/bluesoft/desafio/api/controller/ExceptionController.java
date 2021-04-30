package br.com.bluesoft.desafio.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.bluesoft.desafio.utils.BusinessException;

@RestControllerAdvice
public class ExceptionController {
    
    @ExceptionHandler
    @ResponseBody
    ResponseEntity<?> handleException(Exception ex) {

        if(ex instanceof BusinessException)
            return ResponseEntity.badRequest().body(ex.getMessage());

        return ResponseEntity.status(500).body("Erro no sistema!");
    }
}
