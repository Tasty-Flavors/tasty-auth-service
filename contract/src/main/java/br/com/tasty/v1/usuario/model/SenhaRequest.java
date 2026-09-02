package br.com.tasty.v1.usuario.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SenhaRequest {
    private Integer codigoUsuario;
    @NotNull(message = "Obrigatorio informar o username do usuario")
    private String username;
    @NotNull(message = "Obrigatorio informar a senha do usuario")
    private String novaSenha;

    public SenhaRequest input(Integer codigoUsuario) {
        return SenhaRequest.builder()
                .codigoUsuario(codigoUsuario)
                .username(this.username)
                .novaSenha(this.novaSenha)
                .build();
    }
}
