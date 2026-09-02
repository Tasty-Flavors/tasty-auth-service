package br.com.tasty.v1.usuario.mapper;

import br.com.tasty.usuario.model.UsuarioImpl;
import br.com.tasty.v1.usuario.model.response.UsuarioResponse;

public class UsuarioResponseMapper {
    public static UsuarioResponse mapToResponse(UsuarioImpl usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getCodigoUsuario())
                .nomeEstabelecimento(usuario.getNomeEstabelecimento())
                .email(usuario.getEmail())
                .ativo(usuario.getAtivo())
                .dataCadastro(usuario.getDataCadastro())
                .build();
    }

}
