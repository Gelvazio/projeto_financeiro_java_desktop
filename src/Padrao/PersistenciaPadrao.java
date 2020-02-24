package Padrao;

import java.util.ArrayList;

/**
 *
 * @author Gelvazio Camargo
 * @package Padrao
 * @since 18/05/2016
 */

    /*Responsável por obter os modelos e realizar as operações no banco de dados*/
    abstract class PersistenciaPadrao{
    
    ModelPadrao oModelPadrao = new ModelPadrao();
    Query oQuery             = new Query();
    
    /*Responsável por Inserir os dados no banco de dados através do modelo de dados*/
    abstract void insere();
    
    /*Responsável por Alterar os dados no banco de dados através do modelo de dados*/
    abstract void altera();
    
    /*Responsável por Excluir os dados no banco de dados através do modelo de dados*/
    abstract void exclui();
    
    /*Responsável por Obter os dados no banco de dados através de um modelo de dados*/
    abstract void get();
    
    /*Responsável por Obter todos os modelos no banco de dados através de um modelo de dados*/
    abstract void getAll();
    
    /*Responsável por receber um array retornado do banco de dados e retornar um modelo de dados populado*/
    abstract void getModelFromDb(String aValor[]);

    public ModelPadrao getoModelPadrao() {
        return oModelPadrao;
    }

    public void setoModelPadrao(ModelPadrao oModelPadrao) {
        this.oModelPadrao = oModelPadrao;
    }

    public Query getoQuery() {
        return oQuery;
    }

    public void setoQuery(Query oQuery) {
        this.oQuery = oQuery;
    }
    
    protected ArrayList listaCampoForConsulta(){
        ArrayList listaCampos = new ArrayList();
        return listaCampos;
    }

}
