/*
 * Copyright (C) 2018 Gelvazio Camargo
 *
 * Este arquivo é parte do programa DTS SYSTEMS - SOFTWARE COMERCIAL;
 * O DTS SYSTEMS e um software livre; voce pode redistribui-lo e/ou modifica-lo
 * dentro dos termos da Licenca Publica Geral GNU como publicada pela Fundacao do    
 * Software Livre - FSF;
 *
 * Este sistema e distribuido na esperanca de ser util, mas SEM NENHUMA GARANTIA,
 * sem uma garantia implicita de ADEQUACAO a qualquer MERCADO ou APLICACAO EM PARTICULAR
 * Veja GNU General Public License para mais detalhes.
 *
 * Voce deve ter recebido uma cópia da Licenca Publica Geral GNU/GPL
 * com este programa.  Se não, veja <http://www.gnu.org/licenses/>.
 */
package View;

import Principal.Conexao;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jessica Moratelli
 */
public class ViewManutencaoPadrao extends javax.swing.JInternalFrame{
    
    public ViewManutencaoPadrao() {
        centro();
    }
        
    //Metodo que centraliza a tela
    protected void centro() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        // variavel largura com a largura da tela
        int screenWidth = screenSize.width - this.getWidth();
        // variavel largura com a altura da tela
        int screenHeigth = screenSize.height - this.getHeight();
        //Divide o tamanho da tela para ficar no centro
        setLocation(screenWidth / 2, screenHeigth / 2);
    }

    public static void mensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso!", JOptionPane.OK_OPTION);
    }

    public void PegaCodigoUsuarioLogado() {
        JOptionPane.showMessageDialog(null, "Implementar o Metodo de pegar dados do Usuario Logado!!");
    }

    public void PegaCodigoFilialUsuarioLogado(int cd_usuariologado) {
        //JOptionPane.showMessageDialog(null, "IMplementar o Metodo de pegar dados da Filial do  Usuario Logado!!");
        cd_usuariologado = 1;
    }
    
    //METODOS PADROES DA CLASSE VIEW_MANUTENCAO FILHO
    
    protected void cancelaEdicao(){
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente Cancelar a Edição?");
        if (resposta == JOptionPane.YES_OPTION) {
            habilitaCampos(false);
        }
    }
    
    protected void onKeyPressedCampoCodigo(java.awt.event.KeyEvent evt, JTextField campoCodigo){        
        String codigo = campoCodigo.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (codigo.equals("")) {
                preencheCodigoGenerator(campoCodigo);
                //habilitaCampos(true);
            } else {
                //ValidaCampoCodigoNaoNulo();
            }
        }
    }
    
    protected void preencheCodigoGenerator(JTextField campoCodigo, JTextField campoForFocus, String nomeGenerator) {
        //no postgresql a busca da sequencia se da pelo campo do tipo SERIAL conforme abaixo:
        //SELECT last_value  FROM cor_codigo_seq
        //para os inserts feitos neste campo, nao precisa ser passado o codigo 
        //mas se quiser passar o codigo, vai pegar o codigo que foi passado
        
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT GEN_ID(CD_PESSOA, 1) FROM RDB$DATABASE");
            if (rs.next()) {
                int auxCodigoGenerator = rs.getInt("GEN_ID");
                int auxCodigo = auxCodigoGenerator + 1;
                String a;
                a = "" + auxCodigo;
                campoCodigo.setText(a);
                campoForFocus.grabFocus();
            }
        } catch (SQLException erro) {
            mensagemErro("Erro no Método:getCodigoGenerator() \n Classe:"+getClass()+"\n Erro:"+erro.getMessage());            
        } finally {
            Conexao.closeAll(conn);
        }
    }
    
    protected void processaDadosInclusao(){
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente gravar o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
            gravarAlterar();
        }
    }
    
    //metodos abstratos, ou nao
    
    protected void gravarAlterar(){
        
    }
}
