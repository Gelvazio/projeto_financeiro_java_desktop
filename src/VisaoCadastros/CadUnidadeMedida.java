package VisaoCadastros;

import ControleCadastro.UnidadeMedidaDB;
import ModeloCadastro.UnidadeMedida;
import Principal.Conexao;
import Principal.MetodosGlobais;
import VisaoConsultasCadastro.ConsultaUnidadeMedida;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio Camargo
 */
public class CadUnidadeMedida extends MetodosGlobais {

    private static final String sqlBuscaUnidadeMedida = "SELECT * FROM UNIDADE_MEDIDA WHERE cd_unidade=?";

    /**
     * Creates new form CadUnidade_Medida
     */
    public CadUnidadeMedida() {
        initComponents();
        Centro();
    }

    private void LimpaTela() {
        edtDescricao.setText("");
        edtSigla.setText("");
        //edtFator.setText("");
        edtCodigo.grabFocus();
    }

    private void ExcluirRegistro() {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
            UnidadeMedidaDB unidademedidadb = new UnidadeMedidaDB();
            int auxCodigo = Integer.parseInt(edtCodigo.getText());
            if (unidademedidadb.excluirUnidadeMedida(auxCodigo)) {
                JOptionPane.showMessageDialog(null, "Exclusão efetuada com sucesso!");
                LimpaTela();
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o registro!!");
            }
        }
    }

    private void GravarRegistro() {
        String auxTexto = edtCodigo.getText();
        String auxds_unidade = edtDescricao.getText();
        String auxds_sigla = edtSigla.getText();
        int auxcd_unidade = Integer.parseInt(auxTexto);

        int auxcd_usuario = 1;
        int auxcd_filial = 1;
        UnidadeMedidaDB unidademedidadb = new UnidadeMedidaDB();
        UnidadeMedida unidademedida = new UnidadeMedida(
                auxcd_unidade,
                auxds_sigla,
                auxds_unidade,
                auxcd_filial,
                auxcd_usuario);
        if (unidademedidadb.inserirUnidadeMedida(unidademedida)) {
            JOptionPane.showMessageDialog(null, "Registro incluído com sucesso!");
            LimpaTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível incluir o registro!");
            edtCodigo.grabFocus();
        }
    }

    private void AlterarRegistro() {
        String auxTexto = edtCodigo.getText();
        String auxds_unidade = edtDescricao.getText();
        String auxds_sigla = edtSigla.getText();
        int auxcd_unidade = Integer.parseInt(auxTexto);
        int auxcd_usuario = 1;
        int auxcd_filial = 1;
        UnidadeMedidaDB unidademedidadb = new UnidadeMedidaDB();
        UnidadeMedida unidademedida = new UnidadeMedida(
                auxcd_unidade,
                auxds_sigla,
                auxds_unidade,
                auxcd_filial,
                auxcd_usuario);
        if (unidademedidadb.alterarUnidadeMedida(unidademedida)) {
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o registro!");
        }
        LimpaTela();
    }

    private void ValidaCampoCodigoNãoNulo() {
        String auxTexto = edtCodigo.getText();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UnidadeMedidaDB unidademedidadb = new UnidadeMedidaDB();
        int cd_unidade = Integer.parseInt(auxTexto);
        if (unidademedidadb.getUnidadeMedida(cd_unidade)) {
            try {
                conn = Conexao.getConexao();
                pstmt = conn.prepareStatement(sqlBuscaUnidadeMedida);
                pstmt.setInt(1, cd_unidade);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String auxds_sigla = rs.getString("ds_sigla");
                    String auxds_unidade = rs.getString("ds_unidade");
                    edtDescricao.setText(auxds_unidade);
                    edtSigla.setText(auxds_sigla);
                    //edtFator.setText(tx_fator);
                    edtDescricao.grabFocus();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Unidade Medida Não Cadastrada!");
            edtDescricao.setText("");
            edtSigla.setText("");
            //edtFator.setText("");
            edtDescricao.grabFocus();
        }
    }

    private void GravarCompletoValidado() {
        String auxTexto = edtCodigo.getText();
        String auxds_unidade = edtDescricao.getText();
        String auxds_sigla = edtSigla.getText();
        int auxtx_fator = 1;// Integer.parseInt(edtFator.getText());
        if (auxTexto.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o Código da Unidade de Medida!");
            edtCodigo.grabFocus();
        } else if (auxds_unidade.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o nome da Unidade de Medida!");
            edtDescricao.grabFocus();
        } else if (auxds_sigla.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher a sigla da Unidade de Medida!");
            edtSigla.grabFocus();
        } else if (auxtx_fator < 0) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o fator da Unidade de Medida!!");
            //edtFator.grabFocus();
        } else {
            GravarAlterarRegistro();
        }
    }

    private void GravarAlterarRegistro() {
        UnidadeMedidaDB unidademedidadb = new UnidadeMedidaDB();
        int auxCodigo = Integer.parseInt(edtCodigo.getText());
        if (unidademedidadb.getUnidadeMedida(auxCodigo)) {
            AlterarRegistro();
        } else {
            GravarRegistro();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edtCodigo = new javax.swing.JTextField();
        edtDescricao = new javax.swing.JTextField();
        edtSigla = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Unidade de Medida");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, -1, 35));

        jLabel2.setText("Descrição:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel3.setText("Sigla:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        jLabel5.setText("Código:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        edtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigoKeyPressed(evt);
            }
        });
        getContentPane().add(edtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 390, 25));

        edtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDescricaoKeyPressed(evt);
            }
        });
        getContentPane().add(edtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 390, 25));

        edtSigla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtSiglaKeyPressed(evt);
            }
        });
        getContentPane().add(edtSigla, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 390, 25));

        btnGravar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Valid Green.png"))); // NOI18N
        btnGravar.setText("Gravar");
        btnGravar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarActionPerformed(evt);
            }
        });
        btnGravar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGravarKeyPressed(evt);
            }
        });
        getContentPane().add(btnGravar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 120, 40));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 120, 40));

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Remove Red.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        btnExcluir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnExcluirKeyPressed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 120, 40));

        btnConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Search.png"))); // NOI18N
        btnConsulta.setText("Consulta");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });
        btnConsulta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnConsultaKeyPressed(evt);
            }
        });
        getContentPane().add(btnConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 200, 120, 40));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Red.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 250, 120, 40));

        setBounds(0, 0, 703, 350);
    }// </editor-fold>//GEN-END:initComponents

    private void edtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoKeyPressed
        // TODO add your handling code here:
        String auxTexto = edtCodigo.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxTexto.equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um codigo valido!");
            } else {
                ValidaCampoCodigoNãoNulo();
            }
        }
    }//GEN-LAST:event_edtCodigoKeyPressed

    private void edtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDescricaoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtSigla.grabFocus();
        }
    }//GEN-LAST:event_edtDescricaoKeyPressed

    private void edtSiglaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtSiglaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //edtFator.grabFocus();
        }
    }//GEN-LAST:event_edtSiglaKeyPressed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        // TODO add your handling code here:
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente gravar o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
            GravarCompletoValidado();
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnGravarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGravarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCancelar.grabFocus();
        }
    }//GEN-LAST:event_btnGravarKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente Cancelar a Edição?");
        if (resposta == JOptionPane.YES_OPTION) {
            //LimpaTela();
            edtCodigo.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnExcluir.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        ExcluirRegistro();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExcluirKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConsulta.grabFocus();
        }
    }//GEN-LAST:event_btnExcluirKeyPressed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        ConsultaUnidadeMedida form = new ConsultaUnidadeMedida(edtCodigo);
        this.getDesktopPane().add(form);
        form.setVisible(true);
        edtCodigo.grabFocus();
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnConsultaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsultaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSair.grabFocus();
        }
    }//GEN-LAST:event_btnConsultaKeyPressed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

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
            java.util.logging.Logger.getLogger(CadUnidadeMedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadUnidadeMedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadUnidadeMedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadUnidadeMedida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadUnidadeMedida().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnSair;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtDescricao;
    private javax.swing.JTextField edtSigla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
