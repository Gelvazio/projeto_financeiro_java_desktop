package Estrutura;

import ModelCadastro.ModelCor;
import Principal.Conexao;
import Principal.MetodosGlobais;
import View.CadEstado;
import VisaoConsultasCadastro.ConsultaPadrao;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Gelvazio
 */

public class ViewConsultaPadrao extends MetodosGlobais {

    String mensagem;
    String mensagem_dois;
    String SQLConsultaCores;
    JTextField campoCodigo;

    /**
     * Creates new form LocalizaCores
     */
    public DefaultComboBoxModel getComboCampo() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        String auxCodigo = "Codigo";
        String auxNome = "Nome";
        try {
            modelo.addElement(auxCodigo);
            modelo.addElement(auxNome);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro no Método getComboCampo(): \n" + erro.getMessage());
        } finally {
            return modelo;
        }
    }

    public DefaultComboBoxModel getComboValor() {
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        String auxIgual = "Igual";
        String auxDiferente = "Diferente";
        String auxMaior = "Maior";
        String auxMenor = "Menor";
        String auxMaiorIgual = "MaiorIgual";
        String auxMenorIgual = "MenorIgual";
        String auxParecido = "Parecido";
        try {
            modelo.addElement(auxIgual);
            modelo.addElement(auxParecido);
            modelo.addElement(auxMaior);
            modelo.addElement(auxMenor);
            modelo.addElement(auxMaiorIgual);
            modelo.addElement(auxMenorIgual);
            modelo.addElement(auxDiferente);
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro no Método getComboValor(): \n" + erro.getMessage());
        } finally {
            return modelo;
        }
    }

    public void PegaValorCamposComboboxCampo_E_Valor(String SQLValorCamposComboboxCampo_E_Valor) {
        String auxedtPesquisa = null;
        String auxCampo = cbCampo.getSelectedItem().toString();
        String auxValor = cbValor.getSelectedItem().toString();
        //Programação do SQL do Codigo
        if ((auxCampo.equals("Codigo")) & (auxValor.equals("Igual"))) {
            auxCampo = "cd_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "=" + auxedtPesquisa;
        } else if ((auxCampo.equals("Codigo")) & (auxValor.equals("Diferente"))) {
            auxCampo = "cd_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "<>" + auxedtPesquisa;
        } else if ((auxCampo.equals("Codigo")) & (auxValor.equals("Maior"))) {
            auxCampo = "cd_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = ">" + auxedtPesquisa;
        } else if ((auxCampo.equals("Codigo")) & (auxValor.equals("Menor"))) {
            auxCampo = "cd_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "<" + auxedtPesquisa;
        } else if ((auxCampo.equals("Codigo")) & (auxValor.equals("MaiorIgual"))) {
            auxCampo = "cd_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = ">=" + auxedtPesquisa;
        } else if ((auxCampo.equals("Codigo")) & (auxValor.equals("MenorIgual"))) {
            auxCampo = "cd_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "<=" + auxedtPesquisa;
        } else if ((auxCampo.equals("Codigo")) & (auxValor.equals("Parecido"))) {
            auxCampo = "cd_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "LIKE UPPER('" + auxedtPesquisa + "%')";
        }//######################################################################################################
        //######################################################################################################
        //######################################################################################################
        //######################################################################################################
        //######################################################################################################
        //######################################################################################################
        //Programação do SQL do Nome
        if ((auxCampo.equals("Nome")) & (auxValor.equals("Igual"))) {
            auxCampo = "ds_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "LIKE UPPER('" + auxedtPesquisa + "%')";
        } else if ((auxCampo.equals("Nome")) & (auxValor.equals("Diferente"))) {
            auxCampo = "ds_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "<> '" + auxedtPesquisa + "'";
        } else if ((auxCampo.equals("Nome")) & (auxValor.equals("Maior"))) {
            auxCampo = "ds_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = ">'" + auxedtPesquisa + "'";
        } else if ((auxCampo.equals("Nome")) & (auxValor.equals("Menor"))) {
            auxCampo = "ds_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "<'" + auxedtPesquisa + "'";
        } else if ((auxCampo.equals("Nome")) & (auxValor.equals("MaiorIgual"))) {
            auxCampo = "ds_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = ">='" + auxedtPesquisa + "'";
        } else if ((auxCampo.equals("Nome")) & (auxValor.equals("MenorIgual"))) {
            auxCampo = "ds_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "<='" + auxedtPesquisa + "'";
        } else if ((auxCampo.equals("Nome")) & (auxValor.equals("Parecido"))) {
            auxCampo = "ds_cor";
            auxedtPesquisa = edtPesquisa.getText();
            auxValor = "LIKE UPPER('" + auxedtPesquisa + "%')";
        }
        //######################################################################################################
        //######################################################################################################
        //######################################################################################################
        //######################################################################################################
        //######################################################################################################
        //######################################################################################################
        //E passado por parametro os valores do Edit  mais os valores selecionados do Combobox  para o SQL
        //A Variavel "SQLValorCamposComboboxCampo_E_Valor" recebe o SQL
        SQLValorCamposComboboxCampo_E_Valor = "select * from cor where " + auxCampo + " " + auxValor;
        //A Variavel global "SQLConsultaCores" recebe por parametro a variavel "SQLValorCamposComboboxCampo_E_Valor"
        SQLConsultaCores = SQLValorCamposComboboxCampo_E_Valor;
    }

    public void ValidaCampoPesquisa() {
        //JOptionPane.showMessageDialog(null, "Precisa Fazer Validacao de deixar digitar so numero qdo for o Codigo!!");
        //Nesta parte é feita a Validação do Edit  edtPesquisa, para que caso seja nulo de uma MSG.
        String auxedtPesquisa = edtPesquisa.getText();
        if (auxedtPesquisa.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Digitar algum valor ou caractere no campo Pesquisa!");
            edtPesquisa.grabFocus();
        } else {
            //Não estando Nulo o campo é chamado o Metodo abaixo que é o responsavel pela pesquisa Compelta da Tela.
            ListaCores();
        }
    }

    public ArrayList SQLConsultagetTodosCompleto() {
        //Aqui é chamado o Metodo "PegaValorCamposComboboxCampo_E_Valor("");" para pegar os valores da tela
        //Caso nao seja repassado ele nao da certo pois nao pega nada do edtPesquisa
        PegaValorCamposComboboxCampo_E_Valor("");
        ArrayList listaCor = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexao.getConexao();
            stmt = conn.createStatement();
            //Nessa Parte é passado po parametro os Dados da Variavel "SQLConsulta_Usuario" que contem o sql da pesquisa.
            rs = stmt.executeQuery(SQLConsultaCores);
            while (rs.next()) {
                int auxcd_cor = rs.getInt("cd_cor");
                String auxnm_cor = rs.getString("ds_cor");
                int auxcd_usuario = rs.getInt("cd_usuario");

//                ModelCor cor = new ModelCor(
//                        auxcd_cor,
//                        auxnm_cor,
//                        auxcd_usuario
//                );
//                listaCor.add(cor);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro no sql, SQLConsultagetTodos_Completo: \n" + erro.getMessage());
        } finally {
            Conexao.closeAll(conn);
            return listaCor;
        }
    }

    public void ListaCores() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        //Nesta Parte o ArrayList dos Usuarios(Que chama a Classe Usuario) 
        //recebe por parametro o Metodo "SQLConsultagetTodos_Completo()" que tera os dados da pesquisa
        //Este Metodo Chama o ArrayList  que tera os dados e passa para a DefaultTableModel
        ArrayList<ModelCor> cores = SQLConsultagetTodosCompleto();
        for (ModelCor auxCor : cores) {
            modelo.addRow(new Object[]{
                auxCor.getCd_cor(),
                auxCor.getDs_cor()
            });
        }
        tbGrid.setModel(modelo);
    }

    public ViewConsultaPadrao(){//JTextField campoCodigo) {
        initComponents();
        cbCampo.setModel(getComboCampo());
        cbValor.setModel(getComboValor());
        Centro();
        //this.campoCodigo = campoCodigo;
        
    }
    
    @Override
    public String getTitle() {
        //para chamar o pai basta escrever super na frente        
        //return super.getTitle(); //To change body of generated methods, choose Tools | Templates.
        return this.getTitulo();
    }
       
    protected String getTitulo(){
        return "Meu titulo de Testes";
    }
    
    /**
     * Este método é chamado de dentro do construtor para inicializar o
     * formulário.    ATENÇÃO: Não modifique o código. O conteúdo deste método é
     * sempre     regenerados pelo editor de formulários.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbGrid = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbCampo = new javax.swing.JComboBox();
        cbValor = new javax.swing.JComboBox();
        edtPesquisa = new javax.swing.JTextField();
        btnPesquisa = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoVisualizar = new javax.swing.JButton();
        metodosGlobais1 = new Principal.MetodosGlobais();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edtCodigo = new javax.swing.JTextField();
        edtDescricao = new javax.swing.JTextField();
        btnGravar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        metodosGlobais2 = new Principal.MetodosGlobais();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        edtCodigo1 = new javax.swing.JTextField();
        edtDescricao1 = new javax.swing.JTextField();
        btnGravar1 = new javax.swing.JButton();
        btnCancelar1 = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();
        btnConsulta1 = new javax.swing.JButton();
        btnSair1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Consulta de Cores");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbGrid.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbGrid.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbGridMouseClicked(evt);
            }
        });
        tbGrid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbGridKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbGrid);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 104, 835, 491));

        jLabel1.setText("Valor:");

        jLabel2.setText("Digite sua pesquisa ...");

        jLabel3.setText("Campo:");

        cbCampo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCampo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCampoActionPerformed(evt);
            }
        });

        cbValor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        edtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtPesquisaKeyPressed(evt);
            }
        });

        btnPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novosIcones/ICONES_SAGU/search-20x20.png"))); // NOI18N
        btnPesquisa.setText("Consultar");
        btnPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPesquisaMouseClicked(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novosIcones/ICONES_CAU/b01.gif"))); // NOI18N
        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Carregar");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbValor, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(edtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbValor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        botaoIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novosIcones/ICONES_SELECIONADOS/add.png"))); // NOI18N
        botaoIncluir.setText("Incluir");
        botaoIncluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botaoIncluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoIncluirMouseClicked(evt);
            }
        });
        botaoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirActionPerformed(evt);
            }
        });

        botaoExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONES_SELECIONADOS/canceled.png"))); // NOI18N
        botaoExcluir.setText("Excluir");
        botaoExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ICONES_SELECIONADOS/atualizar.png"))); // NOI18N
        botaoAlterar.setText("Alterar");
        botaoAlterar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botaoAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoAlterarMouseClicked(evt);
            }
        });
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/novosIcones/ICONES_GPWEB/icones/procurar_avancada.png"))); // NOI18N
        botaoVisualizar.setText("Visualizar");
        botaoVisualizar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        botaoVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoVisualizarActionPerformed(evt);
            }
        });

        metodosGlobais1.setTitle("Sistema Comercial DTS SYSTEMS");
        metodosGlobais1.setPreferredSize(new java.awt.Dimension(450, 350));
        metodosGlobais1.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Cadastro de Cores");
        metodosGlobais1.getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 206, 24));

        jLabel5.setText("Descrição:");
        metodosGlobais1.getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel6.setText("Código:");
        metodosGlobais1.getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        edtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigoKeyPressed(evt);
            }
        });
        metodosGlobais1.getContentPane().add(edtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 80, 25));

        edtDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDescricaoKeyPressed(evt);
            }
        });
        metodosGlobais1.getContentPane().add(edtDescricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 340, 25));

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
        metodosGlobais1.getContentPane().add(btnGravar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 120, 40));

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
        metodosGlobais1.getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 120, 40));

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
        metodosGlobais1.getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 120, 40));

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
        metodosGlobais1.getContentPane().add(btnConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 120, 40));

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Red.png"))); // NOI18N
        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        metodosGlobais1.getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 120, 40));

        metodosGlobais2.setTitle("Sistema Comercial DTS SYSTEMS");
        metodosGlobais2.setPreferredSize(new java.awt.Dimension(450, 350));
        metodosGlobais2.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Cadastro de Cores");
        metodosGlobais2.getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 206, 24));

        jLabel8.setText("Descrição:");
        metodosGlobais2.getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel9.setText("Código:");
        metodosGlobais2.getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        edtCodigo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigo1KeyPressed(evt);
            }
        });
        metodosGlobais2.getContentPane().add(edtCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 80, 25));

        edtDescricao1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDescricao1KeyPressed(evt);
            }
        });
        metodosGlobais2.getContentPane().add(edtDescricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 340, 25));

        btnGravar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Valid Green.png"))); // NOI18N
        btnGravar1.setText("Gravar");
        btnGravar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGravar1ActionPerformed(evt);
            }
        });
        btnGravar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnGravar1KeyPressed(evt);
            }
        });
        metodosGlobais2.getContentPane().add(btnGravar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 120, 40));

        btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Cancel.png"))); // NOI18N
        btnCancelar1.setText("Cancelar");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });
        btnCancelar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelar1KeyPressed(evt);
            }
        });
        metodosGlobais2.getContentPane().add(btnCancelar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 90, 120, 40));

        btnExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Remove Red.png"))); // NOI18N
        btnExcluir1.setText("Excluir");
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });
        btnExcluir1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnExcluir1KeyPressed(evt);
            }
        });
        metodosGlobais2.getContentPane().add(btnExcluir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 120, 40));

        btnConsulta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Search.png"))); // NOI18N
        btnConsulta1.setText("Consulta");
        btnConsulta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsulta1ActionPerformed(evt);
            }
        });
        btnConsulta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnConsulta1KeyPressed(evt);
            }
        });
        metodosGlobais2.getContentPane().add(btnConsulta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 120, 40));

        btnSair1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Red.png"))); // NOI18N
        btnSair1.setText("Sair");
        btnSair1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSair1ActionPerformed(evt);
            }
        });
        metodosGlobais2.getContentPane().add(btnSair1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 240, 120, 40));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(botaoIncluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoVisualizar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(metodosGlobais1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(metodosGlobais2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(botaoIncluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoAlterar)
                .addComponent(botaoExcluir)
                .addComponent(botaoVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 13, Short.MAX_VALUE)
                    .addComponent(metodosGlobais1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 12, Short.MAX_VALUE)))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 12, Short.MAX_VALUE)
                    .addComponent(metodosGlobais2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 825, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbGridMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbGridMouseClicked
        // TODO add your handling code here:
        int linha = tbGrid.getSelectedRow();
        if (linha >= 0) {
            String auxCodigo = tbGrid.getValueAt(linha, 0).toString();
            campoCodigo.setText(auxCodigo);
        }
    }//GEN-LAST:event_tbGridMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbCampoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCampoActionPerformed
    }//GEN-LAST:event_cbCampoActionPerformed

    private void btnPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPesquisaMouseClicked
        ValidaCampoPesquisa();
        edtPesquisa.getText();
        PegaValorCamposComboboxCampo_E_Valor("");
    }//GEN-LAST:event_btnPesquisaMouseClicked

    private void edtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtPesquisaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            ValidaCampoPesquisa();
        }
    }//GEN-LAST:event_edtPesquisaKeyPressed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        int linha = tbGrid.getSelectedRow();
        if (linha >= 0) {
            String auxCodigo = tbGrid.getValueAt(linha, 0).toString();
            campoCodigo.setText(auxCodigo);
            campoCodigo.grabFocus();
            dispose();
        }
    }//GEN-LAST:event_jButton3MouseClicked

private void tbGridKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbGridKeyPressed
// TODO add your handling code here:
    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        int linha = tbGrid.getSelectedRow();
        if (linha >= 0) {
            String auxCodigo = tbGrid.getValueAt(linha, 0).toString();
            campoCodigo.setText(auxCodigo);
            campoCodigo.grabFocus();
            dispose();
        }
    }
}//GEN-LAST:event_tbGridKeyPressed

    private void botaoIncluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoIncluirMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoIncluirMouseClicked

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private void botaoAlterarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoAlterarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAlterarMouseClicked

    private void botaoVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoVisualizarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoVisualizarActionPerformed

    private void edtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoKeyPressed
        String auxTexto = edtCodigo.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxTexto.equals("")) {
                //habilitaCampos(true);
                //Passa o código do generator para o campo
                //String auxCodigoGenerator = "" + cordb.ValidaCodigoGenerator();
               // String auxCodigoGenerator = "" + cordb.ValidaCodigoGenerator("GEN_ID","CD_COR");
//                edtCodigo.setText(auxCodigoGenerator);
//                edtDescricao.requestFocus();
            } else {
               // validaCodigoNaoNulo();
            }
        }
    }//GEN-LAST:event_edtCodigoKeyPressed

    private void edtDescricaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDescricaoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGravar.grabFocus();
        }
    }//GEN-LAST:event_edtDescricaoKeyPressed

    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravarActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente gravar o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
//            GravarAlterar();
        }
    }//GEN-LAST:event_btnGravarActionPerformed

    private void btnGravarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGravarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCancelar.grabFocus();
        }
    }//GEN-LAST:event_btnGravarKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente Cancelar a Edição?");
        if (resposta == JOptionPane.YES_OPTION) {
//            habilitaCampos(false);
            edtCodigo.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnExcluir.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
//        ExcluirRegistro();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExcluirKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConsulta.grabFocus();
        }
    }//GEN-LAST:event_btnExcluirKeyPressed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        ConsultaPadrao form = new ConsultaPadrao(edtCodigo);
        this.getDesktopPane().add(form);
        form.setVisible(true);
        edtCodigo.grabFocus();
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnConsultaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsultaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSair.grabFocus();
        }
    }//GEN-LAST:event_btnConsultaKeyPressed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        dispose();
    }//GEN-LAST:event_btnSairActionPerformed

    private void edtCodigo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigo1KeyPressed
        String auxTexto = edtCodigo.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxTexto.equals("")) {
//                habilitaCampos(true);
                //Passa o código do generator para o campo
                //String auxCodigoGenerator = "" + cordb.ValidaCodigoGenerator();
//                String auxCodigoGenerator = "" + cordb.ValidaCodigoGenerator("GEN_ID","CD_COR");
//                edtCodigo.setText(auxCodigoGenerator);
//                edtDescricao.requestFocus();
            } else {
                //validaCodigoNaoNulo();
            }
        }
    }//GEN-LAST:event_edtCodigo1KeyPressed

    private void edtDescricao1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDescricao1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnGravar.grabFocus();
        }
    }//GEN-LAST:event_edtDescricao1KeyPressed

    private void btnGravar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGravar1ActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente gravar o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
//            GravarAlterar();
        }
    }//GEN-LAST:event_btnGravar1ActionPerformed

    private void btnGravar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnGravar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCancelar.grabFocus();
        }
    }//GEN-LAST:event_btnGravar1KeyPressed

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente Cancelar a Edição?");
        if (resposta == JOptionPane.YES_OPTION) {
//            habilitaCampos(false);
            edtCodigo.grabFocus();
        }
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void btnCancelar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelar1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnExcluir.grabFocus();
        }
    }//GEN-LAST:event_btnCancelar1KeyPressed

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed
//        ExcluirRegistro();
    }//GEN-LAST:event_btnExcluir1ActionPerformed

    private void btnExcluir1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExcluir1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConsulta.grabFocus();
        }
    }//GEN-LAST:event_btnExcluir1KeyPressed

    private void btnConsulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsulta1ActionPerformed
        ConsultaPadrao form = new ConsultaPadrao(edtCodigo);
        this.getDesktopPane().add(form);
        form.setVisible(true);
        edtCodigo.grabFocus();
    }//GEN-LAST:event_btnConsulta1ActionPerformed

    private void btnConsulta1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsulta1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSair.grabFocus();
        }
    }//GEN-LAST:event_btnConsulta1KeyPressed

    private void btnSair1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSair1ActionPerformed
        dispose();
    }//GEN-LAST:event_btnSair1ActionPerformed

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoIncluirActionPerformed

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoAlterarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CadEstado().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoIncluir;
    private javax.swing.JButton botaoVisualizar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnConsulta1;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnGravar;
    private javax.swing.JButton btnGravar1;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSair1;
    private javax.swing.JComboBox cbCampo;
    private javax.swing.JComboBox cbValor;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtCodigo1;
    private javax.swing.JTextField edtDescricao;
    private javax.swing.JTextField edtDescricao1;
    private javax.swing.JTextField edtPesquisa;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private Principal.MetodosGlobais metodosGlobais1;
    private Principal.MetodosGlobais metodosGlobais2;
    private javax.swing.JTable tbGrid;
    // End of variables declaration//GEN-END:variables
}
