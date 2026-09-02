package br.com.tasty.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SenhaModelImpl {
    private Integer codigoUsuario;
    private String username;
    private String senhaAnterior;
    private String novaSenha;
    private String novaSenha256;
    private Integer qtdSenhasAnteriores;
}
