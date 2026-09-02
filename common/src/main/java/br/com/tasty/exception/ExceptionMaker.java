package br.com.tasty.exception;

import br.com.tasty.ProvedorDeMensagens;
import br.com.tasty.exception.model.ErrorInfo;
import br.com.tasty.exception.model.ErrorSpec;
import br.com.tasty.exception.model.GenericException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionMaker {

    private static final String DEFAULT_USER_MSG =
            ProvedorDeMensagens.getInstance().getMensagem("erro.default");

    public static GenericException buildGenericException(String message, String valor, Integer linha, String coluna,
                                                         HttpStatus status) {
        final var msg = ProvedorDeMensagens.getInstance().getMensagem(message);
        return buildException(String.format(msg, valor, linha, coluna), status);
    }

    public static GenericException buildGenericException(String message, Integer linha, String coluna,
                                                         HttpStatus status) {
        final var msg = ProvedorDeMensagens.getInstance().getMensagem(message);
        return buildException(String.format(msg, linha, coluna), status);
    }

    public static GenericException buildGenericException(String message, Integer parametro1, Integer parametro2,
                                                         HttpStatus status) {
        final var msg = ProvedorDeMensagens.getInstance().getMensagem(message);
        return buildException(String.format(msg, parametro1, parametro2), status);
    }

    public static GenericException buildGenericException(String message, String parametro1, String parametro2,
                                                         HttpStatus status) {
        final var msg = ProvedorDeMensagens.getInstance().getMensagem(message);
        return buildException(String.format(msg, parametro1, parametro2), status);
    }

    public static GenericException buildGenericException(String message, String complemento, HttpStatus status) {
        final var msg = ProvedorDeMensagens.getInstance().getMensagem(message);
        return buildException(String.format(msg, complemento), status);
    }

    public static GenericException buildGenericException(String message, HttpStatus status) {
        final var msg = ProvedorDeMensagens.getInstance().getMensagem(message);
        return buildException(msg, status);
    }

    private static GenericException buildException(String msg, HttpStatus status) {
        return new GenericException(
                ErrorInfo.builder()
                        .errors(List.of(ErrorSpec.builder()
                                .message(Optional.ofNullable(msg).orElse(DEFAULT_USER_MSG))
                                .suggestedUserActions(List.of(DEFAULT_USER_MSG))
                                .suggestedApplicationActions(List.of(Optional.ofNullable(msg)
                                        .orElse(DEFAULT_USER_MSG)))
                                .httpStatusCodes(List.of(status))
                                .build()))
                        .build(),
                status);
    }

}