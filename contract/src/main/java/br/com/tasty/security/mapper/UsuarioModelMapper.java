package br.com.tasty.security.mapper;

import br.com.tasty.security.model.UsuarioModel;
import br.com.tasty.usuario.model.UsuarioImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UsuarioModelMapper {
    public static UsuarioModel mapToUsuarioModel(UsuarioImpl usuario) {
        return UsuarioModel.builder()
                .codigoUsuario(usuario.getCodigoUsuario())
                .nomeEstabelecimento(usuario.getNomeEstabelecimento())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .ativo(usuario.getAtivo())
                .habilitado(usuario.getHabilitado())
                .role(usuario.getRole())
                .contaBloqueada(usuario.getContaBloqueada())
                .contaExpirada(usuario.getContaExpirada())
                .tentativaLogin(usuario.getTentativaLogin())
                .dataCadastro(usuario.getDataCadastro())
                .dataUltimoLogin(usuario.getDataUltimoLogin())
                .dataUltimaAtualizacaoDaConta(usuario.getDataUltimaAtualizacaoDaConta())
                .build();
    }

}