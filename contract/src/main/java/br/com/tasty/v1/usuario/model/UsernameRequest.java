package br.com.tasty.v1.usuario.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsernameRequest {
    @Email(message = "O e-mail tem de estar no formato correto")
    @Pattern(regexp = "^[^=]+$", message = "O e-mail não pode conter o caractere '='.")
    private String username;
}
