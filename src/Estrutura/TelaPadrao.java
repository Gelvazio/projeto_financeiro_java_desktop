package Estrutura;

import ControleCadastro.ControllerManutencaoPadrao;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class TelaPadrao extends JFrame {
//public class TelaPadrao extends JInternalFrame {

    private int alturaJanela;
    private int larguraJanela;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JPanel painelBotoesRodape;
    private javax.swing.JButton botaoConfirmar;
    private javax.swing.JButton botaoFechar;
    private ArrayList camposTela;

    public ArrayList getCamposTela() {
        return camposTela;
    }

    public void setCamposTela(ArrayList camposTela) {
        this.camposTela = camposTela;
    }
    
    public int getAlturaJanela() {
        return alturaJanela;
    }

    public void setAlturaJanela(int alturaJanela) {
        this.alturaJanela = alturaJanela;
    }

    public int getLarguraJanela() {
        return larguraJanela;
    }

    public void setLarguraJanela(int larguraJanela) {
        this.larguraJanela = larguraJanela;
    }        
    
    public JPanel getPainelCampos() {
        if(painelCampos == null){
            painelCampos = new javax.swing.JPanel();
            painelCampos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            painelCampos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());       
        }
        return painelCampos;
    }

    public void setPainelCampos(JPanel painelCampos) {
        this.painelCampos = painelCampos;
    }

    public JPanel getPainelBotoesRodape() {
        if(painelBotoesRodape == null){
            painelBotoesRodape = new javax.swing.JPanel();
            painelBotoesRodape.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            painelBotoesRodape.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        }
        return painelBotoesRodape;
    }

    public void setPainelBotoesRodape(JPanel painelBotoesRodape) {
        this.painelBotoesRodape = painelBotoesRodape;
    }
    
    public TelaPadrao() {
        this.iniciaComponente();
    }

    public void iniciaComponente() {
        this.ajustaAlturaLarguraJanela();
        this.criaTela();
        this.montaTela();
        this.centralizaTela();
    }
    
    public void ajustaAlturaLarguraJanela(){
        this.setLarguraJanela(800);        
        this.setAlturaJanela(600);
    }
    
    protected void criaTela() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        adicionaBotaoConfirmar();
        adicionaBotaoFechar();
        adicionaPainelBotoes();
        adicionaCamposTela();
        adicionaPainelCampos();        
    }
    
    protected void adicionaCamposTela(){};

    
    /**
     * Adiciona um novo campo form na tela
     * @param nome
     * @param titulo
     * @param valorPadrao
     * @param linha
     * @param tamanhoCampoTexto
     * @param afastamentoEsquerdoCampo 
     */
    protected void adicionaCampo(String nome, String titulo, String valorPadrao, int linha, int tamanhoCampoTexto, int afastamentoEsquerdoCampo) {    
        javax.swing.JLabel label = new javax.swing.JLabel();
        label.setFont(new java.awt.Font("Tahoma", 1, 12));
        label.setText(titulo + ":");
        label.setName(nome);
        
        int afastamentoSuperior = 35;        
        this.getPainelCampos().add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, (afastamentoSuperior * linha),-1,-1));

        javax.swing.JTextField campoTexto = new javax.swing.JTextField(nome, tamanhoCampoTexto);        
        campoTexto.setText(valorPadrao);
        this.getPainelCampos().add(campoTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(afastamentoEsquerdoCampo, (afastamentoSuperior * linha) - 5, -1, -1));
        
//        String auxNome = "" + nome;
//        this.camposTela.add("teste");
    }

    protected void adicionaPainelCampos() {
        int altura = this.getAlturaJanela() - 75;
        int largura = this.getLarguraJanela();
        getContentPane().add(getPainelCampos(), new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, largura, altura));        
    }

    protected void adicionaPainelBotoes() {
        int largura = this.getLarguraJanela();
        int altura = this.getAlturaJanela() - 74;
        getContentPane().add(this.getPainelBotoesRodape(), new org.netbeans.lib.awtextra.AbsoluteConstraints(0, altura, largura, 40));
    }
    
    protected void adicionaBotaoConfirmar() {
        botaoConfirmar = new javax.swing.JButton();
        botaoConfirmar.setText("Confirmar");
        botaoConfirmar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmarActionPerformed(evt);
            }
        });
        this.getPainelBotoesRodape().add(botaoConfirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 95, 25));
    }

    protected void adicionaBotaoFechar() {
        botaoFechar = new javax.swing.JButton();
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });
        this.getPainelBotoesRodape().add(botaoFechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 25));
    }

    protected void centralizaTela() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        // variavel largura com a largura da tela
        int screenWidth = screenSize.width - this.getWidth();
        // variavel largura com a altura da tela
        int screenHeigth = screenSize.height - this.getHeight();
        //Divide o tamanho da tela para ficar no centro
        setLocation(screenWidth / 2, screenHeigth / 2);                
    }

    public void montaTela() {
        pack();
    }
    
    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {                                            
        dispose();
    }	
    
    @Override
    public Dimension getPreferredSize() {
        Dimension oDimensao = new Dimension();
        oDimensao.setSize(getLarguraJanela(), getAlturaJanela());
        return oDimensao;
    }
    	
    private void botaoConfirmarActionPerformed(java.awt.event.ActionEvent evt) {                                               
//        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente gravar o registro?");
//        if (resposta == JOptionPane.YES_OPTION) {
//        }
            this.processaDados();
    }
    
    protected void processaDados(){
        ControllerManutencaoPadrao controller = new ControllerManutencaoPadrao(this);
        controller.processaDadosInclusao(this);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPadrao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPadrao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPadrao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPadrao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                new TelaPadrao().setVisible(true);                
            }
        });
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("framePrincipal"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
