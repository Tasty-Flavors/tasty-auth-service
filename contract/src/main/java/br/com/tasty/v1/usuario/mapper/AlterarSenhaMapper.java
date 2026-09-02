package br.com.tasty.v1.usuario.mapper;

import br.com.tasty.usuario.model.SenhaModelImpl;
import br.com.tasty.v1.usuario.model.SenhaRequest;

public class AlterarSenhaMapper {

    public static SenhaModelImpl mapToSenhaModel(Integer codigoUsuario, SenhaRequest request) {
        return SenhaModelImpl.builder()
                .codigoUsuario(codigoUsuario)
                .novaSenha(request.getNovaSenha())
                .build();
    }

}