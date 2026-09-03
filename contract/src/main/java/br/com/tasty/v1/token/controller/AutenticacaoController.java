package br.com.tasty.v1.token.controller;

import br.com.tasty.v1.token.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/oauth")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService autenticacaoService;

    @GetMapping("/token/info")
    public String validarToken(@RequestHeader("accessToken") String token) {
        return autenticacaoService.validarToken(token);
    }
}