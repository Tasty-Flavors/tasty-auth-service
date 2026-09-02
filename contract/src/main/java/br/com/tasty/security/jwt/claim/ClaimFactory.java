package br.com.tasty.security.jwt.claim;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

@Component
public class ClaimFactory {

    public DecodedJWT buildClaim(String token, String passwordToken) {
        return JWT.require(Algorithm.HMAC512(passwordToken))
                .build()
                .verify(token);
    }

}