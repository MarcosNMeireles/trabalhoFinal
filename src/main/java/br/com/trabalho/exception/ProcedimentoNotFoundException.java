package br.com.trabalho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProcedimentoNotFoundException extends RuntimeException {

    public ProcedimentoNotFoundException(Long id) {
        super("Procedimento não encontrado com ID: " + id);
    }
    
    public ProcedimentoNotFoundException(String message) {
        super(message);
    }
}
