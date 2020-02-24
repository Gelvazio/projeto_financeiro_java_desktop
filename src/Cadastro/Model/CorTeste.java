package Cadastro.Model;

/**
 *
 * @author Gelvazio Camargo CREATE TABLE COR ( CD_COR INTEGER NOT NULL, DS_COR
 * VARCHAR(50), DT_ALT DATE NOT NULL, DT_CAD DATE NOT NULL, HR_CAD TIME NOT
 * NULL, HR_ALT TIME NOT NULL, CD_USUARIO SMALLINT NOT NULL );
 */
public class CorTeste {

    private int cd_cor;
    private String ds_cor;
    private int cd_usuario;

    public int getCd_cor() {
        return cd_cor;
    }

    public void setCd_cor(int cd_cor) {
        this.cd_cor = cd_cor;
    }

    public String getDs_cor() {
        return ds_cor;
    }

    public void setDs_cor(String ds_cor) {
        this.ds_cor = ds_cor;
    }

    public int getCd_usuario() {
        return cd_usuario;
    }

    public void setCd_usuario(int cd_usuario) {
        this.cd_usuario = cd_usuario;
    }
}
