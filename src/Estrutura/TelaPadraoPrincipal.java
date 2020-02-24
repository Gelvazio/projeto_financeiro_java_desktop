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
package Estrutura;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Jessica Moratelli
 */
//public class TelaPadraoPrincipal extends javax.swing.JInternalFrame {
public class TelaPadraoPrincipal extends javax.swing.JFrame {

    private int alturaJanela;
    private int larguraJanela;
    private javax.swing.JPanel painelCampos;
    private javax.swing.JPanel painelBotoesRodape;
    private javax.swing.JButton botaoConfirmar;
    private javax.swing.JButton botaoFechar;
    private ArrayList camposTela;

    /**
     * Creates new form TelaPadraoPrincipal
     */
    public TelaPadraoPrincipal() {
        //initComponents();
        this.iniciaComponente();
    }

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
        if (painelCampos == null) {
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
        if (painelBotoesRodape == null) {
            painelBotoesRodape = new javax.swing.JPanel();
            painelBotoesRodape.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
            painelBotoesRodape.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        }
        return painelBotoesRodape;
    }

    public void setPainelBotoesRodape(JPanel painelBotoesRodape) {
        this.painelBotoesRodape = painelBotoesRodape;
    }

    public void iniciaComponente() {
        this.ajustaAlturaLarguraJanela();
        this.criaTela();
        this.montaTela();
        this.centralizaTela();
    }

    public void ajustaAlturaLarguraJanela() {
        this.setLarguraJanela(800);
        this.setAlturaJanela(200);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension oDimensao = new Dimension();
        oDimensao.setSize(getLarguraJanela(), getAlturaJanela());
        return oDimensao;
    }

    protected void criaTela() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        adicionaBotaoConfirmar();
        adicionaBotaoFechar("Fechar");
        adicionaPainelBotoes();
        adicionaCamposTela();
        adicionaPainelCampos();
    }

    protected void adicionaCamposTela() {
        
    }
    
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
        this.getPainelCampos().add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, (afastamentoSuperior * linha), -1, -1));

        javax.swing.JTextField campoTexto = new javax.swing.JTextField(nome, tamanhoCampoTexto);
        campoTexto.setText(valorPadrao);
        this.getPainelCampos().add(campoTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(afastamentoEsquerdoCampo, (afastamentoSuperior * linha) - 5, -1, -1));
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

    protected void adicionaBotaoFechar(String nome) {
        botaoFechar = new javax.swing.JButton();
        botaoFechar.setText(nome);
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

    private void botaoConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        this.processaDados();
    }

    protected void processaDados() {
//        ControllerManutencaoPadrao controller = new ControllerManutencaoPadrao(this);
//        controller.processaDadosInclusao(this);
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
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(TelaPadraoPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPadraoPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPadraoPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPadraoPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPadraoPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
