package br.com.tasty.usuario;

import br.com.tasty.senha.SenhaFacadeImpl;
import br.com.tasty.usuario.model.SenhaModelImpl;
import br.com.tasty.usuario.model.UsuarioImpl;
import br.com.tasty.usuario.service.UsuarioImplService;
import org.springframework.stereotype.Component;

@Component
public class UsuarioImplFacade {

    private final UsuarioImplService usuarioImplService;
    private final SenhaFacadeImpl senhaFacadeImpl;

    public UsuarioImplFacade(
            UsuarioImplService usuarioImplService,
            SenhaFacadeImpl senhaFacadeImpl) {
        this.usuarioImplService = usuarioImplService;
        this.senhaFacadeImpl = senhaFacadeImpl;
    }

    public UsuarioImpl buscarDadosDeLoginUsuario(String email) {
        return usuarioImplService.buscarDadosDeLoginUsuario(email);
    }

    public UsuarioImpl buscarPorId(Integer id) {
        return usuarioImplService.buscarPorId(id);
    }

    public void cadastrar(UsuarioImpl usuario) {

        usuarioImplService.validarEmailExistente(usuario.getEmail());

        usuario.setSenha(
                senhaFacadeImpl.criptografarSenha(usuario.getSenha())
        );

        usuarioImplService.cadastrar(usuario);
    }

    public void alterarSenha(SenhaModelImpl senha) {

        var usuario = usuarioImplService.buscarPorId(senha.getCodigoUsuario());

        if (!senhaFacadeImpl.validarSenha(
                senha.getSenhaAnterior(),
                usuario.getSenha())) {
            throw new RuntimeException("Senha atual inválida.");
        }

        senhaFacadeImpl.alterarSenha(senha);
    }

    public void validaBloqueio(UsuarioImpl usuario) {
        usuarioImplService.validaBloqueio(usuario);
    }
}