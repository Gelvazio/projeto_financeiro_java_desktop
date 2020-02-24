/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleCadastro;

import ModeloCadastro.Pais;
import Principal.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author geo
 */
public class PaisDB {

    private static final String sqlTodos = "SELECT * FROM PAIS ORDER BY CD_PAIS";
    private static final String sqlExcluir = "DELETE FROM PAIS WHERE CD_PAIS= ?";
    private static final String sqlInserir
            = "INSERT INTO PAIS (CD_PAIS, NM_PAIS, CD_USUARIO,"
            + "DT_ALT, HR_ALT, DT_CAD, HR_CAD, CD_FILIAL) "
            + "VALUES "
            + "(?,?,?,"
            + "CAST('NOW' AS DATE),"
            + "CAST('NOW' AS TIME),"
            + "CAST('NOW' AS DATE),"
            + "CAST('NOW' AS TIME),"
            + "?)                  ";
    private static final String sqlAlterar
            = "UPDATE pais                       "
            + "SET pais.nm_pais= ?,            "
            + "CD_USUARIO=?,                   "
            + "PAIS.DT_ALT=CAST('NOW' AS DATE),"
            + "PAIS.HR_ALT=CAST('NOW' AS TIME),"
            + "PAIS.CD_FILIAL=?                "
            + "WHERE cd_pais = ?               ";
    private static final String sqlPais = "SELECT count(*) as total FROM pais WHERE CD_pais=?";

    public DefaultComboBoxModel getComboPais() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTodos);
            while (rs.next()) {
                modelo.addElement(rs.getString("NM_PAIS"));
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, getComboPais(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return modelo;
    }

    public boolean alterarPais(Pais pais) {
        boolean alterou = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlAlterar);
            pstmt.setString(1, pais.getNm_pais());
            pstmt.setInt(2, pais.getCd_usuario());
            pstmt.setInt(3, pais.getCd_filial());
            pstmt.setInt(4, pais.getCd_pais());
            pstmt.executeUpdate();
            alterou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de sql. alterarCidade(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return alterou;
    }

    public boolean inserirPais(Pais pais) {
        boolean inseriu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlInserir);
            pstmt.setInt(1, pais.getCd_pais());
            pstmt.setString(2, pais.getNm_pais());
            pstmt.setInt(3, pais.getCd_usuario());
            pstmt.setInt(4, pais.getCd_filial());
            pstmt.executeUpdate();
            inseriu = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de sql. inserirCidade(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return inseriu;
    }

    public boolean excluirPais(String cd_estado, int cd_municipio) {
        boolean excluiu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlExcluir);
            pstmt.setString(1, cd_estado);
            pstmt.setInt(2, cd_municipio);
            pstmt.executeUpdate();
            excluiu = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de sql. excluirCidade(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return excluiu;
    }

    public ArrayList getTodos() {
        ArrayList listaPais = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTodos);
            while (rs.next()) {
                int auxcd_pais = rs.getInt("cd_pais");
                String auxnm_pais = rs.getString("nm_pais");
                int auxcd_usuario = rs.getInt("cd_usuario");
                int auxcd_filial = rs.getInt("cd_filial");

                Pais pais = new Pais(
                        auxcd_pais,
                        auxnm_pais,
                        auxcd_usuario,
                        auxcd_filial);
                listaPais.add(pais);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de sql. getTodos(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return listaPais;
    }

    public boolean getPais(int cd_pais) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlPais);
            pstmt.setInt(1, cd_pais);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("total") > 0) {
                    existe = true;
                } else {
                    existe = false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getPais(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return existe;
    }
}
