/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControleCadastro;

import ModeloCadastro.UnidadeMedida;
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
 * @author Gelvazio Camargo
 */
public class NCMSHDB {

    private static final String sqlTodos
            = "SELECT * FROM unidade_medida order by unidade_medida.cd_unidade";
    private static final String sqlInserir
            = "INSERT INTO UNIDADE_MEDIDA"
            + "(CD_UNIDADE, DS_SIGLA,    "
            + "DS_UNIDADE, TX_FATOR, "
            + "FG_VENDA_FRACIONARIA, "
            + "DT_ALT, DT_CAD, HR_CAD, "
            + "HR_ALT, CD_USUARIO, "
            + "CD_FILIAL)"
            + "VALUES"
            + "(?,?,?,?,?,        "
            + "CAST('NOW' AS DATE),"
            + "CAST('NOW' AS DATE),"
            + "CAST('NOW' AS TIME),"
            + "CAST('NOW' AS TIME),"
            + "?,?)";
    private static final String sqlExcluir = "DELETE FROM UNIDADE_MEDIDA WHERE CD_UNIDADE = ?";
    private static final String sqlAlterar
            = "	UPDATE UNIDADE_MEDIDA	"
            + "	SET DS_SIGLA = ?,	"
            + "	    DS_UNIDADE =?',	"
            + "	    TX_FATOR = ?,	"
            + "	    FG_VENDA_FRACIONARIA = ?,	"
            + "	    DT_ALT = CAST('NOW' AS DATE),	"
            + "	    HR_ALT =  CAST('NOW' AS TIME),	"
            + "	    CD_USUARIO = ?,	"
            + "	    CD_FILIAL = ?	"
            + "	WHERE (CD_UNIDADE = ?);";
    private static final String sqlconsultancmsh = "SELECT count(*) as total FROM ncmsh WHERE cd_ncmsh=?";

    public DefaultComboBoxModel getComboUnidadeMedida() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTodos);
            while (rs.next()) {
                modelo.addElement(rs.getString("CD_ESTADO"));
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, getComboEstado(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return modelo;
    }

    public boolean alterarUnidadeMedida(UnidadeMedida unidademedida) {
        boolean alterou = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlAlterar);
            pstmt.setString(1, unidademedida.getDs_sigla());
            pstmt.setString(2, unidademedida.getDs_unidade());
            //pstmt.setInt(3, unidademedida.getTx_fator());
            //pstmt.setInt(4, unidademedida.getFg_venda_fracionada());
            pstmt.setInt(5, unidademedida.getCd_usuario());
            pstmt.setInt(6, unidademedida.getCd_filial());
            pstmt.setInt(7, unidademedida.getCd_unidade());
            pstmt.executeUpdate();
            alterou = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql. alterarUnidadeMedida(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return alterou;
    }

    public boolean excluirUnidadeMedida(int cd_unidade) {
        boolean excluiu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlExcluir);
            pstmt.setInt(1, cd_unidade);
            pstmt.executeUpdate();
            excluiu = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql. excluirUnidadeMedida(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return excluiu;
    }

    public boolean inserirUnidadeMedida(UnidadeMedida unidademedida) {
        boolean inseriu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlInserir);
            pstmt.setInt(1, unidademedida.getCd_unidade());
            pstmt.setString(2, unidademedida.getDs_sigla());
            pstmt.setString(3, unidademedida.getDs_unidade());
            //pstmt.setInt(4, unidademedida.getTx_fator());
            //pstmt.setInt(5, unidademedida.getFg_venda_fracionada());
            pstmt.setInt(6, unidademedida.getCd_usuario());
            pstmt.setInt(7, unidademedida.getCd_filial());
            pstmt.executeUpdate();
            inseriu = true;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql. inserirUnidadeMedida():\n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return inseriu;
    }

    public ArrayList getTodos() {

        ArrayList listaUnidadeMedida = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTodos);
            while (rs.next()) {
                int auxcd_unidade = rs.getInt("cd_unidade");
                String auxds_sigla = rs.getString("ds_sigla");
                String auxds_unidade = rs.getString("ds_unidade");
                int auxtx_fator = rs.getInt("tx_fator");
                int auxfg_venda_fracionada = rs.getInt("fg_venda_fracionaria");
                int auxcd_usuario = rs.getInt("cd_usuario");
                int auxcd_filial = rs.getInt("cd_filial");
                UnidadeMedida unidademedida = new UnidadeMedida(
                        auxcd_unidade,
                        auxds_sigla,
                        auxds_unidade,
                        //auxtx_fator,
                        //auxfg_venda_fracionada,
                        auxcd_filial,
                        auxcd_usuario);
                listaUnidadeMedida.add(unidademedida);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, getTodos():\n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return listaUnidadeMedida;
    }

    public boolean getNCMSH(int cd_ncmsh) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlconsultancmsh);
            pstmt.setInt(1, cd_ncmsh);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("total") > 0) {
                    existe = true;
                } else {
                    existe = false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. getNCMSH(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return existe;
    }
}
