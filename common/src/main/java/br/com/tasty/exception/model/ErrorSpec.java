package br.com.tasty.exception.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ErrorSpec {
    private String namespace;
    private String message;
    private List<String> suggestedUserActions;
    private List<String> suggestedApplicationActions;
    private List<HttpStatus> httpStatusCodes;

}