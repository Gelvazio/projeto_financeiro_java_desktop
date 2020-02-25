package VisaoCadastros;

/**
 * @author:Gelvazio Camargo
 */
public class CadTransportadores extends pessoaPadrao {

    @Override
    public void setTitle(String title) {
        super.setTitle("Cadastro de Transportadoras");
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        this.getTitulo().setText("Cadastro de Transportadoras");
    } 
}
