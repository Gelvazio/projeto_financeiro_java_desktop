package Estrutura;

import javax.swing.JOptionPane;

/**
 *
 * @author Jessica
 */
public class Mensagem {
    
    public static void mensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
    }

    public static void mensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Sucesso!", JOptionPane.OK_OPTION);
    }
    
}
