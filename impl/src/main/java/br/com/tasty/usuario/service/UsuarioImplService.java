package br.com.tasty.usuario.service;

import br.com.tasty.usuario.model.UsuarioImpl;
import br.com.tasty.usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioImplService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioImpl buscarDadosDeLoginUsuario(String email) {
        return usuarioRepository.buscaDadosDeLoginUsuario(email);
    }

    public UsuarioImpl buscarPorId(Integer id) {
        return usuarioRepository.buscaPorId(id);
    }

    @Transactional
    public void cadastrar(UsuarioImpl usuario) {
        usuarioRepository.cadastrarUsuario(usuario);
        usuarioRepository.cadastrarRestaurante(usuario);
    }

    public void validaBloqueio(UsuarioImpl usuario) {

        var banco = buscarDadosDeLoginUsuario(usuario.getEmail());

        if (banco.getContaBloqueada() == 1) {
            throw new RuntimeException("Conta bloqueada.");
        }
    }

    public Boolean validarEmailExistente(String email) {
        return usuarioRepository.validarEmailExistente(email);
    }

    public void incrementarTentativaLogin(UsuarioImpl usuario) {
        usuarioRepository.incrementarTentativaLogin(usuario);
    }

    public void resetarTentativas(UsuarioImpl usuario) {
        usuarioRepository.resetarTentativas(usuario);
    }
}