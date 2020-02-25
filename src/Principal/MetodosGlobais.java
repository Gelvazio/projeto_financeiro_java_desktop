package Principal;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Gelvazio
 */
public class MetodosGlobais extends JInternalFrame {
    
    private String sequencia;

    public void Centro() {
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
        JOptionPane.showMessageDialog(null, "IMplementar o Metodo de pegar dados do Usuario Logado!!");
    }

    public void PegaCodigoFilialUsuarioLogado(int cd_usuariologado) {
        //JOptionPane.showMessageDialog(null, "IMplementar o Metodo de pegar dados da Filial do  Usuario Logado!!");
        cd_usuariologado = 1;
    }

    public int getProximoCodigoGenerator(String sequencia) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int auxCodigo = 0;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            ////      select nextval('notificacao_id_seq')
            rs = stmt.executeQuery("SELECT NEXTVAL('" + sequencia+ "')");
            while (rs.next()) {
                int auxCodigoGenerator = rs.getInt("GEN_ID");
                auxCodigo = auxCodigoGenerator + 1;
                
               
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de conexão! \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return auxCodigo;
    }

    public String getSequencia() {
        return sequencia;
    }

    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }
}
