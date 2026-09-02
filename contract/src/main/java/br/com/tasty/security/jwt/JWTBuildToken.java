package br.com.tasty.security.jwt;

import br.com.tasty.security.jwt.data.DetalheUsuarioData;
import br.com.tasty.security.model.LoginResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class JWTBuildToken {

    private final String passwordToken;
    private final Long expirationToken;

    public JWTBuildToken(Environment environment) {
        this.passwordToken = Objects.requireNonNull(environment.getProperty("token.password"));
        this.expirationToken = Long.parseLong(
                Objects.requireNonNull(environment.getProperty("token.expiration"))
        );
    }

    public String buildTokenResponseAsString(DetalheUsuarioData usuarioData, String ip) {
        return new Gson().toJson(
                buildToken(
                        usuarioData,
                        new Date(System.currentTimeMillis() + expirationToken),
                        ip
                )
        );
    }

    private LoginResponse buildToken(
            DetalheUsuarioData usuarioData,
            Date expiration,
            String ip
    ) {
        return LoginResponse.builder()
                .accessToken(
                        JWT.create()
                                .withSubject(usuarioData.getUsername())
                                .withExpiresAt(expiration)
                                .withClaim("id", usuarioData.getCodigoUsuario())
                                .withClaim("nomeEstabelecimento", usuarioData.getNomeEstabelecimento())
                                .withClaim("email", usuarioData.getLoginUsuario())
                                .withClaim("role", usuarioData.getRole())
                                .withClaim("ip", ip)
                                .sign(Algorithm.HMAC512(passwordToken))
                )
                .build();
    }
}