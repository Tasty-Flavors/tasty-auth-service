package br.com.tasty.usuario.repository;

import br.com.tasty.usuario.model.UsuarioImpl;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UsuarioRepository {

    @Select("""
        SELECT
            CODIGO_USUARIO,
            NOME_ESTABELECIMENTO,
            LOGIN_USUARIO,
            SENHA_USUARIO,
            ATIVO,
            HABILITADO,
            ROLE,
            CONTA_BLOQUEADA,
            CONTA_EXPIRADA,
            TENTATIVA_LOGIN,
            DATA_CADASTRO,
            DATA_ULTIMO_LOGIN,
            DATA_ULTIMA_ATUALIZACAO_DA_CONTA
        FROM USUARIO
        WHERE LOWER(LOGIN_USUARIO) = LOWER(#{email})
        """)
    @Results(id = "usuarioResult", value = {
            @Result(column = "codigo_usuario", property = "codigoUsuario"),
            @Result(column = "nome_estabelecimento", property = "nomeEstabelecimento"),
            @Result(column = "login_usuario", property = "email"),
            @Result(column = "senha_usuario", property = "senha"),
            @Result(column = "ativo", property = "ativo"),
            @Result(column = "role", property = "role"),
            @Result(column = "habilitado", property = "habilitado"),
            @Result(column = "conta_bloqueada", property = "contaBloqueada"),
            @Result(column = "conta_expirada", property = "contaExpirada"),
            @Result(column = "tentativa_login", property = "tentativaLogin"),
            @Result(column = "data_cadastro", property = "dataCadastro"),
            @Result(column = "data_ultimo_login", property = "dataUltimoLogin"),
            @Result(column = "data_ultima_atualizacao_da_conta", property = "dataUltimaAtualizacaoDaConta")
    })
    UsuarioImpl buscaDadosDeLoginUsuario(@Param("email") String email);


    @Select("""
        SELECT
            CODIGO_USUARIO,
            NOME_ESTABELECIMENTO,
            LOGIN_USUARIO,
            SENHA_USUARIO,
            ATIVO,
            HABILITADO,
            ROLE,
            CONTA_BLOQUEADA,
            CONTA_EXPIRADA,
            TENTATIVA_LOGIN,
            DATA_CADASTRO,
            DATA_ULTIMO_LOGIN,
            DATA_ULTIMA_ATUALIZACAO_DA_CONTA
        FROM USUARIO
        WHERE CODIGO_USUARIO = #{codigoUsuario}
        """)
    @ResultMap("usuarioResult")
    UsuarioImpl buscaPorId(Integer codigoUsuario);


    @Insert("""
        INSERT INTO USUARIO
        (
            NOME_ESTABELECIMENTO,
            LOGIN_USUARIO,
            SENHA_USUARIO,
            ATIVO,
            HABILITADO,
            TENTATIVA_LOGIN,
            CONTA_BLOQUEADA,
            CONTA_EXPIRADA,
            DATA_CADASTRO,
            ROLE
        )
        VALUES
        (
            #{nomeEstabelecimento},
            #{email},
            #{senha},
            #{ativo},
            #{habilitado},
            #{tentativaLogin},
            #{contaBloqueada},
            #{contaExpirada},
            CURRENT_TIMESTAMP,
            #{role}
        )
        """)
    @Options(
            useGeneratedKeys = true,
            keyProperty = "codigoUsuario",
            keyColumn = "codigo_usuario"
    )
    void cadastrar(UsuarioImpl usuario);


    @Update("""
        UPDATE USUARIO
        SET TENTATIVA_LOGIN = COALESCE(TENTATIVA_LOGIN,0)+1
        WHERE LOWER(LOGIN_USUARIO)=LOWER(#{model.loginUsuario})
        """)
    void incrementarTentativaLogin(@Param("model") UsuarioImpl model);


    @Update("""
        UPDATE USUARIO
        SET TENTATIVA_LOGIN = 0,
            DATA_ULTIMO_LOGIN = CURRENT_TIMESTAMP
        WHERE LOWER(LOGIN_USUARIO)=LOWER(#{model.loginUsuario})
        """)
    void resetarTentativas(@Param("model") UsuarioImpl model);


    @Select("""
        SELECT
            CASE
                WHEN EXISTS (
                    SELECT 1
                    FROM USUARIO
                    WHERE LOWER(LOGIN_USUARIO) = LOWER(#{email})
                )
                THEN TRUE
                ELSE FALSE
            END
        """)
    Boolean validarEmailExistente(@Param("email") String email);


    @Update("""
        UPDATE USUARIO
        SET ATIVO = FALSE
        WHERE CODIGO_USUARIO = #{codigoUsuario}
        """)
    void desativar(Long codigoUsuario);
}