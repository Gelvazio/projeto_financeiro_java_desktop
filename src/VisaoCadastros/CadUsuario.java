package VisaoCadastros;

import ControleCadastro.UsuarioDB;
import ModeloCadastro.Usuario;
import VisaoConsultasCadastro.ConsultaUsuario;
import Principal.Conexao;
import Principal.MetodosGlobais;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio Camargo
 */
public class CadUsuario extends MetodosGlobais {

    private static final String sqlBuscaUsuario
            = "SELECT * FROM usuario WHERE cd_usuario=?";

    public CadUsuario() {
        initComponents();
        Centro();
        edtCodigo.grabFocus();
    }

    private void LimpaTela() {
        edtCodigo.setText("");
        edtLogin.setText("");
        edtSenha_Um.setText("");
        edtFilial.setText("");
        edtCodigo.grabFocus();
    }

    private void Excluir_Registro() {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
            UsuarioDB usuariodb = new UsuarioDB();
            int auxCodigo = Integer.parseInt(edtCodigo.getText());
            if (usuariodb.excluirUsuario(auxCodigo)) {
                JOptionPane.showMessageDialog(null, "Exclusão efetuada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o registro!!");
            }
        }
        LimpaTela();
    }

    private void Gravar_Registro() {
        // TODO add your handling code here:
        String auxTexto = edtCodigo.getText();
        String auxLogin = edtLogin.getText().toUpperCase();
        //Teste de Conversao de Tipos de Variaveis
        int auxSenhaUmConversao = Integer.parseInt(edtSenha_Um.getText());
        String a = "0" + auxSenhaUmConversao;
        String auxSenha_Dois = edtFilial.getText();
        int auxCd_Filial = 1;
        UsuarioDB usuariodb = new UsuarioDB();
        int auxCodigo = Integer.parseInt(auxTexto);
        Usuario usuario = new Usuario(auxLogin, auxSenha_Dois, auxCd_Filial, auxCodigo);
        if (usuariodb.inserirUsuario(usuario)) {
            JOptionPane.showMessageDialog(null, "Registro incluído com sucesso!");
            LimpaTela();
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível incluir o registro!");
            edtCodigo.grabFocus();
        }
    }

    private void Alterar_Registro() {
        String auxCodigo = edtCodigo.getText();
        String auxLogin = edtLogin.getText();
        String auxSenha = edtSenha_Um.getText();
        String auxFilial = edtFilial.getText();

        int cd_filial = Integer.parseInt(auxFilial);
        int Codigo = Integer.parseInt(auxCodigo);
        
        UsuarioDB usuariodb = new UsuarioDB();

        Usuario usuario = new Usuario(auxLogin, auxSenha, cd_filial, Codigo);
        if (usuariodb.alterarUsuario(usuario)) {
            JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Não foi possível alterar o registro!");
        }
        LimpaTela();
    }

    private void ValidaCodigoGenerator() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            //Pega o Valor do Generator
            rs = stmt.executeQuery("SELECT GEN_ID(GEN_CD_USUARIO, 1) FROM RDB$DATABASE");
            while (rs.next()) {
                int auxCodigoGenerator = rs.getInt("GEN_ID");
                int auxCodigo = auxCodigoGenerator + 1;
                String a;
                a = "" + auxCodigo;
                edtCodigo.setText(a);
                edtLogin.grabFocus();
                //Altera o valor do Generator se o SQL passado acima fosse""SELECT GEN_ID(GEN_CD_USUARIO, 0) FROM RDB$DATABASE""
                //rs = stmt.executeQuery("ALTER SEQUENCE GEN_CD_USUARIO RESTART WITH " + auxCodigo);
            }
        } catch (SQLException erro) {
            System.out.println("Erro de conexão! " + erro);
        } finally {
            Conexao.closeAll(conn);
        }
    }

    private void ValidaCampoCodigoNãoNulo() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsuarioDB usuariodb = new UsuarioDB();
        int cd_usuario = Integer.parseInt(edtCodigo.getText());

        if (usuariodb.getUsuario(cd_usuario)) {
            try {
                conn = Conexao.getConexao();
                pstmt = conn.prepareStatement(sqlBuscaUsuario);
                //E passado por parametro o codigo do usuario resultante do SQL de nome "cd_usuario"
                pstmt.setInt(1, cd_usuario);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String auxUsuario = rs.getString("ds_usuario");
                    String auxLogin = rs.getString("ds_login");
                    //edtUsuario.setText(ds_usuario);
                    String auxSenha = rs.getString("ds_senha");
                    String auxFilial = rs.getString("cd_filial");
                    int a = Integer.parseInt(auxFilial);

                    //Precisa Usar ||  para todos os casos possiveis e eliminar o campo nome do usuario;
                    edtNome.setText(auxUsuario);
                    edtLogin.setText(auxLogin);
                    edtSenha_Um.setText(auxSenha);
                    edtFilial.setText(auxFilial);
                    edtLogin.grabFocus();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario Não Cadastrado!");
            edtLogin.grabFocus();
        }
    }

    private void Gravar_Completo_Validado() {
        String auxTexto = edtCodigo.getText();
        String auxLogin = edtLogin.getText();
        String auxSenhaUm = edtSenha_Um.getText();
        String auxSenhaDois = edtSenha_Um.getText();
        if (auxTexto.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o Código do Usuário!");
            edtCodigo.grabFocus();
        } else if (auxLogin.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o Login do Usuário!");
            edtLogin.grabFocus();
        } else if (auxSenhaUm.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher a Senha do Usuário!");
            edtSenha_Um.grabFocus();
        } else if (auxSenhaDois.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher a Senha do Usuário!");
            edtFilial.grabFocus();
        } else {
            Gravar_Alterar_Registro();
        }
    }

    private void Gravar_Alterar_Registro() {
        //deve ser feita toda a validacao de banco de dados nesta parte
        String auxTexto = edtCodigo.getText();
        int codigo = Integer.parseInt(auxTexto);
        UsuarioDB usuariodb = new UsuarioDB();
        //int cd_usuario = Integer.parseInt(edtCodigo.getText());
        if (usuariodb.getUsuario(codigo)) {
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

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        edtSenha_Um = new javax.swing.JTextField();
        edtFilial = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        edtLogin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edtSenha_Dois = new javax.swing.JTextField();
        edtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Cadastro de Usuários");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 245, 39));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edtSenha_Um.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtSenha_UmKeyPressed(evt);
            }
        });
        jPanel1.add(edtSenha_Um, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 410, 30));

        edtFilial.setText("Precisa Fazer!!!");
        edtFilial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtFilialKeyPressed(evt);
            }
        });
        jPanel1.add(edtFilial, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 410, 30));

        jLabel10.setText("Grupo de Usuário:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 90, -1));

        jLabel8.setText("Senha:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 40, -1));

        edtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtLoginKeyPressed(evt);
            }
        });
        jPanel1.add(edtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 410, 30));

        jLabel2.setText("Login:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 40, -1));

        jLabel11.setText("Confirmar Senha:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 90, -1));

        jLabel7.setText("Código:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 50, -1));

        edtSenha_Dois.setText("Precisa Fazer!!!");
        edtSenha_Dois.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtSenha_DoisKeyPressed(evt);
            }
        });
        jPanel1.add(edtSenha_Dois, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 410, 30));

        edtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigoKeyPressed(evt);
            }
        });
        jPanel1.add(edtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 80, 30));

        jLabel3.setText("Nome:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 40, -1));

        edtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtNomeKeyPressed(evt);
            }
        });
        jPanel1.add(edtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 410, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, 540, 320));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel2.add(btnGravar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

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
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 120, 40));

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
        jPanel2.add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, 40));

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
        jPanel2.add(btnConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 120, 40));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Red.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel2.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 120, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 140, 260));

        setBounds(0, 0, 737, 434);
    }// </editor-fold>//GEN-END:initComponents

    private void edtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoKeyPressed
        // Nesta parte o sistema faz 2 validações
        // 1-valida se o campo Codigo nao esta nulo ,se estiver nulo
        // ele chama uma funcao para pegar o ultimo codigo da tabela
        // Neste caso seria a funcao do generator que deve ser implementada pra frente
        // 2 Validação: Verifica se existe o codigo do campo Codigo na tabela usuario
        // Se o registro existe ele carrega os dados na tela
        // Senao existe ele da a msg de Usuario Nao cadastrado!
        String auxTexto = edtCodigo.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxTexto.equals("")) {
                ValidaCodigoGenerator();
                edtLogin.grabFocus();
            } else {
                ValidaCampoCodigoNãoNulo();
            }
        }
    }//GEN-LAST:event_edtCodigoKeyPressed

    private void edtLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtSenha_Um.grabFocus();
        }
    }//GEN-LAST:event_edtLoginKeyPressed

    private void edtSenha_UmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtSenha_UmKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtFilial.grabFocus();
        }
    }//GEN-LAST:event_edtSenha_UmKeyPressed

    private void edtFilialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtFilialKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGravar.grabFocus();
        }
    }//GEN-LAST:event_edtFilialKeyPressed

    private void edtSenha_DoisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtSenha_DoisKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtSenha_DoisKeyPressed

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
        Excluir_Registro();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExcluirKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConsulta.grabFocus();
        }
    }//GEN-LAST:event_btnExcluirKeyPressed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        ConsultaUsuario form = new ConsultaUsuario(edtCodigo);
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

    private void edtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtNomeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomeKeyPressed

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
            java.util.logging.Logger.getLogger(CadUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CadUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CadUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CadUsuario.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadUsuario().setVisible(true);
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
    private javax.swing.JTextField edtFilial;
    private javax.swing.JTextField edtLogin;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtSenha_Dois;
    private javax.swing.JTextField edtSenha_Um;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
