package br.com.tasty.v1.usuario.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroUsuarioRequest {
    @NotBlank
    private String nomeEstabelecimento;
    @Email(message = "O e-mail tem de estar no formato correto")
    @Pattern(regexp = "^[^=]+$", message = "O e-mail não pode conter o caractere '='.")
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
}