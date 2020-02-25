package Padrao;

import Principal.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jessica Maria Koch
 */
public class Query extends ComportamentoPadrao{

    /*
    private conexao;

    public Query(conexao ) {
        this.<error> = <error>;
    }

    public  Query() {
        $this->conexao = ConexaoDB::getInstance();
    }

    public void select1(String sSql) {
        sSql = this.query(sSql);
        Object oLinhaAtual[];
        
        if (oLinhaAtual = pg_fetch_assoc(sSql)) {
            return oLinhaAtual;
        }
        return false;
    }
    
    */
    public ArrayList select(String sSql) {
        ArrayList select = new ArrayList();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            pstmt = conn.prepareStatement(sSql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                select.add(rs.getRow());
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no ArrayList select: \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return select;
    }
   
    
    /*
    public function selectAll($sSql) {
        $rSql = $this->query($sSql);
        $aRetorno = Array();
        while ($oLinhaAtual = pg_fetch_assoc($rSql)) {
            $aRetorno[] = $oLinhaAtual;
        }
        return $aRetorno;
    }

    public function query($sSql) {
        $rRetorno = @pg_query($this->conexao, $sSql);
        if ($rRetorno !== false) {
            return $rRetorno;
        }
        //echo "<pre>" . print_r(pg_last_error($this->conexao) . $sSql) . "</pre>";
        throw new Exception('Erro ao executar comando SQL');
    }
    
    */

}
 

