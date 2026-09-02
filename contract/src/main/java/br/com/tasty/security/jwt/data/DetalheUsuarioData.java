package br.com.tasty.security.jwt.data;

import br.com.tasty.security.model.UsuarioModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioData implements UserDetails {

    private final Optional<UsuarioModel> usuarioModel;

    public DetalheUsuarioData(Optional<UsuarioModel> usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return usuarioModel.map(UsuarioModel::getSenha).orElse(null);
    }

    @Override
    public String getUsername() {
        return usuarioModel.orElse(new UsuarioModel()).getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !(usuarioModel.orElse(new UsuarioModel()).getContaExpirada() == 1);
    }

    @Override
    public boolean isAccountNonLocked() {
        return !(usuarioModel.orElse(new UsuarioModel()).getContaBloqueada() == 1);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return usuarioModel.orElse(new UsuarioModel()).getHabilitado() == 1;
    }

    public Integer getCodigoUsuario() {
        return usuarioModel.orElse(new UsuarioModel()).getCodigoUsuario();
    }

    public String getNomeEstabelecimento() {
        return usuarioModel.orElse(new UsuarioModel()).getNomeEstabelecimento();
    }

    public String getLoginUsuario() {
        return usuarioModel.orElse(new UsuarioModel()).getEmail();
    }

    public String getRole() {
        return usuarioModel.orElse(new UsuarioModel()).getRole();
    }
}