package br.com.tasty.senha.service;

import br.com.tasty.senha.repository.SenhaRepository;
import br.com.tasty.usuario.model.SenhaModelImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static br.com.tasty.exception.ExceptionMaker.buildGenericException;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class SenhaServiceImpl {

    private static final String SENHA_ERRO = "senha.erro";

    private final PasswordEncoder passwordEncoder;
    private final SenhaRepository senhaRepository;

    public SenhaServiceImpl(
            PasswordEncoder passwordEncoder,
            SenhaRepository senhaRepository) {
        this.passwordEncoder = passwordEncoder;
        this.senhaRepository = senhaRepository;
    }

    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public boolean validarSenha(String senhaDigitada, String senhaCriptografada) {
        return passwordEncoder.matches(senhaDigitada, senhaCriptografada);
    }

    public void alterarSenha(SenhaModelImpl senhaModel) {
        try {
            senhaRepository.alterarSenha(senhaModel);
        } catch (DataAccessException e) {
            throw buildGenericException(
                    SENHA_ERRO,
                    e.getMessage(),
                    INTERNAL_SERVER_ERROR
            );
        }
    }
}