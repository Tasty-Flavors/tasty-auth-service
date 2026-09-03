package br.com.tasty.v1.token;

import br.com.tasty.security.jwt.JWTBuildToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AutenticacaoService {

    private final JWTBuildToken jwtBuildToken;

    public String validarToken(String token) {
        return jwtBuildToken.validarToken(token);
    }
}