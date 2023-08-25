package br.com.trabalho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AgendamentoNotFoundException extends RuntimeException {

    public AgendamentoNotFoundException(Long id) {
        super("Agendamento não encontrado com ID: " + id);
    }
    
    public AgendamentoNotFoundException(String message) {
        super(message);
    }
}

