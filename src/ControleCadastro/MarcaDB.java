package ControleCadastro;

import ModeloCadastro.Cor;
import Principal.Conexao;
import Principal.MetodosGlobais;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Gelvazio Camargo
 */
public class MarcaDB extends MetodosGlobais {

    private static final String sqlTodos
            = "SELECT * FROM MARCA ORDER BY CD_MARCA";
    private static final String sqlExcluir
            = "DELETE FROM MARCA WHERE CD_MARCA= ?";
    private static final String sqlInserir
            = "INSERT INTO COR                  "
            + "(CD_COR, DS_COR, DT_ALT, DT_CAD, "
            + "HR_CAD, HR_ALT, CD_USUARIO)      "
            + "VALUES                           "
            + "(?,?,                            "
            + "CAST('NOW' AS DATE),             "
            + "CAST('NOW' AS DATE),             "
            + "CAST('NOW' AS TIME),             "
            + "CAST('NOW' AS TIME),             "
            + "?)                               ";
    private static final String sqlAlterar
            = "UPDATE COR  SET                     "
            + "    COR.DS_COR=?,                   "
            + "    COR.DT_ALT=CAST('NOW' AS DATE), "
            + "    COR.HR_ALT=CAST('NOW' AS TIME), "
            + "    COR.CD_USUARIO=?,               "
            + "WHERE                               "
            + "    (CD_COR = ? )                   ";
    private static final String sqlCor
            = "SELECT                "
            + "    count(*) as total "
            + "FROM                  "
            + "    MARCA               "
            + "WHERE                 "
            + "    CD_MARCA=?          ";

    public DefaultComboBoxModel getComboMarca() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTodos);
            while (rs.next()) {
                modelo.addElement(rs.getString("ds_marca"));
            }
        } catch (SQLException erro) {
            mensagemErro("Erro no sql, getComboCor(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return modelo;
    }

    public boolean alterarCor(Cor cor) {
        boolean alterou = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlAlterar);
            pstmt.setString(1, cor.getDs_cor());
            pstmt.setInt(2, cor.getCd_usuario());
            pstmt.setInt(3, cor.getCd_cor());
            pstmt.executeUpdate();
            alterou = true;
        } catch (SQLException erro) {
            mensagemErro("Erro de sql. alterarCor(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return alterou;
    }

    public boolean inserirCor(Cor cor) {
        boolean inseriu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlInserir);
            pstmt.setInt(1, cor.getCd_cor());
            pstmt.setString(2, cor.getDs_cor());
            pstmt.setInt(3, cor.getCd_usuario());
            pstmt.executeUpdate();
            inseriu = true;
        } catch (SQLException erro) {
           mensagemErro("Erro de sql. inserirCor(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return inseriu;
    }

    public boolean excluirCor(int cd_cor) {
        boolean excluiu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlExcluir);
            pstmt.setInt(1, cd_cor);
            pstmt.executeUpdate();
            excluiu = true;
        } catch (SQLException erro) {
            mensagemErro("Erro de sql. excluirCor(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return excluiu;
    }

    public ArrayList getTodos() {
        ArrayList listaCor = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlTodos);
            while (rs.next()) {
                int auxcd_cor = rs.getInt("cd_cor");
                String auxnm_cor = rs.getString("ds_cor");
                int auxcd_usuario = rs.getInt("cd_usuario");

                Cor cor = new Cor(
                        auxcd_cor,
                        auxnm_cor,
                        auxcd_usuario
                );
                listaCor.add(cor);
            }
        } catch (SQLException erro) {
            mensagemErro("Erro de sql. getTodos(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return listaCor;
    }

    public boolean getCor(int cd_cor) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlCor);
            pstmt.setInt(1, cd_cor);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("total") > 0) {
                    existe = true;
                } else {
                    existe = false;
                }
            }
        } catch (SQLException e) {
            mensagemErro("Erro de SQL. getCor(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return existe;
    }
}
