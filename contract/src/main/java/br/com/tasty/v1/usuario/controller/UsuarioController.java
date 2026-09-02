package br.com.tasty.v1.usuario.controller;

import br.com.tasty.v1.usuario.UsuarioFacade;
import br.com.tasty.v1.usuario.model.CadastroUsuarioRequest;
import br.com.tasty.v1.usuario.model.SenhaRequest;
import br.com.tasty.v1.usuario.model.response.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioFacade usuarioFacade;

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@Valid @RequestBody CadastroUsuarioRequest request) {
        usuarioFacade.cadastrar(request);
    }


    @PutMapping("/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(
            @RequestAttribute("id") Integer codigoUsuario,
            @Valid @RequestBody SenhaRequest request
    ) {
        usuarioFacade.alterarSenha(codigoUsuario, request.input(codigoUsuario));
    }

    @GetMapping("/me")
    public UsuarioResponse buscarUsuarioLogado(
            @RequestAttribute("id") Integer codigoUsuario
    ) {
        return usuarioFacade.buscarUsuarioLogado(codigoUsuario);
    }

}