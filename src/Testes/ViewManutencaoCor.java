package View;

import ControleCadastro.ControllerManutencaoPadrao;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ViewManutencaoCor extends metodoGlobalTelaPadrao {
   
    private JTextField codigo;
    
    //@Override
//    public void ajustaAlturaLarguraJanela(){        
//        this.setLarguraJanela(400);
//        this.setAlturaJanela(200);
//    }  

//    @Override
//    public String getTitle() {
//        return "Cadastro de Cores";
//    }
    
    @Override
    protected void adicionaCamposTela() {
        this.adicionaCampo("codigo", "Código","", 1, 10, 95);
        this.adicionaCampo("nome", "Descrição","", 2, 25, 95);
    }

    @Override
    protected void processaDados() {
        //super.processaDados(); //To change body of generated methods, choose Tools | Templates.
        //this.GravarAlterar();
        
        ControllerManutencaoPadrao controller = new ControllerManutencaoPadrao(this);
        controller.processaDados();
    }

    public JTextField getCodigo() {
        return codigo;
    }

    public void setCodigo(JTextField codigo) {
        this.codigo = codigo;
    }    
    
    public void GravarAlterar() {        
        //Method[] aMetodos = this.getClass().getDeclaredMethods();
        JPanel painelCampos = this.getPainelCampos();
        
        Component[] componentes = painelCampos.getComponents();
        
        for (int i = 0; i < componentes.length; i++){
            
            //JTextField campoTexto = componentes[i];
            
            
            System.out.println("Componente:" + componentes[i] +"\n");
        }
        
        
        ArrayList<String> camposTela = this.getCamposTela();
        
        JOptionPane.showMessageDialog(null, "Valor do campo código:" + this.getCodigo().getText() + "\n ");
//        for (String oCampo : camposTela) {
//            System.out.println("Campo:" + oCampo);            
//        }
        
        String teste = "11";
//        
//        
//        for (int i = 0; i < camposTela.size();i++){
//            String auxCampo = "_" + camposTela.get[i].toString();// = edtCodigo.getText();        
//        }
//        
        
//        String auxTexto = edtCodigo.getText();
//        String auxNome = edtDescricao.getText();
//        if (auxTexto.equals("")) {
//            JOptionPane.showMessageDialog(null, "Favor Preencher o Código da Cor!");
//            edtCodigo.grabFocus();
//        } else if (auxNome.equals("")) {
//            JOptionPane.showMessageDialog(null, "Favor Preencher o nome da Cor!");
//            edtDescricao.grabFocus();
//        } else {
//            int codigo = Integer.parseInt(auxTexto);
//            int auxcd_usuario = 1;
//            Cor cor = new Cor(
//                    codigo,
//                    auxNome,
//                    auxcd_usuario
//            );
//
//            if (cordb.getCor(codigo)) {
//                //Altera
//                if (cordb.alterarCor(cor)) {
//                    JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
//                    habilitaCampos(false);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Não foi possível alterar o registro!");
//                    edtCodigo.grabFocus();
//                }
//            } else {
//                if (cordb.inserirCor(cor)) {
//                    JOptionPane.showMessageDialog(null, "Registro incluído com sucesso!");
//                    habilitaCampos(false);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Não foi possível incluir o registro!");
//                    edtCodigo.grabFocus();
//                }
//            }
//        }
    }

}