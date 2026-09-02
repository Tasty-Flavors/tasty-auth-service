package br.com.tasty.exception.model;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class GenericException extends RuntimeException {

    private final ErrorInfo errorInfo;
    private final HttpStatus status;

    public GenericException(ErrorInfo errorInfo, HttpStatus status) {
        super(errorInfo.getErrors().getFirst().getMessage());
        this.errorInfo = errorInfo;
        this.status = status;
    }

}
