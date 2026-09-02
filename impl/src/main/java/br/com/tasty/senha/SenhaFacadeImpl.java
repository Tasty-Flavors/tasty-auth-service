package br.com.tasty.senha;

import br.com.tasty.senha.service.SenhaServiceImpl;
import br.com.tasty.senha.step.ValidaComplexidadeDaSenhaStep;
import br.com.tasty.usuario.model.SenhaModelImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SenhaFacadeImpl {

    private final SenhaServiceImpl senhaServiceImpl;
    private final ValidaComplexidadeDaSenhaStep validaComplexidadeDaSenhaStep;

    public String criptografarSenha(String senha) {
        return senhaServiceImpl.criptografarSenha(senha);
    }

    public boolean validarSenha(String senhaDigitada, String senhaBanco) {
        return senhaServiceImpl.validarSenha(senhaDigitada, senhaBanco);
    }

    public void validarPolitica(String senha) {
        validaComplexidadeDaSenhaStep.validar(senha);
    }

    public void alterarSenha(SenhaModelImpl senhaModel) {
        validarPolitica(senhaModel.getNovaSenha());
        senhaServiceImpl.alterarSenha(senhaModel);
    }
}