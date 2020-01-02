package br.com.minhascontas.domain.exception.detais;

import java.time.ZonedDateTime;

/**
 *
 * Classe contendo específica para retorno de erros da aplicação, considerando bean validations.
 *
 * @author raphael.moreira
 */
public class ValidationErrorDetails extends ErrorDetails {
    private String field;
    private String fieldMessage;

    public static final class Builder {
        private String title;
        private String detail;
        private ZonedDateTime timestamp;
        private String developerMessage;
        private String field;
        private String fieldMessage;

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder detail(String detail) {
            this.detail = detail;
            return this;
        }

        public Builder timestamp(ZonedDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder developerMessage(String developerMessage) {
            this.developerMessage = developerMessage;
            return this;
        }

        public Builder field(String field) {
            this.field = field;
            return this;
        }

        public Builder fieldMessage(String fieldMessage) {
            this.fieldMessage = fieldMessage;
            return this;
        }

        public ValidationErrorDetails build() {
            ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
            validationErrorDetails.setDeveloperMessage(developerMessage);
            validationErrorDetails.setTitle(title);
            validationErrorDetails.setDetail(detail);
            validationErrorDetails.setTimestamp(timestamp);
            validationErrorDetails.fieldMessage = fieldMessage;
            validationErrorDetails.field = field;
            return validationErrorDetails;
        }
    }

    public String getField() {
        return field;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

}

