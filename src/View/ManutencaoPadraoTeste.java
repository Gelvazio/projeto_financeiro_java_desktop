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

import Estrutura.TelaPadrao;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jessica Moratelli
 */
public class ManutencaoPadraoTeste extends TelaPadrao {

    public ManutencaoPadraoTeste() {
        //TelaPadrao.setV
    }

    @Override
    public void pack() {
        super.pack(); 
    }
    
    

    protected void adicionaCampos444() {
        JTextField codigo = new JTextField("TESTE GELVAZIO");
        JPanel painelCampos = this.getPainelCampos();
        painelCampos.add(codigo, 25);

        JOptionPane.showMessageDialog(null, "dados de testes");
//        javax.swing.JTextField campoTexto = new javax.swing.JTextField("teste", 25);
//        campoTexto.setText("15");
//        this.getPainelCampos().add(campoTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

    }
//
    public static void main(String[] args) {
        TelaPadrao tela = new TelaPadrao();
        tela.montaTela();
    }
}
