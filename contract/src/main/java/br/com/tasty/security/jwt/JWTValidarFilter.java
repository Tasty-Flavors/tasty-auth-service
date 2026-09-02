package br.com.tasty.security.jwt;

import br.com.tasty.security.jwt.claim.JwtClaim;
import br.com.tasty.security.model.UsuarioModel;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class JWTValidarFilter extends BasicAuthenticationFilter {

    private static final String HEADER = "Authorization";
    private static final String PREFIXO = "Bearer ";

    private final JwtClaim jwtClaim;

    public JWTValidarFilter(
            AuthenticationManager authenticationManager,
            JwtClaim jwtClaim) {

        super(authenticationManager);
        this.jwtClaim = jwtClaim;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        String authorization = request.getHeader(HEADER);

        if (Objects.isNull(authorization) || !authorization.startsWith(PREFIXO)) {
            chain.doFilter(request, response);
            return;
        }

        String token = authorization.replace(PREFIXO, "");

        var authentication = getAuthenticationToken(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();

        request.setAttribute("id", usuario.getCodigoUsuario());
        request.setAttribute("loginUsuario", usuario.getEmail());
        request.setAttribute("nomeEstabelecimento", usuario.getNomeEstabelecimento());

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

        var jwt = jwtClaim.buscaClaim(token);

        UsuarioModel usuario = UsuarioModel.builder()
                .codigoUsuario(jwt.getClaim("id").asInt())
                .nomeEstabelecimento(jwt.getClaim("nomeEstabelecimento").asString())
                .email(jwt.getSubject())
                .build();

        return new UsernamePasswordAuthenticationToken(
                usuario,
                null,
                Collections.emptyList()
        );
    }
}