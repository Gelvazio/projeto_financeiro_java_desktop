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
package ControleCadastro;

import Estrutura.TelaPadrao;
import View.ViewManutencaoCor;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Jessica Moratelli
 */
public class Controller {

    protected TelaPadrao Tela;

    public JFrame getTela() {
        return Tela;
    }

    public void setTela(TelaPadrao Tela) {
        this.Tela = Tela;
    }
    
    public Controller(TelaPadrao oTela) {
        this.setTela(oTela);
    }

    protected void setBean(TelaPadrao oTela) {
        //this.getTela();        
        //ViewManutencaoCor telaCor = this.getTela();
//        JPanel aPainelCampos = telaCor.getPainelCampos();
//        Component Componente [] = aPainelCampos.getComponents();
//        
//        int i = 0;
//        
//        ModelCadastro.Cor cor = new ModelCadastro.Cor();
//        
//        for(Component comp : Componente) {
//            Component oComponente = Componente[i]; 
//            if(oComponente instanceof JTextField){
//                
//                String sNomeMetodo = oComponente.getName();
//                //String sValorNomeMetodo = comp.getText();
//                //String Valor = cor.sNomeMetodo();
//                
//            }
//            System.out.println("componente da posicao:" + i + Componente);
//            i++;
////            comp.parent = this;
////            adjustListeningChildren(AWTEvent.HIERARCHY_EVENT_MASK,
////                                    comp.numListening(AWTEvent.HIERARCHY_EVENT_MASK));
////            adjustListeningChildren(AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK,
////                                    comp.numListening(AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK));
////            adjustDescendants(comp.countHierarchyMembers());
       // }
    }
}
