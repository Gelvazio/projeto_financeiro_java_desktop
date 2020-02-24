/*
 * Copyright (C) 2016 Jessica Maria Koch
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
 

