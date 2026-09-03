package br.com.tasty.v1.token;

import br.com.tasty.security.jwt.JWTBuildToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AutenticacaoService {

    private final JWTBuildToken jwtBuildToken;

    public String validarToken(String token) {
        return jwtBuildToken.validarToken(token);
    }
}