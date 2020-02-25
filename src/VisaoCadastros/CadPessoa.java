package VisaoCadastros;

/**
 *
 * @author Gelvazio
 */
public class CadPessoa extends pessoaPadrao {
	
    @Override
    public void setTitle(String title) {
        super.setTitle("Cadastro de Pessoa");
    }

    @Override
    protected void initComponents() {
        super.initComponents();
        this.getTitulo().setText("Cadastro de Pessoa");
    }
}