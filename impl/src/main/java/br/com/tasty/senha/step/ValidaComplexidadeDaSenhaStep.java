package br.com.tasty.senha.step;

import br.com.tasty.exception.ExceptionMaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidaComplexidadeDaSenhaStep {

    private static final String POLITICA_SENHA =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])(?!.*=).{8,}$";

    public void validar(String senha) {

        if (!senha.matches(POLITICA_SENHA)) {
            throw ExceptionMaker.buildGenericException(
                    "senha.complexidade.erro",
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}