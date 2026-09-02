package br.com.tasty.security.jwt.claim;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JwtClaim {

    private final String passwordToken;
    private final ClaimFactory claimFactory;

    public JwtClaim(Environment environment,
                    ClaimFactory claimFactory) {

        this.passwordToken = Objects.requireNonNull(
                environment.getProperty("token.password")
        );

        this.claimFactory = claimFactory;
        System.out.println("VALIDANDO: " + passwordToken);
    }

    public DecodedJWT buscaClaim(String token) {
        return claimFactory.buildClaim(token, passwordToken);
    }

}