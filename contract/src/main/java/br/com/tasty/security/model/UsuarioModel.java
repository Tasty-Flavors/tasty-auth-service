package br.com.tasty.security.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    private Integer codigoUsuario;
    private String nomeEstabelecimento;
    private String email;
    private String senha;
    private Integer ativo;
    private Integer habilitado;
    private String role;
    private Integer contaBloqueada;
    private Integer contaExpirada;
    private Integer tentativaLogin;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataUltimoLogin;
    private LocalDateTime dataUltimaAtualizacaoDaConta;

}