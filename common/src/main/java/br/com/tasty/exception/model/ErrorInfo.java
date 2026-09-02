package br.com.tasty.exception.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorInfo {

    private List<ErrorSpec> errors;

}
