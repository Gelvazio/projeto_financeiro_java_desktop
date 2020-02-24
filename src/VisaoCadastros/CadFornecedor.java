package VisaoCadastros;

import javax.swing.JOptionPane;

/**
 *
 * @author:Gelvazio Camargo
 */
public class CadFornecedor extends CadPessoa {

    @Override
    public void montaTitulo() {
        super.montaTitulo();
        JOptionPane.showMessageDialog(null,"Titulo:");
    }


}
