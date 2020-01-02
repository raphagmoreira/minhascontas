package br.com.minhascontas.domain.dto;

import br.com.minhascontas.domain.exception.detais.ValidationErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

/**
 * Classe responsável pela padronização no tipo de retorno da API
 *
 * @param <T>
 * @author raphael.moreira
 */
public class RestResponseDTO<T> {

    private HttpStatus status;

    private String message;

    private ValidationErrorDetails validationErrorDetails;

    private T body;

    public RestResponseDTO() {
    }

    public RestResponseDTO(T body, String message, HttpStatus status) {
        this.body = body;
        this.message = message;
        this.status = status;
    }

    public RestResponseDTO(T body) {
        this.body = body;
    }

    public RestResponseDTO(HttpStatus status, String message, ValidationErrorDetails validationErrorDetails, T body) {
        this.status = status;
        this.message = message;
        this.validationErrorDetails = validationErrorDetails;
        this.body = body;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValidationErrorDetails getValidationErrorDetails() {
        return validationErrorDetails;
    }

    public void setValidationErrorDetails(ValidationErrorDetails validationErrorDetails) {
        this.validationErrorDetails = validationErrorDetails;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public ResponseEntity<RestResponseDTO<T>> returnEntity() {
        setMessage(this.getMessage());
        setStatus(this.getStatus());

        return new ResponseEntity<>(
                this,
                this.getStatus()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestResponseDTO<?> that = (RestResponseDTO<?>) o;
        return status == that.status &&
                Objects.equals(message, that.message) &&
                Objects.equals(validationErrorDetails, that.validationErrorDetails) &&
                Objects.equals(body, that.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message, validationErrorDetails, body);
    }

    @Override
    public String toString() {
        return "RestResponseDTO{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", errorDetails=" + validationErrorDetails +
                ", body=" + body +
                '}';
    }
}