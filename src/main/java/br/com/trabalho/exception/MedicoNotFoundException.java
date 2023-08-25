package br.com.trabalho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MedicoNotFoundException extends RuntimeException {

    public MedicoNotFoundException(Long id) {
        super("Médico não encontrado com ID: " + id);
    }
    
    public MedicoNotFoundException(String message) {
        super(message);
    }
}
