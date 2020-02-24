/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleCadastro;

import ModeloCadastro.Usuario;
import Principal.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio Camargo
 */
public class UsuarioDB {

    //Parte dos SQL de Consultas
    private static final String sqlTodos = "SELECT * FROM USUARIO";
    private static final String sqlInserir
            = "INSERT INTO USUARIO (CD_USUARIO,DS_USUARIO, "
            + "DS_SENHA, CD_FILIAL,DT_ALT, HR_ALT, DT_CAD, HR_CAD)"
            + "VALUES( ?, ?, ?, ? , "
            + "CAST('NOW' AS DATE),"
            + "CAST('NOW' AS TIME),"
            + "CAST('NOW' AS DATE),"
            + "CAST('NOW' AS TIME) )";
    private static final String sqlExcluir = "DELETE FROM usuario WHERE CD_usuario= ?";
    private static final String sqlAlterar
            = "UPDATE USUARIO SET "
            + "DS_USUARIO =?,"
            + "DS_SENHA =?,"
            + "CD_FILIAL=?,"
            + "DT_ALT=CAST('NOW' AS DATE),"
            + "HR_ALT=CAST('NOW' AS TIME)"
            + "WHERE CD_USUARIO = ?";
    //parte da filial deve ser implementado junto dos cadastros de filiais
    //posteriormente
    //atualmete por default sera sempre Um 1
    //+ " AND (CD_FILIAL = ?);";
    private static final String sqlConsultaUsuario = "SELECT (usuario.cd_usuario)as codigosql FROM usuario where usuario.cd_usuario=?";
    private static final String sqlConsultaUsuarioLogin = "SELECT (usuario.cd_usuario)as codigosql FROM usuario where usuario.ds_login=?";

    public boolean getUsuario(int CD_USUARIO) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlConsultaUsuario);
            pstmt.setInt(1, CD_USUARIO);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("codigosql") > 0) {
                    existe = true;
                } else {
                    existe = false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getUsuario: \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
            return existe;
        }
    }

    public boolean getLogin(String ds_login) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlConsultaUsuarioLogin);
            pstmt.setString(1, ds_login);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("codigosql") > 0) {
                    existe = true;
                } else {
                    existe = false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getUsuario: \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
            return existe;
        }
    }

    public boolean alterarUsuario(Usuario usuario) {
        boolean alterou = false;
        Connection conn = null;
        PreparedStatement pstmt;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlAlterar);
            pstmt.setString(1, usuario.getDs_usuario());
            pstmt.setString(2, usuario.getDs_senha());
            pstmt.setInt(3, usuario.getCd_filial());
            pstmt.setInt(4, usuario.getCd_usuario());
            pstmt.executeUpdate();
            alterou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql. alterarUsuario(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
            return alterou;
        }
    }

    public boolean excluirUsuario(int cd_usuario) {
        boolean excluiu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlExcluir);
            pstmt.setInt(1, cd_usuario);
            pstmt.executeUpdate();
            excluiu = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql. excluirUsuario():\n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
            return excluiu;
        }
    }

    public boolean inserirUsuario(Usuario usuario) {
        boolean inseriu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlInserir);
            /*
             "INSERT INTO USUARIO (CD_USUARIO,DS_USUARIO, "
             + "DS_SENHA, CD_FILIAL,DT_ALT, HR_ALT, DT_CAD, HR_CAD)"
             + "VALUES( ?, ?, ?, ? , "
             */
            pstmt.setInt(1, usuario.getCd_usuario());
            pstmt.setString(2, usuario.getDs_usuario());
            pstmt.setString(3, usuario.getDs_senha());
            pstmt.setInt(4, usuario.getCd_filial());

            pstmt.executeUpdate();
            inseriu = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql. inserirUsuarios(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return inseriu;
    }

    public ArrayList getTodos() {
        ArrayList listaUsuario = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTodos);
            //OBSERVE MUITO IMPORTANTE!!!!!
            //NAO DEIXAR ESPAÇOS,POIS DA ERROS DEPOIS!!!!
            while (rs.next()) {
                //Campos Inteiros
                //NÃO DEIXAR ESPAÇOS!!!!!!
                int cd_usuario = rs.getInt("cd_usuario");
                String ds_usuario = rs.getString("ds_usuario");
                String ds_senha = rs.getString("ds_senha");
                int cd_filial = rs.getInt("cd_filial");
                //NÃO DEIXAR ESPAÇOS!!!!!!
                //Usuario usuario = new Usuario(auxLogin, auxSenha_Dois, auxCd_Filial, auxCodigoDois);
                Usuario usuario = new Usuario(
                        ds_usuario,
                        ds_senha,
                        cd_filial,
                        cd_usuario);
                listaUsuario.add(usuario);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, getTodos(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
            return listaUsuario;
        }
    }
}
