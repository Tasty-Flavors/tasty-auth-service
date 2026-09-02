package br.com.tasty.security.model;

import com.auth0.jwt.interfaces.Claim;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDeClaim {
    private String tipo;
    private Map<String, Claim> claim;
}
