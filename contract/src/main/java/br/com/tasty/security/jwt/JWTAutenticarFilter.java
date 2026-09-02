package br.com.tasty.security.jwt;

import br.com.tasty.security.jwt.data.DetalheUsuarioData;
import br.com.tasty.security.model.UsuarioModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

import static br.com.tasty.exception.ExceptionMaker.buildGenericException;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTBuildToken jwtBuildToken;

    public JWTAutenticarFilter(AuthenticationManager authenticationManager,
                               JWTBuildToken jwtBuildToken) {
        this.authenticationManager = authenticationManager;
        this.jwtBuildToken = jwtBuildToken;

        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) {
        var usuarioModel = new UsuarioModel();

        try {

            usuarioModel = preparaUsuarioModel(request);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            usuarioModel.getEmail(),
                            usuarioModel.getSenha(),
                            new ArrayList<>()
                    )
            );

        } catch (BadCredentialsException | IOException e) {

            throw buildGenericException(
                    "falha.autenticar.usuario",
                    e.getMessage(),
                    BAD_REQUEST
            );
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        var usuarioData = (DetalheUsuarioData) authResult.getPrincipal();

        response.getWriter().write(
                jwtBuildToken.buildTokenResponseAsString(
                        usuarioData,
                        request.getRemoteAddr()
                )
        );

        response.getWriter().flush();
    }

    private UsuarioModel preparaUsuarioModel(HttpServletRequest request)
            throws IOException {

        var usuarioModel = new ObjectMapper()
                .readValue(request.getInputStream(), UsuarioModel.class);

        request.setAttribute("userName", usuarioModel.getEmail());

        return usuarioModel;
    }
}