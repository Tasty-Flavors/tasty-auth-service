package br.com.tasty.v1.usuario.mapper;

import br.com.tasty.usuario.model.UsuarioImpl;
import br.com.tasty.v1.usuario.model.CadastroUsuarioRequest;

public class CadastroUsuarioMapper {

    public static UsuarioImpl mapToUsuarioImpl(CadastroUsuarioRequest request) {

        return UsuarioImpl.builder()
                .nomeEstabelecimento(request.getNomeEstabelecimento())
                .email(request.getEmail())
                .senha(request.getSenha())
                .ativo(1)
                .habilitado(1)
                .contaBloqueada(0)
                .contaExpirada(0)
                .tentativaLogin(0)
                .role("RESTAURANTE")
                .build();
    }

}