package br.com.tasty.v1.usuario.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SenhaModel {
    private Long codigoUsuario;
    private String senhaAtual;
    private String novaSenha;

}