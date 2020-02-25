package VisaoCadastros;

/**
 *
 * @author Gelvazio
 */
public class CadVendedores extends pessoaPadrao {

    @Override
    public void setTitle(String title) {
        super.setTitle("Cadastro de Vendedores");
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        this.getTitulo().setText("Cadastro de Vendedores");
    }
}