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
package View;

import ControleCadastro.EstadoDB;
import ControleCadastro.MunicipioDB;
import ControleCadastro.PaisDB;
import ControleCadastro.PessoaDB;
import ModelCadastro.Pessoa;
import Persistencia.PersistenciaTributacao;
import Principal.Conexao;
import VisaoConsultasCadastro.ConsultaEstadoPessoa;
import VisaoConsultasCadastro.ConsultaMunicipio;
import VisaoConsultasCadastro.ConsultaPaises;
import VisaoConsultasCadastro.ConsultaPessoa;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gelvazio Camargo
 */
public class ViewManutencaoPessoa extends ViewManutencaoPadrao {

    public ViewManutencaoPessoa() {
        initComponents();
    }
    
    EstadoDB estadodb = new EstadoDB();
    MunicipioDB cidadedb = new MunicipioDB();
    PaisDB paisdb = new PaisDB();
    PessoaDB pessoadb = new PessoaDB();

    private static final String sqlBuscaNomeCidade = "SELECT * FROM municipio WHERE cd_municipio=? and cd_estado=?";
    private static final String sqlBuscaNomeEstado = "SELECT estado.* FROM estado where estado.cd_estado=?";
    private static final String sqlBuscaNomePais = "SELECT  PAIS.* FROM PAIS where pais.CD_pais=?";
    private static final String sqlBuscaPessoa = "SELECT * FROM PESSOA WHERE cd_pessoa=?";

    Date data = new Date();
    SimpleDateFormat formatadata = new SimpleDateFormat("dd/MM/yyyy");
    GregorianCalendar hora = new GregorianCalendar();
    SimpleDateFormat formatahora = new SimpleDateFormat("HH:mm:hh");
    boolean VerificaHabilitacaoCampos = false;

    /**
     * Creates new form CadPessoa
     */
    public CadPessoa() {
        initComponents();
        edtCodigo.grabFocus();
        habilitaCampos(false);
    }

    private void AlteraParaDataHoraAtual() {
        edtDataAlteracao.setText(formatadata.format(data));
        edtHoraAlteracao.setText(formatahora.format(hora.getTime()));
    }

    private void habilitaCampos(boolean habilita) {
        edtCodigo.requestFocus();
        edtCodigo.setEnabled(!habilita);
        edtCPF.setEnabled(habilita);
        edtNome.setEnabled(habilita);
        edtEndereco.setEnabled(habilita);
        edtBairro.setEnabled(habilita);
        edtNumero.setEnabled(habilita);
        cbSiglaEstado.setEnabled(habilita);
        cbEstado.setEnabled(habilita);
        edtCodigoCidade.setEnabled(habilita);
        cbCidade.setEnabled(habilita);
        edtCodigoPais.setEnabled(habilita);
        cbPais.setEnabled(habilita);
        btnLocalizaCidade.setEnabled(habilita);
        btnLocalizaEstado.setEnabled(habilita);
        btnLocalizaPais.setEnabled(habilita);
        edtTelefone.setEnabled(habilita);
        edtCEP.setEnabled(habilita);
        edtEmail.setEnabled(habilita);

        btnGravar.setEnabled(habilita);
        btnCancelar.setEnabled(habilita);
        btnConsulta.setEnabled(!habilita);
        btnExcluir.setEnabled(habilita);

        //Botoes Radio
        RadioAtivo.setEnabled(habilita);
        RadioConsumo.setEnabled(habilita);
        Radiorevenda.setEnabled(habilita);
        RadioPessoaFisica.setEnabled(habilita);
        RadioSimplesNacional.setEnabled(habilita);
        RadioRegimeNormal.setEnabled(habilita);
        RadioCliente.setEnabled(habilita);
        RadioVendedor.setEnabled(habilita);
        RadioFornecedor.setEnabled(habilita);
        RadioTransportadora.setEnabled(habilita);

        //Datas
        edtDataAlteracao.setEnabled(habilita);
        edtDataCadastro.setEnabled(habilita);
        edtHoraAlteracao.setEnabled(habilita);
        edtHoraCadastro.setEnabled(habilita);

        edtInscricaoEstadual.setEnabled(habilita);

        if (habilita) {
            VerificaHabilitacaoCampos = true;
            edtCodigo.requestFocus();
        } else {
            VerificaHabilitacaoCampos = false;
            LimpaTela();
        }
    }
    
    private void LimpaTela() {
        edtCodigo.setText("");
        edtCPF.setText("");
        edtNome.setText("");
        edtEndereco.setText("");
        edtBairro.setText("");
        edtNumero.setText("");
        edtCodigoCidade.setText("");
        edtCodigoPais.setText("");
        edtTelefone.setText("");
        edtCEP.setText("");
        edtEmail.setText("");
        edtCodigo.grabFocus();

        cbCidade.removeAllItems();
        cbEstado.removeAllItems();
        cbSiglaEstado.removeAllItems();
        cbPais.removeAllItems();

        //Botoes Radio
        RadioAtivo.setSelected(false);
        RadioConsumo.setSelected(false);
        Radiorevenda.setSelected(false);
        RadioPessoaFisica.setSelected(false);
        RadioSimplesNacional.setSelected(false);
        RadioRegimeNormal.setSelected(false);
        RadioCliente.setSelected(false);
        RadioVendedor.setSelected(false);
        RadioFornecedor.setSelected(false);
        RadioTransportadora.setSelected(false);

        //Datas
        edtDataAlteracao.setText("");
        edtDataCadastro.setText("");
        edtHoraAlteracao.setText("");
        edtHoraCadastro.setText("");
        edtInscricaoEstadual.setText("");
    }

    private DefaultComboBoxModel ValidaCampoCodigoEstadoNaoNulo() {
        String cd_estado = cbSiglaEstado.getSelectedItem().toString();
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if (estadodb.getEstado(cd_estado)) {
            try {
                conn = Conexao.getConexao();
                pstmt = conn.prepareStatement(sqlBuscaNomeEstado);
                pstmt.setString(1, cd_estado);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String auxNome = rs.getString("ds_estado");
                    cbEstado.setSelectedItem(auxNome);
                    edtCodigoCidade.grabFocus();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro no sql, ValidaCampoCodigoEstadoNaoNulo(): \n" + erro.getMessage());
            } finally {
                Conexao.closeAll(conn);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Estado nao cadastrado!");
            cbSiglaEstado.requestFocus();
        }
        return modelo;
    }

    private DefaultComboBoxModel ValidaCampoCodigoCidadeNaoNulo() {
        String auxTexto = edtCodigoCidade.getText();
        int cd_municipio = Integer.parseInt(auxTexto);
        String auxcd_estado = cbSiglaEstado.getSelectedItem().toString();

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if (cidadedb.getCidade(auxcd_estado, cd_municipio)) {
            try {
                conn = Conexao.getConexao();
                pstmt = conn.prepareStatement(sqlBuscaNomeCidade);
                pstmt.setInt(1, cd_municipio);
                pstmt.setString(2, auxcd_estado);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String auxNome = rs.getString("ds_municipio");
                    cbCidade.setSelectedItem(auxNome);
                    edtCodigoPais.grabFocus();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro no sql, ValidaCampoCodigoCidadeNaoNulo(): \n" + erro.getMessage());
            } finally {
                Conexao.closeAll(conn);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cidade nao cadastrada");
            edtCodigoCidade.setText("");
            cbCidade.setSelectedItem("Selecione a Cidade");
            edtCodigoCidade.requestFocus();
        }
        return modelo;
    }

    private DefaultComboBoxModel ValidaCampoCodigoPaisNaoNulo() {
        String auxTexto = edtCodigoPais.getText();
        int cd_pais = Integer.parseInt(auxTexto);

        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        if (paisdb.getPais(cd_pais)) {
            try {
                conn = Conexao.getConexao();
                pstmt = conn.prepareStatement(sqlBuscaNomePais);
                pstmt.setInt(1, cd_pais);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    String auxNome = rs.getString("nm_pais");
                    cbPais.setSelectedItem(auxNome);
                    edtTelefone.grabFocus();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro no sql, ValidaCampoCodigoPaisNaoNulo(): \n" + erro.getMessage());
            } finally {
                Conexao.closeAll(conn);
            }
        } else {
            JOptionPane.showMessageDialog(null, "País nao cadastrado!");
            edtCodigoPais.requestFocus();
        }
        return modelo;
    }

    private void ComboBoxEstado() {
        if (VerificaHabilitacaoCampos) {
            if (cbEstado.getSelectedItem() != null) {
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                String sql
                        = "SELECT                "
                        + "    ESTADO.cd_estado          "
                        + "FROM                  "
                        + "     ESTADO           "
                        + "WHERE                 "
                        + "    ESTADO.DS_ESTADO='" + cbEstado.getSelectedItem() + "'";
                try {
                    conn = Conexao.getConexao();
                    stmt = conn.createStatement();
                    rs = stmt.executeQuery(sql);
                    rs.next();
                    String auxcd_estado = rs.getString("cd_estado");
                    cbSiglaEstado.setSelectedItem(auxcd_estado);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Estado não encontrado!Erro na funcao ComboBoxEstado()!:" + ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Estado não cadastrado na base de dados!");
            }
        }
    }

    private void ComboBoxCidade() {
        if (VerificaHabilitacaoCampos) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            String auxCodigoEstado = cbSiglaEstado.getSelectedItem().toString();
            String nomeCidade = cbCidade.getSelectedItem().toString();
            String sql
                    = "SELECT                      "
                    + "    MUNICIPIO.*             "
                    + "FROM                        "
                    + "    MUNICIPIO               "
                    + "WHERE                       "
                    + "     MUNICIPIO.CD_ESTADO=?   "
                    + "     AND                     "
                    + "     MUNICIPIO.DS_MUNICIPIO=?";// + cbCidade.getSelectedItem() + "'";
            try {
                conn = Conexao.getConexao();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, auxCodigoEstado);
                pstmt.setString(2, nomeCidade);
                rs = pstmt.executeQuery();
                rs.next();
                int auxcd_municipio = rs.getInt("cd_municipio");
                String aux = "";
                aux = "" + auxcd_municipio;
                edtCodigoCidade.setText(aux);
                edtCodigoCidade.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Cidade não encontrada!Erro na funcao ComboBoxCidade()!:" + ex.getMessage());
            }
        }
    }

    private void ComboBoxPais() {
        if (VerificaHabilitacaoCampos) {
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;
            String sql
                    = "SELECT            "
                    + "    PAIS.*        "
                    + "FROM              "
                    + "    PAIS          "
                    + "WHERE             "
                    + "    PAIS.NM_PAIS='" + cbPais.getSelectedItem() + "'";
            try {
                conn = Conexao.getConexao();
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);
                rs.next();
                int auxcd_pais = rs.getInt("cd_pais");
                String aux = "";
                aux = "" + auxcd_pais;
                edtCodigoPais.setText(aux);
                edtCodigoPais.setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Pais não encontrado!Erro na funcao ComboBoxPais()!:" + ex.getMessage());
            }
        }
    }

    private void ExcluirRegistro() {
        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o registro?");
        if (resposta == JOptionPane.YES_OPTION) {
            int auxCodigo = Integer.parseInt(edtCodigo.getText());
            if (pessoadb.excluirPessoa(auxCodigo)) {
                JOptionPane.showMessageDialog(null, "Exclusão efetuada com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possivel excluir o registro!!");

            }
        }
        habilitaCampos(false);
        LimpaTela();
    }

    @Override
    protected void gravarAlterar() {
        String auxcd_pessoa = edtCodigo.getText();
        String nm_pessoa    = edtNome.getText();
        String ds_endereco  = edtEndereco.getText();
        String nr_endereco  = edtNumero.getText();
        String ds_bairro    = edtBairro.getText();
        String cd_estado    = cbSiglaEstado.getSelectedItem().toString();
        String auxcd_cidade = edtCodigoCidade.getText();
        String auxcd_pais   = edtCodigoPais.getText();
        String cd_cep       = edtCEP.getText();
        String ds_email     = edtEmail.getText();
        String nr_telefone  = edtTelefone.getText();
        String cd_cgccpf    = edtCPF.getText();

        String auxcd_usuario = "1";
        //programação da Data e Hora
        int cd_pessoa = Integer.parseInt(auxcd_pessoa);
        if (!pessoadb.getPessoa(cd_pessoa)) {
            edtDataCadastro.setText(formatadata.format(data));
            edtHoraCadastro.setText(formatahora.format(hora.getTime()));
            edtDataAlteracao.setText(formatadata.format(data));
            edtHoraAlteracao.setText(formatahora.format(hora.getTime()));
        } else {
            edtDataAlteracao.setText(formatadata.format(data));
            edtHoraAlteracao.setText(formatahora.format(hora.getTime()));
        }
        String dt_alt = edtDataAlteracao.getText();
        String hr_alt = edtHoraAlteracao.getText();
        String dt_cad = edtDataCadastro.getText();
        String hr_cad = edtHoraCadastro.getText();

        //Conversao de Datas e Horas
        //Inicio
        java.text.DateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);

        //Inicia com null as variaveis
        java.util.Date javautilDateDataCadastro = null;
        java.util.Date javautilDateDataAlteracao = null;

        //Altera para java.util.date a String do campo de texto
        javautilDateDataCadastro = (java.util.Date) formatter.parse(dt_cad);
        javautilDateDataAlteracao = (java.util.Date) formatter.parse(dt_alt);

        //Altera de java.util.date para java.sql.date
        java.sql.Date DataCadastro = new java.sql.Date(javautilDateDataCadastro.getTime());
        java.sql.Date DataAlteracao = new java.sql.Date(javautilDateDataAlteracao.getTime());

        //Conversao da Hora
        //Converte para java.util.Date
        java.sql.Date horaCadastro = new java.sql.Date(formatahora.parse(hr_cad).getTime());
        java.sql.Date horaAlteracao = new java.sql.Date(formatahora.parse(hr_alt).getTime());

        //Converte de java.util.Date para  para java.sql.Time
        java.sql.Time javasqltimehoraCadastro = new java.sql.Time(horaCadastro.getTime());
        java.sql.Time javasqltimehoraAlteracao = new java.sql.Time(horaAlteracao.getTime());
        //Fim Parte Conversao Datas

        String auxcd_filial = "1";
        String nr_inscricao_estadual = edtInscricaoEstadual.getText();

        //Botoes Radio da Tela
        String auxfg_ativo          = "0";
        String auxtipo_consumo      = "0";
        String auxregime_tributacao = "0";
        String auxfg_cliente        = "0";
        String auxfg_vendedor       = "0";
        String auxfg_fornecedor     = "0";
        String auxfg_transportador  = "0";

        if (RadioAtivo.isSelected()) {
            auxfg_ativo = "1";
        } else {
            auxfg_ativo = "0";
        }
        if (RadioConsumo.isSelected()) {
            auxtipo_consumo = "0";
        } else if (Radiorevenda.isSelected()) {
            auxtipo_consumo = "1";
        }
        if (RadioPessoaFisica.isSelected()) {
            auxregime_tributacao = "0";
        } else if (RadioSimplesNacional.isSelected()) {
            auxregime_tributacao = "1";
        } else if (RadioRegimeNormal.isSelected()) {
            auxregime_tributacao = "2";
        }
        if (RadioCliente.isSelected()) {
            auxfg_cliente = "1";
        } else {
            auxfg_cliente = "0";
        }
        if (RadioVendedor.isSelected()) {
            auxfg_vendedor = "1";
        } else {
            auxfg_vendedor = "0";
        }
        if (RadioFornecedor.isSelected()) {
            auxfg_fornecedor = "1";
        } else {
            auxfg_fornecedor = "0";
        }
        if (RadioTransportadora.isSelected()) {
            auxfg_transportador = "1";
        } else {
            auxfg_transportador = "0";
        }

        //Conversao das variaveis
        cd_pessoa = Integer.parseInt(auxcd_pessoa);
        int fg_cliente = Integer.parseInt(auxfg_cliente);
        int fg_vendedor = Integer.parseInt(auxfg_vendedor);
        int fg_fornecedor = Integer.parseInt(auxfg_fornecedor);
        int cd_cidade = Integer.parseInt(auxcd_cidade);
        int cd_pais = Integer.parseInt(auxcd_pais);
        int cd_usuario = Integer.parseInt(auxcd_usuario);
        int cd_filial = Integer.parseInt(auxcd_filial);
        int tipo_consumo = Integer.parseInt(auxtipo_consumo);
        int regime_tributacao = Integer.parseInt(auxregime_tributacao);
        int fg_transportador = Integer.parseInt(auxfg_transportador);
        int fg_ativo = Integer.parseInt(auxfg_ativo);
        //Valida Campos da Tela
        if (auxcd_pessoa.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o Código da Pessoa!");
            edtCodigo.grabFocus();
        } else if (cd_cgccpf.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o CPF da Pessoa!");
            edtCPF.grabFocus();
        } else if (nm_pessoa.equals("")) {
            JOptionPane.showMessageDialog(null, "Favor Preencher o Nome da Pessoa!");
            edtNome.grabFocus();
        } else {

            if (nr_inscricao_estadual.equals("")) {
                nr_inscricao_estadual = "ISENTO";
            }
            //Dados da classe
            Pessoa pessoa = new Pessoa(
                    cd_pessoa,
                    nm_pessoa,
                    fg_cliente,
                    fg_vendedor,
                    fg_fornecedor,
                    ds_endereco,
                    nr_endereco,
                    ds_bairro,
                    cd_estado,
                    cd_cidade,
                    cd_pais,
                    cd_cep,
                    ds_email,
                    nr_telefone,
                    cd_cgccpf,
                    cd_usuario,
                    DataAlteracao,
                    javasqltimehoraAlteracao,
                    DataCadastro,
                    javasqltimehoraCadastro,
                    cd_filial,
                    nr_inscricao_estadual,
                    tipo_consumo,
                    regime_tributacao,
                    fg_transportador,
                    fg_ativo
            );

            if (pessoadb.getPessoa(cd_pessoa)) {
                if (pessoadb.alterarPessoa(pessoa)) {
                    JOptionPane.showMessageDialog(null, "Registro alterado com sucesso!");
                    habilitaCampos(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível alterar o registro!");
                }
            } else if (pessoadb.inserirPessoa(pessoa)) {
                JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!");
                habilitaCampos(false);
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível inserir o registro!");
            }
        }
    }

    

    private void ValidaCampoCodigoNaoNulo() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int cd_pessoa = Integer.parseInt(edtCodigo.getText());
        if (pessoadb.getPessoa(cd_pessoa)) {
            //Habilitação dos campos
            habilitaCampos(true);

            //Combobox Estado
            cbEstado.setModel(estadodb.getComboboxPessoaEstado());
            ComboBoxEstado();
            //Carrega as siglas dos estados tambem
            cbSiglaEstado.setModel(estadodb.getComboEstado());
            //Combobox Cidade
            cbCidade.setModel(cidadedb.getComboCidade());
            ComboBoxCidade();

            //Combobox Pais
            cbPais.setModel(paisdb.getComboPais());
            ComboBoxPais();
            try {
                conn = Conexao.getConexao();
                pstmt = conn.prepareStatement(sqlBuscaPessoa);
                pstmt.setInt(1, cd_pessoa);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    //Conversão das variaveis para String
                    int auxCD_CIDADE           = rs.getInt("CD_CIDADE");
                    String auxCD_CIDADE_String = "";
                    auxCD_CIDADE_String        = "0" + auxCD_CIDADE;
                    int auxCD_PAIS             = rs.getInt("CD_PAIS");
                    String auxCD_PAIS_String   = null;
                    auxCD_PAIS_String          = "0" + auxCD_PAIS;
                    //Campos String
                    //NÃO DEIXAR ESPAÇOS!!!!!!
                    String auxInscricaoEstadual = rs.getString("nr_inscricao_estadual");
                    String auxNM_PESSOA         = rs.getString("NM_PESSOA");
                    String auxDS_ENDERECO       = rs.getString("DS_ENDERECO");
                    String auxNR_ENDERECO       = rs.getString("NR_ENDERECO");
                    String auxDS_BAIRRO         = rs.getString("DS_BAIRRO");
                    String auxCD_CEP            = rs.getString("CD_CEP");
                    String auxDS_EMAIL          = rs.getString("DS_EMAIL");
                    String auxNR_TELEFONE       = rs.getString("NR_TELEFONE");
                    String auxCD_CGCCPF         = rs.getString("CD_CGCCPF");
                    String auxCD_ESTADO         = rs.getString("CD_ESTADO");

                    //Campos dos botoes radio da tela 
                    int auxfg_ativo          = rs.getInt("fg_ativo");
                    int auxtipo_consumo      = rs.getInt("tipo_consumo");
                    int auxregime_tributacao = rs.getInt("regime_tributacao");
                    int auxfg_cliente        = rs.getInt("fg_cliente");
                    int auxfg_vendedor       = rs.getInt("fg_vendedor");
                    int auxfg_fornecedor     = rs.getInt("fg_fornecedor");
                    int auxfg_transportador  = rs.getInt("fg_transportador");

                    //Campos de Data
                    Date dt_alt = rs.getDate("dt_alt");
                    Time hr_alt = rs.getTime("hr_alt");
                    Date dt_cad = rs.getDate("dt_cad");
                    Time hr_cad = rs.getTime("hr_cad");
                    edtDataCadastro.setText(formatadata.format(dt_cad));
                    edtHoraCadastro.setText(formatahora.format(hr_cad));

                    if (auxfg_ativo == 1) {
                        RadioAtivo.setSelected(true);
                    } else {
                        RadioAtivo.setSelected(false);
                    }
                    if (auxtipo_consumo == 1) {
                        Radiorevenda.setSelected(true);
                    } else {
                        RadioConsumo.setSelected(true);
                    }

                    switch (auxregime_tributacao) {
                        case 0:
                            RadioPessoaFisica.setSelected(true);
                            break;
                        case 1:
                            RadioSimplesNacional.setSelected(true);
                            break;
                        default:
                            RadioRegimeNormal.setSelected(true);
                            break;
                    }
                    if (auxfg_cliente == 1) {
                        RadioCliente.setSelected(true);
                    } else {
                        RadioCliente.setSelected(false);
                    }
                    if (auxfg_vendedor == 1) {
                        RadioVendedor.setSelected(true);
                    } else {
                        RadioVendedor.setSelected(false);
                    }
                    if (auxfg_fornecedor == 1) {
                        RadioFornecedor.setSelected(true);
                    } else {
                        RadioFornecedor.setSelected(false);
                    }
                    if (auxfg_transportador == 1) {
                        RadioTransportadora.setSelected(true);
                    } else {
                        RadioTransportadora.setSelected(false);
                    }
                    edtInscricaoEstadual.setText(auxInscricaoEstadual);
                    cbSiglaEstado.setSelectedItem(auxCD_ESTADO);
                    edtCodigoCidade.setText(auxCD_CIDADE_String);
                    edtCodigoPais.setText(auxCD_PAIS_String);

                    edtNome.setText(auxNM_PESSOA);
                    edtEndereco.setText(auxDS_ENDERECO);
                    edtNumero.setText(auxNR_ENDERECO);
                    edtBairro.setText(auxDS_BAIRRO);
                    edtCEP.setText(auxCD_CEP);
                    edtEmail.setText(auxDS_EMAIL);
                    edtTelefone.setText(auxNR_TELEFONE);
                    edtCPF.setText(auxCD_CGCCPF);
                    edtCPF.grabFocus();

                    //Validacao dos combobox da tela
                    ValidaCampoCodigoEstadoNaoNulo();

                    ValidaCampoCodigoPaisNaoNulo();
                    AlteraParaDataHoraAtual();
                }
            } catch (SQLException erro) {
                JOptionPane.showMessageDialog(null, "Erro de conexão no Método ValidaCampoCodigoNãoNulo()! " + erro.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pessoa não Cadastrada!");
            habilitaCampos(false);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        edtCodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        edtNome = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        edtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        edtEndereco = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        edtNumero = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        edtCEP = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        edtCPF = new javax.swing.JTextField();
        edtTelefone = new javax.swing.JTextField();
        btnLocalizaCidade = new javax.swing.JButton();
        btnLocalizaPais = new javax.swing.JButton();
        edtCodigoCidade = new javax.swing.JTextField();
        edtCodigoPais = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnLocalizaEstado = new javax.swing.JButton();
        cbEstado = new javax.swing.JComboBox();
        cbCidade = new javax.swing.JComboBox();
        cbPais = new javax.swing.JComboBox();
        edtBairro = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        RadioAtivo = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        RadioConsumo = new javax.swing.JRadioButton();
        Radiorevenda = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        RadioCliente = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        RadioFornecedor = new javax.swing.JRadioButton();
        RadioVendedor = new javax.swing.JRadioButton();
        RadioTransportadora = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        RadioPessoaFisica = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        RadioSimplesNacional = new javax.swing.JRadioButton();
        RadioRegimeNormal = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        edtDataCadastro = new javax.swing.JTextField();
        edtHoraCadastro = new javax.swing.JTextField();
        edtDataAlteracao = new javax.swing.JTextField();
        edtHoraAlteracao = new javax.swing.JTextField();
        cbSiglaEstado = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        edtInscricaoEstadual = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnConsulta = new javax.swing.JButton();
        botaoIncluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        edtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigoKeyPressed(evt);
            }
        });
        jPanel4.add(edtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 80, 30));

        jLabel2.setText("Nome:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 40, -1));

        edtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtNomeKeyPressed(evt);
            }
        });
        jPanel4.add(edtNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 330, 30));

        jLabel4.setText("E-mail:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 40, -1));

        edtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtEmailKeyPressed(evt);
            }
        });
        jPanel4.add(edtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 330, 30));

        jLabel6.setText("Endereço:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 70, -1));

        jLabel8.setText("Telefone:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 60, -1));

        jLabel9.setText("Numero:");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));

        edtEndereco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtEnderecoKeyPressed(evt);
            }
        });
        jPanel4.add(edtEndereco, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 330, 30));

        jLabel10.setText("Bairro:");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 50, -1));

        edtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtNumeroKeyPressed(evt);
            }
        });
        jPanel4.add(edtNumero, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, 70, 30));

        jLabel13.setText("CEP:");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, -1));

        edtCEP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCEPKeyPressed(evt);
            }
        });
        jPanel4.add(edtCEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 130, 30));

        jLabel14.setText("CPF /CNPJ:");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        edtCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCPFKeyPressed(evt);
            }
        });
        jPanel4.add(edtCPF, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 140, 30));

        edtTelefone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtTelefoneKeyPressed(evt);
            }
        });
        jPanel4.add(edtTelefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 160, 30));

        btnLocalizaCidade.setText("...");
        btnLocalizaCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizaCidadeActionPerformed(evt);
            }
        });
        btnLocalizaCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLocalizaCidadeKeyPressed(evt);
            }
        });
        jPanel4.add(btnLocalizaCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 30, 30));

        btnLocalizaPais.setText("...");
        btnLocalizaPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizaPaisActionPerformed(evt);
            }
        });
        btnLocalizaPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLocalizaPaisKeyPressed(evt);
            }
        });
        jPanel4.add(btnLocalizaPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 30, 30));

        edtCodigoCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigoCidadeKeyPressed(evt);
            }
        });
        jPanel4.add(edtCodigoCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 55, 30));

        edtCodigoPais.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtCodigoPaisKeyPressed(evt);
            }
        });
        jPanel4.add(edtCodigoPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 55, 30));

        jLabel3.setText("Cód.Cidade:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 70, -1));

        jLabel5.setText("Cód País:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 60, -1));

        jLabel7.setText("Cód.Estado:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 70, -1));

        btnLocalizaEstado.setText("...");
        btnLocalizaEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocalizaEstadoActionPerformed(evt);
            }
        });
        btnLocalizaEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnLocalizaEstadoKeyPressed(evt);
            }
        });
        jPanel4.add(btnLocalizaEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 30, 30));

        cbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbEstadoActionPerformed(evt);
            }
        });
        jPanel4.add(cbEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 270, 30));

        cbCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCidadeActionPerformed(evt);
            }
        });
        jPanel4.add(cbCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 270, 30));

        cbPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPaisActionPerformed(evt);
            }
        });
        jPanel4.add(cbPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 270, 30));

        edtBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtBairroKeyPressed(evt);
            }
        });
        jPanel4.add(edtBairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, 190, 30));

        jLabel11.setText("Código:");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 60, 30));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        RadioAtivo.setText("Ativo");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("Tipo de Consumo:");

        RadioConsumo.setText("Consumo");
        RadioConsumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioConsumoActionPerformed(evt);
            }
        });

        Radiorevenda.setText("Revenda");
        Radiorevenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadiorevendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Radiorevenda)
                    .addComponent(RadioConsumo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RadioConsumo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Radiorevenda)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        RadioCliente.setText("Cliente");

        jLabel12.setText("Tipo de Cadastro:");

        RadioFornecedor.setText("Fornecedor");

        RadioVendedor.setText("Vendedor");

        RadioTransportadora.setText("Transportadora");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RadioVendedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RadioCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RadioFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RadioTransportadora, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadioCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadioFornecedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadioVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadioTransportadora)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        RadioPessoaFisica.setText("Sem Regime(Pessoa Física)");
        RadioPessoaFisica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioPessoaFisicaActionPerformed(evt);
            }
        });

        jLabel15.setText("Regime de Tributação:");

        RadioSimplesNacional.setText("Simples Nacional");
        RadioSimplesNacional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioSimplesNacionalActionPerformed(evt);
            }
        });

        RadioRegimeNormal.setText("Regime Normal");
        RadioRegimeNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioRegimeNormalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(RadioPessoaFisica, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                        .addComponent(RadioSimplesNacional, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RadioRegimeNormal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadioPessoaFisica)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RadioSimplesNacional)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(RadioRegimeNormal)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel18.setText("Data Cadastro:");

        jLabel19.setText("Hora Cadastro:");

        jLabel20.setText("Data Alteração:");

        jLabel21.setText("Hora Alteração:");

        edtDataCadastro.setEditable(false);
        edtDataCadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDataCadastroKeyPressed(evt);
            }
        });

        edtHoraCadastro.setEditable(false);
        edtHoraCadastro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtHoraCadastroKeyPressed(evt);
            }
        });

        edtDataAlteracao.setEditable(false);
        edtDataAlteracao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDataAlteracaoKeyPressed(evt);
            }
        });

        edtHoraAlteracao.setEditable(false);
        edtHoraAlteracao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtHoraAlteracaoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtDataCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtHoraCadastro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtHoraAlteracao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edtDataAlteracao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(edtDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel19)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtHoraCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtDataAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edtHoraAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(RadioAtivo)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(RadioAtivo)
                .addGap(7, 7, 7)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 220, -1));

        cbSiglaEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbSiglaEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbSiglaEstadoKeyPressed(evt);
            }
        });
        jPanel4.add(cbSiglaEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, 30));

        jLabel17.setText("Insc.Estadual:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));

        edtInscricaoEstadual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtInscricaoEstadualKeyPressed(evt);
            }
        });
        jPanel4.add(edtInscricaoEstadual, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 100, 30));

        jTabbedPane1.addTab("Cadastro", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 530));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        botaoIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Faturamento/Knob Add.png"))); // NOI18N
        botaoIncluir.setText("Incluir");
        botaoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirActionPerformed(evt);
            }
        });
        botaoIncluir.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botaoIncluirKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 142, 210));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoKeyPressed
        String codigo = edtCodigo.getText();
        onKeyPressedCampoCodigo(evt,codigo);
    }//GEN-LAST:event_edtCodigoKeyPressed

    private void edtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtNomeKeyPressed
        
    }//GEN-LAST:event_edtNomeKeyPressed

    private void edtEmailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtEmailKeyPressed
        
    }//GEN-LAST:event_edtEmailKeyPressed

    private void edtEnderecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtEnderecoKeyPressed
        
    }//GEN-LAST:event_edtEnderecoKeyPressed

    private void edtNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtNumeroKeyPressed
        
    }//GEN-LAST:event_edtNumeroKeyPressed

    private void edtCEPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCEPKeyPressed
        
    }//GEN-LAST:event_edtCEPKeyPressed

    private void edtCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCPFKeyPressed
        
    }//GEN-LAST:event_edtCPFKeyPressed

    private void edtTelefoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtTelefoneKeyPressed
        
    }//GEN-LAST:event_edtTelefoneKeyPressed

    
    private void btnLocalizaCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLocalizaCidadeKeyPressed
        
    }//GEN-LAST:event_btnLocalizaCidadeKeyPressed

    private void btnLocalizaPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizaPaisActionPerformed
        ConsultaPaises form = new ConsultaPaises(edtCodigoPais);
        JInternalFrame j = new JInternalFrame();
        j.getDesktopPane().add(form);
        form.setVisible(true);
    }//GEN-LAST:event_btnLocalizaPaisActionPerformed

    private void btnLocalizaPaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLocalizaPaisKeyPressed
        
    }//GEN-LAST:event_btnLocalizaPaisKeyPressed

    private void edtCodigoCidadeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoCidadeKeyPressed
        String auxTexto = edtCodigoCidade.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxTexto.equals("")) {
                JOptionPane.showMessageDialog(null, "Código da Cidade nulo!");
                edtCodigoCidade.requestFocus();
            } else {
                ValidaCampoCodigoCidadeNaoNulo();
            }
        }
    }//GEN-LAST:event_edtCodigoCidadeKeyPressed

    private void edtCodigoPaisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtCodigoPaisKeyPressed
        String auxTexto = edtCodigoPais.getText();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxTexto.equals("")) {
                JOptionPane.showMessageDialog(null, "Código do País nulo!");
                edtCodigoPais.requestFocus();
            } else {
                ValidaCampoCodigoPaisNaoNulo();
            }
        }
    }//GEN-LAST:event_edtCodigoPaisKeyPressed

    private void btnLocalizaEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizaEstadoActionPerformed
        ConsultaEstadoPessoa formulario = new ConsultaEstadoPessoa(cbSiglaEstado, cbEstado);
        JInternalFrame j = new JInternalFrame();
        j.getDesktopPane().add(formulario);
        formulario.setVisible(true);
    }//GEN-LAST:event_btnLocalizaEstadoActionPerformed

    private void btnLocalizaEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnLocalizaEstadoKeyPressed
        
    }//GEN-LAST:event_btnLocalizaEstadoKeyPressed

    private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbEstadoActionPerformed
        
    }//GEN-LAST:event_cbEstadoActionPerformed

    private void cbCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCidadeActionPerformed
        
    }//GEN-LAST:event_cbCidadeActionPerformed

    private void cbPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPaisActionPerformed
        
    }//GEN-LAST:event_cbPaisActionPerformed

    private void edtBairroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtBairroKeyPressed
        
    }//GEN-LAST:event_edtBairroKeyPressed

    private void RadioConsumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioConsumoActionPerformed
        onSelectRadioTipoConsumo(PersistenciaTributacao.TIPO_CONSUMO_CONSUMO);
    }//GEN-LAST:event_RadioConsumoActionPerformed

    private void RadiorevendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadiorevendaActionPerformed
        onSelectRadioTipoConsumo(PersistenciaTributacao.TIPO_CONSUMO_REVENDA);
    }//GEN-LAST:event_RadiorevendaActionPerformed

    private void RadioPessoaFisicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioPessoaFisicaActionPerformed
        onSelectRadioTributacao(PersistenciaTributacao.REGIME_TRIBUTACAO_PESSOA_FISICA);
    }//GEN-LAST:event_RadioPessoaFisicaActionPerformed

    private void RadioSimplesNacionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioSimplesNacionalActionPerformed
        onSelectRadioTributacao(PersistenciaTributacao.REGIME_TRIBUTACAO_SIMPLES_NACIONAL);
    }//GEN-LAST:event_RadioSimplesNacionalActionPerformed

    private void RadioRegimeNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioRegimeNormalActionPerformed
        onSelectRadioTributacao(PersistenciaTributacao.REGIME_TRIBUTACAO_NORMAL);
    }//GEN-LAST:event_RadioRegimeNormalActionPerformed

    private void edtDataCadastroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDataCadastroKeyPressed
        
    }//GEN-LAST:event_edtDataCadastroKeyPressed

    private void edtHoraCadastroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtHoraCadastroKeyPressed
        
    }//GEN-LAST:event_edtHoraCadastroKeyPressed

    private void edtDataAlteracaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDataAlteracaoKeyPressed
        
    }//GEN-LAST:event_edtDataAlteracaoKeyPressed

    private void edtHoraAlteracaoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtHoraAlteracaoKeyPressed
        
    }//GEN-LAST:event_edtHoraAlteracaoKeyPressed

    private void cbSiglaEstadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbSiglaEstadoKeyPressed
        
    }//GEN-LAST:event_cbSiglaEstadoKeyPressed

    private void edtInscricaoEstadualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtInscricaoEstadualKeyPressed
        
    }//GEN-LAST:event_edtInscricaoEstadualKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        cancelaEdicao();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnExcluir.grabFocus();
        }
    }//GEN-LAST:event_btnCancelarKeyPressed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed

        //        exclui();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnExcluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnExcluirKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConsulta.grabFocus();
        }
    }//GEN-LAST:event_btnExcluirKeyPressed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        ConsultaPessoa form = new ConsultaPessoa(edtCodigo);
        JInternalFrame j = new JInternalFrame();
        j.getDesktopPane().add(form);
        form.setVisible(true);
        edtCodigo.grabFocus();
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void btnConsultaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsultaKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSair.grabFocus();
        }
    }//GEN-LAST:event_btnConsultaKeyPressed

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed
        processaDadosInclusao();
    }//GEN-LAST:event_botaoIncluirActionPerformed

    private void botaoIncluirKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botaoIncluirKeyPressed
        
    }//GEN-LAST:event_botaoIncluirKeyPressed

    private void btnLocalizaCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocalizaCidadeActionPerformed
        ConsultaMunicipio formulario = new ConsultaMunicipio(edtCodigoCidade, cbSiglaEstado);
        JInternalFrame j = new JInternalFrame();
        j.getDesktopPane().add(formulario);
        formulario.setVisible(true);    
    }//GEN-LAST:event_btnLocalizaCidadeActionPerformed

    
    
    
    //LISTA DE METODOS E CHAMADAS QUE SAO PRIVATE

    
    //METODOS SOBRESCRITOS NA CLASSE VIEW_FILHA
    @Override
    protected void onKeyPressedCampoCodigo(java.awt.event.KeyEvent evt, String codigo){        
        super.onKeyPressedCampoCodigo(evt, codigo);        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (codigo.equals("")) {
                //Combobox Estado
                cbEstado.setModel(estadodb.getComboboxPessoaEstado());
                ComboBoxEstado();
                //Carrega as siglas dos estados tambem
                cbSiglaEstado.setModel(estadodb.getComboEstado());

                //Combobox Cidade
                cbCidade.setModel(cidadedb.getComboCidade());
                ComboBoxCidade();

                //Combobox Pais
                cbPais.setModel(paisdb.getComboPais());
                ComboBoxPais();
                AlteraParaDataHoraAtual();
            }
        }
    }
    
    private void btnGravarActionPerformed(java.awt.event.ActionEvent evt) {

        
    }

    private void btnGravarKeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnCancelar.grabFocus();
        }
    }

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {

        int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente Cancelar a Edição?");
        if (resposta == JOptionPane.YES_OPTION) {
            habilitaCampos(false);
            LimpaTela();
        }
    }

    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnExcluir.grabFocus();
        }
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {

        ExcluirRegistro();
    }

    private void btnExcluirKeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnConsulta.grabFocus();
        }
    }

    private void btnConsultaKeyPressed(java.awt.event.KeyEvent evt) {

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btnSair.grabFocus();
        }
    }

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

   

    

    private void edtBairroKeyPressed(java.awt.event.KeyEvent evt) {

    }

    
    //METODOS DA AREA DOS COMBOBOX
    
     private void cbEstadoActionPerformed(java.awt.event.ActionEvent evt) {
        ComboBoxEstado();
    }

    private void cbCidadeActionPerformed(java.awt.event.ActionEvent evt) {
        ComboBoxCidade();
    }
    
    private void cbSiglaEstadoKeyPressed(java.awt.event.KeyEvent evt) {

        String auxTexto = cbSiglaEstado.getSelectedItem().toString();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (auxTexto.equals("")) {
                JOptionPane.showMessageDialog(null, "Sigla do Estado nulo!");
                cbSiglaEstado.requestFocus();
            } else {
                ValidaCampoCodigoEstadoNaoNulo();
                edtCodigoCidade.setText("");
                cbCidade.setSelectedItem("");
            }
        }
    }

    private void cbPaisActionPerformed(java.awt.event.ActionEvent evt) {
        ComboBoxPais();
    }
    
    private void edtInscricaoEstadualKeyPressed(java.awt.event.KeyEvent evt) {

    }

    private void edtDataCadastroKeyPressed(java.awt.event.KeyEvent evt) {

    }

    private void edtHoraCadastroKeyPressed(java.awt.event.KeyEvent evt) {

    }

    private void edtDataAlteracaoKeyPressed(java.awt.event.KeyEvent evt) {

    }

    private void edtHoraAlteracaoKeyPressed(java.awt.event.KeyEvent evt) {

    }


    //METODOS ARRUMADOS
    protected void onSelectRadioTipoConsumo(int tipo){
        switch(tipo){
            case PersistenciaTributacao.TIPO_CONSUMO_CONSUMO:
                RadioConsumo.setSelected(true);
                Radiorevenda.setSelected(false);
            break;
            case PersistenciaTributacao.TIPO_CONSUMO_REVENDA:
                RadioConsumo.setSelected(false);
                Radiorevenda.setSelected(true);
            break;
        }
    }
    
    protected void onSelectRadioTributacao(int tipo){
        switch(tipo){
            case PersistenciaTributacao.REGIME_TRIBUTACAO_PESSOA_FISICA:
                RadioPessoaFisica.setSelected(true);
                RadioSimplesNacional.setSelected(false);
                RadioRegimeNormal.setSelected(false);
            break;
            case PersistenciaTributacao.REGIME_TRIBUTACAO_SIMPLES_NACIONAL:
                RadioPessoaFisica.setSelected(false);
                RadioSimplesNacional.setSelected(true);
                RadioRegimeNormal.setSelected(false);
            break;
            case PersistenciaTributacao.REGIME_TRIBUTACAO_NORMAL:
                RadioPessoaFisica.setSelected(false);
                RadioSimplesNacional.setSelected(false);
                RadioRegimeNormal.setSelected(true);
            break;
        }
    }
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ViewManutencaoPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ViewManutencaoPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ViewManutencaoPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ViewManutencaoPessoa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ViewManutencaoPessoa().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RadioAtivo;
    private javax.swing.JRadioButton RadioCliente;
    private javax.swing.JRadioButton RadioConsumo;
    private javax.swing.JRadioButton RadioFornecedor;
    private javax.swing.JRadioButton RadioPessoaFisica;
    private javax.swing.JRadioButton RadioRegimeNormal;
    private javax.swing.JRadioButton RadioSimplesNacional;
    private javax.swing.JRadioButton RadioTransportadora;
    private javax.swing.JRadioButton RadioVendedor;
    private javax.swing.JRadioButton Radiorevenda;
    private javax.swing.JButton botaoIncluir;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLocalizaCidade;
    private javax.swing.JButton btnLocalizaEstado;
    private javax.swing.JButton btnLocalizaPais;
    private javax.swing.JComboBox cbCidade;
    private javax.swing.JComboBox cbEstado;
    private javax.swing.JComboBox cbPais;
    private javax.swing.JComboBox cbSiglaEstado;
    private javax.swing.JTextField edtBairro;
    private javax.swing.JTextField edtCEP;
    private javax.swing.JTextField edtCPF;
    private javax.swing.JTextField edtCodigo;
    private javax.swing.JTextField edtCodigoCidade;
    private javax.swing.JTextField edtCodigoPais;
    private javax.swing.JTextField edtDataAlteracao;
    private javax.swing.JTextField edtDataCadastro;
    private javax.swing.JTextField edtEmail;
    private javax.swing.JTextField edtEndereco;
    private javax.swing.JTextField edtHoraAlteracao;
    private javax.swing.JTextField edtHoraCadastro;
    private javax.swing.JTextField edtInscricaoEstadual;
    private javax.swing.JTextField edtNome;
    private javax.swing.JTextField edtNumero;
    private javax.swing.JTextField edtTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
