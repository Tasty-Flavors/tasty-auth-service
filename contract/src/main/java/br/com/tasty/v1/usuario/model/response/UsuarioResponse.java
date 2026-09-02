package br.com.tasty.v1.usuario.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    private Integer id;
    private String nomeEstabelecimento;
    private String email;
    private Integer ativo;
    private LocalDateTime dataCadastro;
}