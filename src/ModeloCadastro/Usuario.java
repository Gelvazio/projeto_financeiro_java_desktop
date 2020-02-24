/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloCadastro;

/**
 *
 * @author Gelvazio Camargo Tabela do banco de dados CREATE TABLE USUARIO (
 * CD_USUARIO SMALLINT NOT NULL, DS_USUARIO VARCHAR(400) NOT NULL, DS_SENHA
 * VARCHAR(10), CD_FILIAL INTEGER, DT_ALT DATE NOT NULL, HR_ALT TIME NOT NULL,
 * DT_CAD DATE NOT NULL, HR_CAD TIME NOT NULL );
 *
 */
public class Usuario {

    String ds_usuario;
    String ds_senha;
    int cd_filial;
    int cd_usuario;

    public Usuario(String ds_usuario, String ds_senha, int cd_filial, int cd_usuario) {
        this.ds_usuario = ds_usuario;
        this.ds_senha = ds_senha;
        this.cd_filial = cd_filial;
        this.cd_usuario = cd_usuario;
    }

    public String getDs_usuario() {
        return ds_usuario;
    }

    public void setDs_usuario(String ds_usuario) {
        this.ds_usuario = ds_usuario;
    }

    public String getDs_senha() {
        return ds_senha;
    }

    public void setDs_senha(String ds_senha) {
        this.ds_senha = ds_senha;
    }

    public int getCd_filial() {
        return cd_filial;
    }

    public void setCd_filial(int cd_filial) {
        this.cd_filial = cd_filial;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }
}
