package Testes;

import ModeloCadastro.Cor;
import ModeloCadastro.Usuario;
import Principal.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio Camargo
 */
public class ExemploCommitERolback {

    private static final String sqlAlterarUsuario
            = "UPDATE USUARIO SET "
            + "DS_USUARIO =?,"
            + "DS_SENHA =?,"
            + "CD_FILIAL=?,"
            + "DT_ALT=CAST('NOW' AS DATE),"
            + "HR_ALT=CAST('NOW' AS TIME)"
            + "WHERE CD_USUARIO = ?";

    public boolean alterarUsuario(Usuario usuario) {
        boolean alterou = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();

            //conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sqlAlterarUsuario);
            pstmt.setString(1, usuario.getDs_usuario());
            pstmt.setString(2, usuario.getDs_senha());
            pstmt.setInt(3, usuario.getCd_filial());
            pstmt.setInt(4, usuario.getCd_usuario());

            //conn.TRANSACTION_NONE;
            //pstmt.execute
            pstmt.executeUpdate();
            alterou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de sql. alterarUsuario(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return alterou;
    }

    public static void main(String[] args) {
        System.out.println("Testes de Commit e rollback!!");
        String nome = "Usuario de Testes Geo";
        String senha = "senha";
        int cdusuario = 1;
        int cdfilial = 1;

        ExemploCommitERolback e = new ExemploCommitERolback();

        Usuario usuario = new Usuario(
                nome,
                senha,
                cdusuario,
                cdfilial);
        if (e.alterarUsuario(usuario)) {
            JOptionPane.showMessageDialog(null, "Usuario Alterado com Sucesso!!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar o Usuario!!");
        }

    }

    /*
     try {  
     // Bloco de Código  
     Conn = Conexao.getConexao();
     Conn.commit(SqlTeste); // Se estiver tudo ok, grava as alterações no B.D.  
     catch (Exception e) {  
     con.rollback(); // Descarta todas alterações, feitas desde o inicio da transação  
     System.out.println("Falha ao gravar dados: " + e.toString());  
     }
     conn.setAutoCommit(true);
    
     */
}
