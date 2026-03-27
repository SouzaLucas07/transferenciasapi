package com.souzadev.transferenciasapi.controller;

import com.souzadev.transferenciasapi.domain.exception.RequisicaoInvalidaException;
import com.souzadev.transferenciasapi.domain.exception.UsuarioNaoEncontradoException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RequisicaoInvalidaException.class)
    public ResponseEntity<String> handleRequisicaoInvalidaException(RequisicaoInvalidaException e) {
        return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException e) {
        return new ResponseEntity<>("Erro: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Captura qualquer outra exceção não tratada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        e.printStackTrace(); // Log do erro
        return new ResponseEntity<>("Erro interno do servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}