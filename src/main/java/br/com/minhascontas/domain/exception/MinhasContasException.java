package br.com.minhascontas.domain.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * Classe padrão de exceções da aplicação.
 *
 * @author neto.diegues
 * @version 1.0.0
 * @since 01/11/19
 */

public class MinhasContasException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final HttpStatus httpStatus;

    public MinhasContasException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}