package VisaoCadastros;

/**
 * @author:Gelvazio Camargo
 */
public class CadFornecedor extends pessoaPadrao {

    @Override
    public void setTitle(String title) {
        super.setTitle("Cadastro de Fornecedor");
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        this.getTitulo().setText("Cadastro de Fornecedor");
    } 
}
