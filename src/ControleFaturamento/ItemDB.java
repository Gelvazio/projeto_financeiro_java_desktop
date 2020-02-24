package ControleFaturamento;

import static ControleFaturamento.VendaDB.mensagemErro;
import ModeloFaturamento.Item;
import Principal.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio
 */
public class ItemDB {

    //Métodos
    //Generator
    //Alterar
    //Inserir
    //Excluir
    //Listar Todos os itens da Venda
    private static final String sqlExcluir
            = "DELETE FROM ITENS_ORC WHERE ITENS_ORC.CD_FILIAL =? AND ITENS_ORC.CD_MOVIMENTO=? AND ITENS_ORC.CD_SEQ_ITE_PRO=?";
    private static final String sqlInserir
            = "INSERT INTO ITENS_ORC "
            + "(CD_FILIAL, "
            + "CD_MOVIMENTO, "
            + "CD_SEQ_ITE_PRO, "
            + "CD_REFER_PRO, "
            + "QT_ITE_PRO, "
            + "VL_CUS_ITE_PRO, "
            + "VL_VEN_ITE_PRO, "
            + "VL_REAL_ITE_PRO, "
            + "TX_ICMS, "
            + "VL_BASE_ICM, "
            + "VL_ICM, "
            + "TX_ICMS_SUBSTITUICAO, "
            + "VL_MVA, "
            + "VL_BASE_ICM_SUBSTITUICAO, "
            + "VL_ICM_SUBSTITUICAO,"
            + "TX_ISS, "
            + "VL_BASE_ISS, "
            + "VL_ISS, "
            + "CST_IPI, "
            + "VL_BASE_IPI, "
            + "TX_IPI, "
            + "VL_IPI, "
            + "CST_PIS, "
            + "VL_BASE_PIS, "
            + "TX_PIS, "
            + "VL_PIS, "
            + "CST_COFINS, "
            + "VL_BASE_COFINS, "
            + "TX_COFINS, "
            + "VL_COFINS, "
            + "DT_EMI_DOC, "
            + "CD_TIPO_DOC, "
            + "AB_UNIDADE, "
            + "CD_VENDEDOR, "
            + "CD_USUARIO, "
            + "DT_ALT, "
            + "HR_ALT, "
            + "DT_CAD, "
            + "HR_CAD, "
            + "CFOP, "
            + "VL_DESCONTO, "
            + "VL_ACRESCIMO, "
            + "CD_GRUPO_FISCAL, "
            + "CD_CST, "
            + "VL_PESO_LIQUIDO, "
            + "VL_PESO_BRUTO, "
            + "VL_VOLUME, "
            + "FG_SITUACAO, "
            + "VL_RATEADO, "
            + "VL_FRETE, "
            + "VL_IMPOSTOS) "
            + "VALUES "
            + "(?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, "
            + "?, ?, ?, ?, ?, ?, "
            + "?, ?,?, ?, ?, "
            + "?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, "
            + "?,"
            + "?,"
            + "?,"
            + "?,"
            + "?, ?, ?, ?, ?,"
            + "?, ?, ?, ?, ?,"
            + "?, ?);";

    private static final String sqlAlterar
            = "UPDATE ITENS_ORC SET CD_REFER_PRO = ?,"
            + "    QT_ITE_PRO = ?,"
            + "    VL_CUS_ITE_PRO = ?,"
            + "    VL_VEN_ITE_PRO = ?,"
            + "    VL_REAL_ITE_PRO = ?,"
            + "    TX_ICMS = ?,"
            + "    VL_BASE_ICM = ?,"
            + "    VL_ICM = ?,"
            + "    TX_ICMS_SUBSTITUICAO = ?,"
            + "    VL_MVA = ?,"
            + "    VL_BASE_ICM_SUBSTITUICAO = ?,"
            + "    VL_ICM_SUBSTITUICAO = ?,"
            + "    TX_ISS = ?,"
            + "    VL_BASE_ISS = ?,"
            + "    VL_ISS = ?,"
            + "    CST_IPI = ?,"
            + "    VL_BASE_IPI = ?,"
            + "    TX_IPI = ?,"
            + "    VL_IPI = ?,"
            + "    CST_PIS = ?,"
            + "    VL_BASE_PIS = ?,"
            + "    TX_PIS = ?,"
            + "    VL_PIS = ?,"
            + "    CST_COFINS =?,"
            + "    VL_BASE_COFINS = ?,"
            + "    TX_COFINS = ?,"
            + "    VL_COFINS = ?,"
            + "    DT_EMI_DOC = ?,"
            + "    CD_TIPO_DOC = ?,"
            + "    AB_UNIDADE = ?,"
            + "    CD_VENDEDOR =?,"
            + "    CD_USUARIO = ?,"
            + "    DT_ALT =?,"
            + "    HR_ALT = ?,"
            +"     DT_CAD = ?,"
            +"     HR_CAD = ?,"
            + "    CFOP = ?,"
            + "    VL_DESCONTO = ?,"
            + "    VL_ACRESCIMO = ?,"
            + "    CD_GRUPO_FISCAL = ?,"
            + "    CD_CST = ?,"
            + "    VL_PESO_LIQUIDO = ?,"
            + "    VL_PESO_BRUTO = ?,"
            + "    VL_VOLUME = ?,"
            + "    FG_SITUACAO = ?,"
            + "    VL_RATEADO = ?,"
            + "    VL_FRETE = ?,"
            + "    VL_IMPOSTOS = ?"
            + "WHERE (CD_FILIAL = ?) AND (CD_MOVIMENTO = ?) AND (CD_SEQ_ITE_PRO = ?);";
           
    private static final String sqlBuscaDados
            = "SELECT                "
            + "    count(*) as total "
            + "FROM                  "
            + "    ITENS_ORC         "
            + "WHERE                 "
            + "    CD_FILIAL = ?     "
            + "    AND               "
            + "    CD_MOVIMENTO=?    "
            + "    AND               "
            + "    CD_SEQ_ITE_PRO=?  ";
    public static void mensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
    }
    public boolean alterar(Item item) {
        boolean alterou = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlAlterar);
            pstmt.setLong(1, item.getCd_refer_pro());
            pstmt.setDouble(2, item.getQt_ite_pro());
            pstmt.setDouble(3, item.getVl_cus_ite_pro());
            pstmt.setDouble(4, item.getVl_ven_ite_pro());
            pstmt.setDouble(5, item.getVl_real_ite_pro());
            pstmt.setDouble(6, item.getTx_icms());
            pstmt.setDouble(7, item.getVl_base_icm());
            pstmt.setDouble(8, item.getVl_icm());
            pstmt.setDouble(9, item.getTx_icms_substituicao());
            pstmt.setDouble(10, item.getVl_mva());
            pstmt.setDouble(11, item.getVl_base_icm_substituicao());
            pstmt.setDouble(12, item.getVl_icm_substituicao());
            pstmt.setDouble(13, item.getTx_iss());
            pstmt.setDouble(14, item.getVl_base_iss());
            pstmt.setDouble(15, item.getVl_iss());
            pstmt.setInt(16, item.getCst_ipi());
            pstmt.setDouble(17, item.getVl_base_ipi());
            pstmt.setDouble(18, item.getTx_ipi());
            pstmt.setDouble(19, item.getVl_ipi());
            pstmt.setDouble(20, item.getCst_ipi());
            pstmt.setDouble(21, item.getVl_base_pis());
            pstmt.setDouble(22, item.getTx_pis());
            pstmt.setDouble(23, item.getVl_pis());
            pstmt.setInt(24, item.getCst_cofins());
            pstmt.setDouble(25, item.getVl_base_cofins());
            pstmt.setDouble(26, item.getTx_cofins());
            pstmt.setDouble(27, item.getVl_cofins());
            pstmt.setDate(28, (Date) item.getDt_emi_doc());
            pstmt.setDouble(29, item.getCd_tipo_doc());
            pstmt.setString(30, item.getAb_unidade());
            pstmt.setInt(31, item.getCd_vendedor());
            pstmt.setInt(32, item.getCd_usuario());
            pstmt.setDate(33, (Date) item.getDt_alt());
            pstmt.setTime(34,item.getHr_alt());
            pstmt.setDate(35, (Date) item.getDt_cad());
            pstmt.setTime(36,  item.getHr_cad());
            pstmt.setInt(37, item.getCfop());
            pstmt.setDouble(38, item.getVl_desconto());
            pstmt.setDouble(39, item.getVl_acrescimo());
            pstmt.setInt(40, item.getCd_grupo_fiscal());
            pstmt.setInt(41, item.getCd_cst());
            pstmt.setDouble(42, item.getVl_peso_liquido());
            pstmt.setDouble(43, item.getVl_peso_bruto());
            pstmt.setDouble(44, item.getVl_volume());
            pstmt.setInt(45, item.getFg_situacao());
            pstmt.setDouble(46, item.getVl_rateado());
            pstmt.setDouble(47, item.getVl_frete());
            pstmt.setDouble(48, item.getVl_impostos());            
            pstmt.setInt(49, item.getCd_filial());
            pstmt.setInt(50, item.getCd_movimento());
            pstmt.setInt(51, item.getCd_seq_ite_pro());
            pstmt.executeUpdate();
            alterou = true;
        } catch (SQLException erro) {
            mensagemErro("sql. alterarItem(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return alterou;
    }

    public boolean inserir(Item item) {
        boolean inseriu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlInserir);
            pstmt.setInt(1, item.getCd_filial());
            pstmt.setInt(2, item.getCd_movimento());
            pstmt.setInt(3, item.getCd_seq_ite_pro());
            pstmt.setLong(4, item.getCd_refer_pro());            
            pstmt.setDouble(5, item.getQt_ite_pro());
            pstmt.setDouble(6, item.getVl_cus_ite_pro());
            pstmt.setDouble(7, item.getVl_ven_ite_pro());
            pstmt.setDouble(8, item.getVl_real_ite_pro());
            pstmt.setDouble(9, item.getTx_icms());
            pstmt.setDouble(10, item.getVl_base_icm());
            pstmt.setDouble(11, item.getVl_icm());            
            pstmt.setDouble(12, item.getTx_icms_substituicao());
            pstmt.setDouble(13, item.getVl_mva());
            pstmt.setDouble(14, item.getVl_base_icm_substituicao());
            pstmt.setDouble(15, item.getVl_icm_substituicao());
            pstmt.setDouble(16, item.getTx_iss());
            pstmt.setDouble(17, item.getVl_base_iss());
            pstmt.setDouble(18, item.getVl_iss());
            pstmt.setInt(19, item.getCst_ipi());
            pstmt.setDouble(20, item.getVl_base_ipi());
            pstmt.setDouble(21, item.getTx_ipi());            
            pstmt.setDouble(22, item.getVl_ipi());
            pstmt.setDouble(23, item.getCst_ipi());
            pstmt.setDouble(24, item.getVl_base_pis());
            pstmt.setDouble(25, item.getTx_pis());
            pstmt.setDouble(26, item.getVl_pis());
            pstmt.setInt(27, item.getCst_cofins());
            pstmt.setDouble(28, item.getVl_base_cofins());
            pstmt.setDouble(29, item.getTx_cofins());
            pstmt.setDouble(30, item.getVl_cofins());
            pstmt.setDate(31,(Date) item.getDt_emi_doc());            
            pstmt.setDouble(32, item.getCd_tipo_doc());
            pstmt.setString(33, item.getAb_unidade());
            pstmt.setInt(34, item.getCd_vendedor());
            pstmt.setInt(35, item.getCd_usuario());
            pstmt.setDate(36, (Date) item.getDt_alt());
            pstmt.setTime(37,item.getHr_alt());
            pstmt.setDate(38, (Date) item.getDt_cad());
            pstmt.setTime(39,  item.getHr_cad());
            pstmt.setInt(40, item.getCfop());
            pstmt.setDouble(41, item.getVl_desconto());            
            pstmt.setDouble(42, item.getVl_acrescimo());
            pstmt.setInt(43, item.getCd_grupo_fiscal());
            pstmt.setInt(44, item.getCd_cst());
            pstmt.setDouble(45, item.getVl_peso_liquido());
            pstmt.setDouble(46, item.getVl_peso_bruto());
            pstmt.setDouble(47, item.getVl_volume());
            pstmt.setInt(48, item.getFg_situacao());
            pstmt.setDouble(49, item.getVl_rateado());
            pstmt.setDouble(50, item.getVl_frete());
            pstmt.setDouble(51, item.getVl_impostos());
            pstmt.executeUpdate();
            inseriu = true;
        } catch (SQLException erro) {
            mensagemErro("sql. inserirItem(): \n" + erro.getMessage());            
        } finally {
            Conexao.closeAll(conn);
        }
        return inseriu;
    }

    public boolean excluir(int cd_filial,int cd_movimento,int sequencia) {
        boolean excluiu = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlExcluir);
            pstmt.setInt(1, cd_filial);
            pstmt.setInt(2, cd_movimento);
            pstmt.setInt(3, sequencia);
            pstmt.executeUpdate();
            excluiu = true;
        } catch (SQLException erro) {
            mensagemErro("sql. excluirItem(): \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return excluiu;
    }

    public boolean getItem(int cd_filial,int cd_movimento,int sequencia) {
        boolean existe = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sqlBuscaDados);
            pstmt.setInt(1, cd_filial);
            pstmt.setInt(2, cd_movimento);
            pstmt.setInt(3, sequencia);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("total") > 0) {
                    existe = true;
                } else {
                    existe = false;
                }
            }
        } catch (SQLException e) {
            mensagemErro("SQL. getItem(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return existe;
    }



}
