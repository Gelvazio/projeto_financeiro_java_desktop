package ModeloCadastro;

/**
 *
 * @author Gelvazio Camargo Tabela Banco Dados 
 * CREATE TABLE UNIDADE_MEDIDA (
    CD_UNIDADE  SMALLINT NOT NULL,
    DS_UNIDADE  VARCHAR(40),
    DT_ALT      DATE NOT NULL,
    DT_CAD      DATE NOT NULL,
    HR_CAD      TIME NOT NULL,
    HR_ALT      TIME NOT NULL,
    CD_USUARIO  SMALLINT NOT NULL,
    CD_FILIAL   INTEGER NOT NULL
);
 */
public class UnidadeMedida {

    int cd_unidade;
    String ds_unidade;
    String ds_sigla;
    int cd_usuario;
    int cd_filial;

    public UnidadeMedida(int cd_unidade, String ds_unidade, String ds_sigla, int cd_usuario, int cd_filial) {
        this.cd_unidade = cd_unidade;
        this.ds_unidade = ds_unidade;
        this.ds_sigla = ds_sigla;
        this.cd_usuario = cd_usuario;
        this.cd_filial = cd_filial;
    }

    public int getCd_unidade() {
        return cd_unidade;
    }

    public void setCd_unidade(int cd_unidade) {
        this.cd_unidade = cd_unidade;
    }

    public String getDs_unidade() {
        return ds_unidade;
    }

    public void setDs_unidade(String ds_unidade) {
        this.ds_unidade = ds_unidade;
    }

    public String getDs_sigla() {
        return ds_sigla;
    }

    public void setDs_sigla(String ds_sigla) {
        this.ds_sigla = ds_sigla;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }

    public int getCd_filial() {
        return cd_filial;
    }

    public void setCd_filial(int cd_filial) {
        this.cd_filial = cd_filial;
    }

    
}
