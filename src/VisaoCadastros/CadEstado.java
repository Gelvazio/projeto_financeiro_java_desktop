package VisaoCadastros;

import ControleCadastro.EstadoDB;
import ModeloCadastro.Estado;
import VisaoConsultasCadastro.ConsultaEstado;
import Principal.Conexao;
import Principal.MetodosGlobais;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio
 */
public class CadEstado extends MetodosGlobais {

    private static final String sqlBuscaEstado = "SELECT * FROM estado  WHERE cd_estado=?";

    /**
     * Creates new form CadEstado
     */
    public CadEstado() {
        initComponents();
        Centro();
    }

    private void LimpaTela() {
        edtSigla.setText("");
        edtDescricao.setText("");
        edtIbge.setText("");
        edtSigla.grabFocus();
    }

    private void Excluir_Registro() {
        String auxTexto = edtSigla.getText().toUpperCase();
        if (auxTexto.equals("")) {
            JOptionPane.showConfirmDialog(null, "Não há registros a serem excluidos!");
        } else {

            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro?");
            if (resposta == JOptionPane.YES_OPTION) {
                EstadoDB estadodb = new EstadoDB();
                String auxCodigo = edtSigla.getText();
                if (estadodb.excluirEstado(auxCodigo)) {
                    JOptionPane.showMessageDialog(null, "Exclusão efetuada com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possivel excluir o registro!!");
                }
            }
            LimpaTela();
        }
    }

    private void Gravar_Registro() {
        String auxCd_estado = edtSigla.getText().toUpperCase();
        String auxDs_estado = edtDescricao.getText();
        int auxCd_ibge = Integer.parseInt(edtIbge.getText());
        //Sera implementado depois
        int auxcd_usuario = 1;
        int auxcd_filial = 1;
        EstadoDB estadodb = new EstadoDB();
        Estado estado = new Estado(
                auxCd_estado,
                auxDs_estado,
                auxCd_ibge,
                auxcd_filial,
                auxcd_usuario);
        if (estadodb.inserirEstado(estado)) {
            JOptionPane.showMessageDialog(null, "Registro incluído com sucesso!");
            LimpaTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível incluir o registro!");
            edtSigla.grabFocus();
        }
    }

    private void Alterar_Registro() {
        String auxCd_estado = edtSigla.getText().toUpperCase();
        String auxDs_estado = edtDescricao.getText();
        int auxCd_ibge = Integer.parseInt(edtIbge.getText());
        //Sera implementado depois
        int auxcd_usuario = 1;
        int auxcd_filial = 1;
        EstadoDB estadodb = new EstadoDB();
        Estado estado = new Estado(
                auxCd_estado,
                auxDs_estado,
                auxCd_ibge,
                auxcd_filial,
                auxcd_usuario);
        if (estadodb.alterarEstado(estado)) {
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o registro!");
        }
        LimpaTela();
    }

    private void ValidaCampoCodigoNãoNulo() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        EstadoDB estadodb = new EstadoDB();
        String cd_estado = edtSigla.getText().toUpperCase();

        if (estadodb.getEstado(cd_estado)) {
            try {
                conn = Conexao.getConexao();
                pstmt = conn.prepareStatement(sqlBuscaEstado);
                //E passado por parametro o codigo do usuario resultante do SQL de nome "cd_usuario"
                pstmt.setString(1, cd_estado);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String auxcd_estado = rs.getString("cd_estado");
                    String auxds_estado = rs.getString("ds_estado");
                    int auxcd_ibge = rs.getInt("cd_ibge");

                    String a;
                    a = "" + auxcd_ibge;
                    edtSigla.setText(auxcd_estado);
                    edtDescricao.setText(auxds_estado);
                    edtIbge.setText(a);
                    edtDescricao.grabFocus();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Estado Não Cadastrado!");
            edtDescricao.grabFocus();
        }
    }

    private void Gravar_Completo_Validado() {
        String auxTexto = edtSigla.getText();
        String auxDescricao = edtDescricao.getText();
        String auxedtIbge = edtIbge.getText();
        if (auxTexto.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher a Sigla do Estado!");
            edtSigla.grabFocus();
        } else if (auxDescricao.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o nome do Estado!");
            edtDescricao.grabFocus();
        } else if (auxedtIbge.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o codigo do IBGE!");
            edtIbge.grabFocus();
        } else {
            Gravar_Alterar_Registro();
        }
    }

    private void Gravar_Alterar_Registro() {
        String auxTexto = edtSigla.getText();
        EstadoDB estadodb = new EstadoDB();
        if (estadodb.getEstado(auxTexto)) {
            Alterar_Registro();
        } else {
            Gravar_Registro();
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

        jPanel3 = new javax.swing.JPanel();
        edtIbge = new javax.swing.JTextField();
        edtDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        edtSigla = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGravar = new javax.swing.JButton();

        setTitle("Cadastro de Estado");

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edtIbge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtIbgeKeyPressed(evt);
            }
        });
        jPanel3.add(edtIbge, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 370, 25));

        edtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDescricaoKeyPressed(evt);
            }
        });
        jPanel3.add(edtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 370, 25));

        jLabel2.setText("Descrição:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 70, -1));

        jLabel4.setText("Código IBGE:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 70, -1));

        jLabel5.setText("Estado/Sigla:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 70, -1));

        edtSigla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtSiglaKeyPressed(evt);
            }
        });
        jPanel3.add(edtSigla, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 370, 25));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel6.setText("Cadastro de Estado");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 0, -1, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Red.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel3.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, 120, 40));

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
        jPanel3.add(btnConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 120, 40));

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
        jPanel3.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 120, 40));

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
        jPanel3.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 120, 40));

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
        jPanel3.add(btnGravar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 120, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 645, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        setBounds(0, 0, 671, 350);
    }// </editor-fold>//GEN-END:initComponents

    private void edtSiglaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtSiglaKeyPressed
        // TODO add your handling code here:
        String auxTexto = edtSigla.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxTexto.equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor digite um Valor no campo!");
                edtSigla.grabFocus();
            } else {
                ValidaCampoCodigoNãoNulo();
            }
        }
    }//GEN-LAST:event_edtSiglaKeyPressed

    private void edtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDescricaoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtIbge.grabFocus();
        }
    }//GEN-LAST:event_edtDescricaoKeyPressed

    private void edtIbgeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtIbgeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGravar.grabFocus();
        }
    }//GEN-LAST:event_edtIbgeKeyPressed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        ConsultaEstado form = new ConsultaEstado(edtSigla);
        this.getDesktopPane().add(form);
        form.setVisible(true);
        edtSigla.grabFocus();
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnConsultaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsultaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSair.grabFocus();
        }
    }//GEN-LAST:event_btnConsultaKeyPressed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        Excluir_Registro();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExcluirKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConsulta.grabFocus();
        }
    }//GEN-LAST:event_btnExcluirKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente Cancelar a Edição?");
        if (resposta == JOptionPane.YES_OPTION) {
            LimpaTela();
            edtSigla.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnExcluir.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        // TODO add your handling code here:
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente gravar o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
            Gravar_Completo_Validado();
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnGravarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGravarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCancelar.grabFocus();
        }
    }//GEN-LAST:event_btnGravarKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadEstado().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnSair;
    private javax.swing.JTextField edtDescricao;
    private javax.swing.JTextField edtIbge;
    private javax.swing.JTextField edtSigla;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
