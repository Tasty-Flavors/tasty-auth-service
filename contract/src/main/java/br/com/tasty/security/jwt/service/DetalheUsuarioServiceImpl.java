package br.com.tasty.security.jwt.service;

import br.com.tasty.security.jwt.data.DetalheUsuarioData;
import br.com.tasty.security.mapper.UsuarioModelMapper;
import br.com.tasty.usuario.UsuarioImplFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioImplFacade usuarioImplFacade;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        var usuario = usuarioImplFacade.buscarDadosDeLoginUsuario(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return new DetalheUsuarioData(
                Optional.of(UsuarioModelMapper.mapToUsuarioModel(usuario))
        );
    }

    public boolean contaBloqueada(String email) {
        return usuarioImplFacade.buscarDadosDeLoginUsuario(email)
                .getContaBloqueada() == 1;
    }

    public boolean contaExpirada(String email) {
        return usuarioImplFacade.buscarDadosDeLoginUsuario(email)
                .getContaExpirada() == 1;
    }

    public boolean usuarioHabilitado(String email) {
        return usuarioImplFacade.buscarDadosDeLoginUsuario(email)
                        .getHabilitado() == 1;
    }
}