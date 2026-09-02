package br.com.tasty.v1.usuario;

import br.com.tasty.usuario.UsuarioImplFacade;
import br.com.tasty.v1.usuario.model.CadastroUsuarioRequest;
import br.com.tasty.v1.usuario.model.SenhaRequest;
import br.com.tasty.v1.usuario.model.response.UsuarioResponse;
import org.springframework.stereotype.Component;

import static br.com.tasty.v1.usuario.mapper.AlterarSenhaMapper.mapToSenhaModel;
import static br.com.tasty.v1.usuario.mapper.CadastroUsuarioMapper.mapToUsuarioImpl;
import static br.com.tasty.v1.usuario.mapper.UsuarioResponseMapper.mapToResponse;

@Component
public class UsuarioFacade {

    private final UsuarioImplFacade usuarioImplFacade;

    public UsuarioFacade(UsuarioImplFacade usuarioImplFacade) {
        this.usuarioImplFacade = usuarioImplFacade;
    }

    public void cadastrar(CadastroUsuarioRequest request) {
        usuarioImplFacade.cadastrar(mapToUsuarioImpl(request));
    }

    public void alterarSenha(Integer codigoUsuario, SenhaRequest request) {
        usuarioImplFacade.alterarSenha(mapToSenhaModel(codigoUsuario, request));
    }

    public UsuarioResponse buscarUsuarioLogado(Integer codigoUsuario) {
        return mapToResponse(usuarioImplFacade.buscarPorId(codigoUsuario));
    }
}