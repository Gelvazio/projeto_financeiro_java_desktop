/*
 * Copyright (C) 2014 Gelvazio
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Principal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio
 */
public class ConfiguracaoBancoDados extends JFrame {
    

   // public class ConfiguracaoBancoDados extends MetodosGlobais{

    final static String LOCALHOST = "Conexao.ini";

    private static final String driver = "org.firebirdsql.jdbc.FBDriver"; //Classe do driver
    private static String banco = "";
    private static String str_conn = "";//URL de conexão
    private static final String usuario = "SYSDBA"; //Usuário da base
    private static final String senha = "masterkey"; //Senha da base

    /**
     * Creates new form ConfiguracaoBancoDados
     */
    public ConfiguracaoBancoDados() {
        initComponents();
        setLocationRelativeTo(this);
    }

    public void selecionaBancoDados() {
        JFileChooser caminhoarquivo = new JFileChooser();
        caminhoarquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int i = caminhoarquivo.showSaveDialog(null);
        if (i == 1) {
            edtCaminho.setText("");
        } else {
            File arquivo = caminhoarquivo.getSelectedFile();
            edtCaminho.setText(arquivo.getPath());
        }
    }

    public static void carregaArquivoEmTexto() {
        //Este método carrega o arquivo ini na tela
        try {
            java.awt.Desktop.getDesktop().open(new File(LOCALHOST));
        } catch (FileNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Erro!Arquivo não encontrado! \n" + erro.getMessage());
        } catch (IOException erro) {
            JOptionPane.showMessageDialog(null, "Erro!Arquivo não encontrado!Parte dois! \n" + erro.getMessage());
        } finally {
            //Finalizando o evento
            System.exit(0);
        }
    }

    public boolean verificaCamposTela() {
        boolean verifica = false;
        //Validacao dos campos da tela
        String auxRede = edtRede.getText();
        String auxPorta = edtPorta.getText();
        String auxCaminho = edtCaminho.getText();

        if (auxRede.equals("")) {
            JOptionPane.showMessageDialog(null, "Endereço da Rede Nulo!");
            edtRede.requestFocus();
            verifica = false;
        } else if (auxPorta.equals("")) {
            JOptionPane.showMessageDialog(null, "Número da Porta Nulo!");
            edtPorta.requestFocus();
            verifica = false;
        } else if (auxCaminho.equals("")) {
            JOptionPane.showMessageDialog(null, "Caminho está Nulo!");
            edtCaminho.requestFocus();
            verifica = false;
        } else {
            verifica = true;
        }
        return verifica;
    }

    public void gravarIniBancoDados() {

        if (verificaCamposTela()) {
            //Instancia a classe que le e grava arquivos do Java
            BufferedWriter arquivo;
            String valores
                    = "Caminho=" + edtRede.getText() + ":"
                    + edtPorta.getText() + "/" + edtCaminho.getText();
            try {
                // abre o arquivo de texto para acrescentar linhas
                arquivo = new BufferedWriter(new FileWriter(LOCALHOST, true));
                // escreve a linha de texto
                arquivo.write(valores);
                // insere uma quebra de linha
                arquivo.newLine();
                // força a gravação dos dados em disco
                arquivo.flush();
                arquivo.close();
                JOptionPane.showMessageDialog(null, "Caminho do banco gravado com sucesso!");
                edtCaminho.setText("");
                edtRede.setText("");
                edtPorta.setText("");
                valores="";
            } catch (IOException erro) {
                JOptionPane.showMessageDialog(null, "Erro ao gravar o caminho do banco de dados!" + erro.getMessage());
            }
        }
    }

    public Connection getConexaoTelaBancoDados() {

        banco = edtRede.getText() + ":" + edtPorta.getText() + "/" + edtCaminho.getText();

        str_conn = "jdbc:firebirdsql://" + banco;

        Connection conn = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(str_conn, usuario, senha);

        } catch (ClassNotFoundException erro) {
            JOptionPane.showMessageDialog(null, "Erro de driver! \n" + erro.getMessage());
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro de Conexão! \n" + erro.getMessage());
        }
        return conn;
    }

    public boolean verificaConexaoTela() {
        String sqlUsuario
                = "SELECT                "
                + "    count(*) as total "
                + "FROM                  "
                + "    USUARIO           ";
        boolean existe = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConexaoTelaBancoDados();
            pstmt = conn.prepareStatement(sqlUsuario);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                existe = rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL. verificaConexaoTela(): \n" + e.getMessage());
        } finally {
            Conexao.closeAll(conn);
        }
        return existe;
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
        edtCaminho = new javax.swing.JTextField();
        edtRede = new javax.swing.JTextField();
        btnTestarConexao = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnGravarIni = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        edtPorta = new javax.swing.JTextField();
        btnAbreArquivoGravado = new javax.swing.JButton();
        btnPesquisa = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Configuração de Banco de Dados");

        jLabel2.setText("Caminho:");

        jLabel3.setText("Rede:");

        btnTestarConexao.setText("Testar Conexão");
        btnTestarConexao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestarConexaoActionPerformed(evt);
            }
        });

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        btnGravarIni.setText("Gravar Dados");
        btnGravarIni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravarIniActionPerformed(evt);
            }
        });

        jLabel6.setText("Porta:");

        btnAbreArquivoGravado.setText("Abrir Arquivo Gravado");
        btnAbreArquivoGravado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbreArquivoGravadoActionPerformed(evt);
            }
        });

        btnPesquisa.setText("Pesquisar");
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        jLabel4.setText("Localhost ou IP");

        jLabel5.setText("Porta 3050  ou 3060");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTestarConexao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAbreArquivoGravado))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnGravarIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(edtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addComponent(edtCaminho)
                            .addComponent(edtRede))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPesquisa)
                    .addComponent(jLabel4))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtRede, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel3)
                        .addGap(10, 10, 10)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(edtCaminho, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGravarIni, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTestarConexao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbreArquivoGravado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnGravarIniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarIniActionPerformed
        // TODO add your handling code here:
        if (verificaConexaoTela()) {
            gravarIniBancoDados();
        }
    }//GEN-LAST:event_btnGravarIniActionPerformed

    private void btnAbreArquivoGravadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbreArquivoGravadoActionPerformed
        // TODO add your handling code here:
        carregaArquivoEmTexto();
    }//GEN-LAST:event_btnAbreArquivoGravadoActionPerformed

    private void btnTestarConexaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestarConexaoActionPerformed
        // TODO add your handling code here:
        if (verificaCamposTela()) {
            if (verificaConexaoTela()) {
                JOptionPane.showMessageDialog(null, "Conexão ok!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro nos dados da tela!");
            }
        }
    }//GEN-LAST:event_btnTestarConexaoActionPerformed

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        // TODO add your handling code here:
        selecionaBancoDados();
    }//GEN-LAST:event_btnPesquisaActionPerformed

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
            java.util.logging.Logger.getLogger(ConfiguracaoBancoDados.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfiguracaoBancoDados.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfiguracaoBancoDados.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfiguracaoBancoDados.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfiguracaoBancoDados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbreArquivoGravado;
    private javax.swing.JButton btnGravarIni;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnTestarConexao;
    private javax.swing.JTextField edtCaminho;
    private javax.swing.JTextField edtPorta;
    private javax.swing.JTextField edtRede;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
