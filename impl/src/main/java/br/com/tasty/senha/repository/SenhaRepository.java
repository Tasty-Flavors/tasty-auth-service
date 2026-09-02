package br.com.tasty.senha.repository;


import br.com.tasty.usuario.model.SenhaModelImpl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface SenhaRepository {

    @Update("""
        UPDATE USUARIO
        SET
            SENHA_USUARIO = #{senhaModel.novaSenha},
            DATA_ULTIMA_ATUALIZACAO_DA_CONTA = CURRENT_TIMESTAMP,
            TENTATIVA_LOGIN = 0
        WHERE CODIGO_USUARIO = #{senhaModel.codigoUsuario}
    """)
    void alterarSenha(@Param("senhaModel") SenhaModelImpl senhaModel);

}