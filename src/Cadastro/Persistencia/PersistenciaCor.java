package Cadastro.Persistencia;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jessica Maria Koch
 */
public class PersistenciaCor {
    private static String SQL="";

    public static String getSQL() {
        return SQL;
    }

    public static void setSQL(String SQL) {
        PersistenciaCor.SQL = SQL;
    }
    
    
    void insere() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void altera() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void exclui() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void getModelFromDb(String[] aValor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*Deve trazer os campos do banco de dados que estarao na consulta */
    public ArrayList listaCampoForConsulta() {
        ArrayList listaCampos = new ArrayList();
    
        
        return listaCampos;
    }
    
    public ArrayList setListaFromPersistencia(){
        ArrayList adicionaCampo = new ArrayList();
        String auxCodigo        = "cd_cor";
        String auxDescricao     = "ds_cor";
        String auxCodigoUsuario = "cd_usuario";        

        
        
        adicionaCampo.add(auxCodigo);
        adicionaCampo.add(auxDescricao);
        adicionaCampo.add(auxCodigoUsuario);
                
                
                
                
        listaCampos listacor = new listaCampos(
                auxCodigo,
                auxDescricao,
                auxCodigoUsuario        
        );
        //adicionaCampo.add(listacor);
        
        return adicionaCampo;
    }
    
    public void insereTeste() {
        ArrayList<listaCampos> campos = setListaFromPersistencia(); 
        
        ArrayList Valor=setListaFromPersistencia(); 
        int i=0;
        for (listaCampos auxCampos : campos) {  
            //Valor [i]=campos[i];
            
            i++;
        }
        
        
        
        
        /*
        for (listaCampos auxCampos : campos) {              
            setSQL("INSERT INTO COR        "
                    + "("+auxCampos.getCodigo()     +","
                    + " "+auxCampos.getDescricao()  +"," 
                    + "DT_ALT, DT_CAD, "
                    + "HR_CAD, HR_ALT, "
                    + " "+auxCampos.getCodigoUsuario()+")"
                    + "VALUES(?,?,                      "
                    + "CAST('NOW' AS DATE),             "
                    + "CAST('NOW' AS DATE),             "
                    + "CAST('NOW' AS TIME),             "
                    + "CAST('NOW' AS TIME),             "
                    + "?)                               ");
            
            setSQL(auxCampos.getCodigo()+ ","+auxCampos.getDescricao()+","+auxCampos.getCodigoUsuario());
                }  

*/
            setSQL("Testes Inicio"+setListaFromPersistencia()+"Teste Fim");
        
        String sql = getSQL();
        JOptionPane.showMessageDialog(null,"SQL: \n" +  sql);
    }

    class listaCampos{
        private String Codigo;
        private String Descricao;
        private String CodigoUsuario;

        public listaCampos(String Codigo, String Descricao, String CodigoUsuario) {
            this.Codigo = Codigo;
            this.Descricao = Descricao;
            this.CodigoUsuario = CodigoUsuario;
        }

        public String getCodigo() {
            return Codigo;
        }

        public void setCodigo(String Codigo) {
            this.Codigo = Codigo;
        }

        public String getDescricao() {
            return Descricao;
        }

        public void setDescricao(String Descricao) {
            this.Descricao = Descricao;
        }

        public String getCodigoUsuario() {
            return CodigoUsuario;
        }

        public void setCodigoUsuario(String CodigoUsuario) {
            this.CodigoUsuario = CodigoUsuario;
        }
        
   
    }

}

