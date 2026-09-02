package br.com.tasty.security.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HttpErrorResponse {

    private Integer status;
    private String error;
    private String message;

}