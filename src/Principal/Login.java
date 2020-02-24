package Principal;

import ControleCadastro.UsuarioDB;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio Camargo
 *
 */
public class Login extends JFrame {

    private static final String SQL_BUSCA_USUARIO = "SELECT * FROM usuario WHERE ds_login = ?";

    public static void iniciaLogin() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    public Login() {
        initComponents();
        edtLogin.grabFocus();
        //Coloca a tela no Centro
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        // variavel largura com a largura da tela
        int screenWidth = screenSize.width - this.getWidth();
        // variavel largura com a altura da tela
        int screenHeigth = screenSize.height - this.getHeight();
        //Divide o tamanho da tela para ficar no centro
        setLocation(screenWidth / 2, screenHeigth / 2);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Principal = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtFilial = new javax.swing.JTextField();
        edtSenha = new javax.swing.JTextField();
        btnSair = new javax.swing.JButton();
        btnEntrar = new javax.swing.JButton();
        edtLogin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.MOVE_CURSOR));

        Panel_Principal.setBackground(new java.awt.Color(51, 51, 255));
        Panel_Principal.setForeground(new java.awt.Color(153, 255, 153));
        Panel_Principal.setName(""); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Login:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        jLabel3.setText("Senha:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        jLabel4.setText("Filial:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        edtFilial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtFilialKeyPressed(evt);
            }
        });
        jPanel1.add(edtFilial, new org.netbeans.lib.awtextra.AbsoluteConstraints(89, 101, 99, -1));

        edtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtSenhaKeyPressed(evt);
            }
        });
        jPanel1.add(edtSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 99, -1));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Red.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        jPanel1.add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(134, 139, 105, -1));

        btnEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Valid Green.png"))); // NOI18N
        btnEntrar.setText("Entrar");
        btnEntrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEntrarMouseClicked(evt);
            }
        });
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        btnEntrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnEntrarKeyPressed(evt);
            }
        });
        jPanel1.add(btnEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        edtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtLoginKeyPressed(evt);
            }
        });
        jPanel1.add(edtLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 99, -1));

        Panel_Principal.add(jPanel1);
        jPanel1.setBounds(80, 100, 250, 210);

        jLabel1.setBackground(new java.awt.Color(204, 51, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DTS SYSTEMS");
        Panel_Principal.add(jLabel1);
        jLabel1.setBounds(80, 30, 260, 32);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Panel_Principal, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean validaSenhaUsuario() {
        boolean valida = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UsuarioDB usuariodb = new UsuarioDB();
        String auxLoginTela = edtLogin.getText();
        if (usuariodb.getLogin(auxLoginTela)) {
            try {
                conn = ConexaoFirebird.getConexao();
                pstmt = conn.prepareStatement(SQL_BUSCA_USUARIO);
                pstmt.setString(1, auxLoginTela);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String auxLogin = rs.getString("ds_login");
                    String auxSenha = rs.getString("ds_senha");
                    int auxfilial = rs.getInt("cd_filial");
                    //Conversao de Variaveis
                    String filial = "" + auxfilial;
                    //Verificação dos dados
                    String loginTela = edtLogin.getText();
                    String senhaTela = edtSenha.getText();
                    String filialTela = edtFilial.getText();

                    //Primeiro Verifica se o login e senha é igual
                    if (loginTela.equals(auxLogin)) {
                            //Login Confere

                        //Inicia a verificação da senha
                        if (senhaTela.equals(auxSenha)) {
                                //Senha Confere

                            //Inicia a verificação da filial
                            if (filialTela.equals(filial)) {
                                //Conferiu tudo,então altera pra verdadeiro o retorno desta funcao
                                valida = true;

                                //Passar como parametro o codigo do usuario e a filial
                                //para o base
                            } else {
                                JOptionPane.showMessageDialog(null, "Filial não confere!");
                                edtFilial.requestFocus();
                                valida = false;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Senha não confere!");
                            edtSenha.requestFocus();
                            valida = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Login do Usuario Não Cadastrado!");
                        valida = false;
                    }

                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão! " + erro);
                valida = false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario Não Cadastrado!");
            edtLogin.grabFocus();
            valida = false;
        }
        return valida;
    }

    public String getFilial() {
        String filial = edtFilial.getText();
        return filial;
    }

    public int getUsuario() {
        int usuario = 0;
        String filial = edtFilial.getText();
        return usuario;
    }

    private void ValidaDadosUsuarioTela() {
        ChamaTelaPrincipal();
//        String auxSenha = edtSenha.getText();
//        String auxFilial = edtFilial.getText();
//        String ds_usuario = edtLogin.getText();
//        if (ds_usuario.equals("")) {
//            JOptionPane.showMessageDialog(null, "Favor Preencher o Login do Usuário!");
//            edtLogin.grabFocus();
//        } else if (auxSenha.equals("")) {
//            JOptionPane.showMessageDialog(null, "Favor Preencher a Senha do Usuário!");
//            edtSenha.grabFocus();
//        } else if (auxFilial.equals("")) {
//            JOptionPane.showMessageDialog(null, "Favor Preencher a Filial do Usuário!");
//            edtFilial.grabFocus();
//        } else {
//            if (validaSenhaUsuario()) {
//                ChamaTelaPrincipal();
//            }
//        }
    }

    private void ChamaTelaPrincipal() {
        FormularioPrincipal formulario = new FormularioPrincipal();
        formulario.setResizable(true);
        formulario.entrasistema();
        formulario.setVisible(true);
        dispose();
    }

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        ValidaDadosUsuarioTela();
    }//GEN-LAST:event_btnEntrarActionPerformed
    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void edtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtSenhaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String auxSenha = edtSenha.getText();
            if (auxSenha.equals("")) {
                JOptionPane.showMessageDialog(null, "Favor Preencher a Senha do Usuário!");
                edtSenha.grabFocus();
            } else {
                edtFilial.grabFocus();
            }
        }
    }//GEN-LAST:event_edtSenhaKeyPressed

    private void edtFilialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtFilialKeyPressed
        // TODO add your handling code here:
        String auxFilial = edtFilial.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxFilial.equals("")) {
                JOptionPane.showMessageDialog(null, "Favor Preencher a Filial do Usuário!");
                edtFilial.grabFocus();
            } else {
                btnEntrar.grabFocus();
            }
        }
    }//GEN-LAST:event_edtFilialKeyPressed

    private void btnEntrarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEntrarKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ValidaDadosUsuarioTela();
        }
    }//GEN-LAST:event_btnEntrarKeyPressed

    private void edtLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtLoginKeyPressed
        // TODO add your handling code here:
        String auxNomeUsuario = edtLogin.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxNomeUsuario.equals("")) {
                JOptionPane.showMessageDialog(null, "Favor Preencher a Filial do Usuário!");
                edtLogin.grabFocus();
            } else {
                edtSenha.grabFocus();
            }
        }
    }//GEN-LAST:event_edtLoginKeyPressed

    private void btnEntrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntrarMouseClicked
        // TODO add your handling code here:
        ValidaDadosUsuarioTela();
    }//GEN-LAST:event_btnEntrarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        iniciaLogin();
        
//        ConfiguracaoBancoDados configuracaoBanco = new ConfiguracaoBancoDados();
//
//        int numero = 0;
//        //Se acessa o banco de dados,habilita 1 para abrir o login
//        if (ConexaoFirebird.verificagetConexao()) {
//            numero = 1;
//        } else {
//            numero = 2;
//        }
//        switch (numero) {
//            case 1:
//                iniciaLogin();
//                break;
//            case 2:
//                System.out.println("Erro!");
//                //Abre a tela de configuracao do banco de dados
//                configuracaoBanco.setVisible(true);
//                break;
//        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Panel_Principal;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnSair;
    private javax.swing.JTextField edtFilial;
    private javax.swing.JTextField edtLogin;
    private javax.swing.JTextField edtSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
